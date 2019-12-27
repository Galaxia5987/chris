package robot.Climb;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.Constants;
import robot.Ports;

import javax.naming.ldap.Control;

/**
 * An example subsystem to represent the basic structure of a robot subsystem
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
     * /----------------------------------------------------------------------------------------------/
     *  Moves the climb system after a set height is decided in the {@link #setHeight(double)} command
     * /----------------------------------------------------------------------------------------------/
     */
    public void climb(){
        if(targetHeight < Constants.ClimbSubsystem.DISABLE_THRESHOLD && getHeight() < Constants.ClimbSubsystem.DISABLE_THRESHOLD){
            climbMaster.set(ControlMode.PercentOutput, 0);
        }
        else{
            climbMaster.set(ControlMode.MotionMagic, targetHeight, DemandType.ArbitraryFeedForward, Constants.ClimbSubsystem.GRAVITY_COMPENSATION);
        }
    }


    private double convertTicksToMeters(int ticks){
        return ticks/Constants.ClimbSubsystem.TICKS_PER_METER;
    }

    private double convertMetersToTicks(double meters){
        return meters*Constants.ClimbSubsystem.TICKS_PER_METER;
    }

    @Override
    protected void initDefaultCommand() {

    }

}
