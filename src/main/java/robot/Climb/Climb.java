package robot.Climb;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.Constants;
import robot.Ports;


/**
 * A subsystem for the robot's climb mechanism
 */
public class Climb extends Subsystem {

    private static TalonSRX climbMaster = new TalonSRX(Ports.ClimbSubsystem.CLIMB_MASTER);
    private static VictorSPX climbSlave = new VictorSPX(Ports.ClimbSubsystem.CLIMB_SLAVE);
    private static VictorSPX railMotor = new VictorSPX(Ports.ClimbSubsystem.RAIL_MOTOR);
    private double targetHeight = 0;

    public Climb() {
        //Encoder configuration
        climbMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.TALON_TIME_OUT_MS);
        climbMaster.setSelectedSensorPosition(0);

        //Motor invertion
        climbMaster.setInverted(Constants.ClimbSubsystem.CLIMB_MASTER_INVERTED);
        climbSlave.setInverted(Constants.ClimbSubsystem.CLIMB_SLAVE_INVERTED);
        railMotor.setInverted(Constants.ClimbSubsystem.RAIL_MOTOR_INVERTED);

        climbMaster.configMotionCruiseVelocity(convertMetersToTicks(Constants.ClimbSubsystem.CRUISE_VELOCITY), Constants.TALON_TIME_OUT_MS);
        climbMaster.configMotionAcceleration(convertMetersToTicks(Constants.ClimbSubsystem.CRUISE_ACCELERATIONN), Constants.TALON_TIME_OUT_MS)

        //Encoder invertion
        climbMaster.setSensorPhase(Constants.ClimbSubsystem.CLIMB_ENCODER_INVERTED);

        //Follow configuration
        climbSlave.follow(climbMaster);

        //PIDF configurations
        climbMaster.config_kP(0, Constants.ClimbSubsystem.CLIMB_PIDF[0], Constants.TALON_TIME_OUT_MS);
        climbMaster.config_kI(0, Constants.ClimbSubsystem.CLIMB_PIDF[1], Constants.TALON_TIME_OUT_MS);
        climbMaster.config_kD(0, Constants.ClimbSubsystem.CLIMB_PIDF[2], Constants.TALON_TIME_OUT_MS);
        climbMaster.config_kF(0, Constants.ClimbSubsystem.CLIMB_PIDF[3], Constants.TALON_TIME_OUT_MS);


    }


    /**
     * Sets the wanted height for the climb
     * @param height wanted height for the climb
     */
    public void setHeight(double height){
        if (height > 0 && height < Constants.ClimbSubsystem.MAX_CLIMB_HEIGHT)
            targetHeight = height;

    }

    /**
     *
     * @return the current height of the robot as part of the climb, 0 - at ground level, max height(constant number) - robot has finished a full climb to level 3
     */
    public double getHeight(){
        return convertTicksToMeters(climbMaster.getSelectedSensorPosition());
    }

    public void setRailMotorSpeed(double speed){
        if (speed < 1 && speed > -1) {
            railMotor.set(ControlMode.PercentOutput, speed);
        }
    }

    /**
     *  Moves the climb system after a set height is decided in the {@link #setHeight(double)} command
     */
    public void climb(){
        climbMaster.set(ControlMode.MotionMagic, targetHeight, DemandType.ArbitraryFeedForward, Constants.ClimbSubsystem.GRAVITY_COMPENSATION);

    }


    /**
     *  Called at the end of a climb command, in case the climb is at it's starting position it will deny any force from being applied
     */
    public void holdInPlace(){
        if(targetHeight < Constants.ClimbSubsystem.DISABLE_THRESHOLD && getHeight() < Constants.ClimbSubsystem.DISABLE_THRESHOLD)
            climbMaster.set(ControlMode.PercentOutput, 0);
    }

    /**
     *
     * @param ticks
     * @return The given amount of ticks in meters //TODO: use coefficient instead lol
     */
    private double convertTicksToMeters(int ticks){
        return ticks/Constants.ClimbSubsystem.TICKS_PER_METER;
    }

    /**
     *
     * @param meters
     * @return The given amount of meters in ticks //TODO: use coefficient instead lol
     */
    private int convertMetersToTicks(double meters){
        return (int) (meters*Constants.ClimbSubsystem.TICKS_PER_METER);
    }

    @Override
    protected void initDefaultCommand() {

    }

}
