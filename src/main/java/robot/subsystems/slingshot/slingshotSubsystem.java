package robot.subsystems.slingshot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.Constants;
import robot.Ports;

/**
 * The subsystem of the slingshot which will be used for shooting the cargo to the target
 */
public class slingshotSubsystem extends Subsystem {
    private TalonSRX slingMaster = new TalonSRX(Ports.slingshot.SLING_MASTER);
    private VictorSPX slingSlave1 = new VictorSPX(Ports.slingshot.SLING_SLAVE_1);
    private VictorSPX slingSlave2 = new VictorSPX(Ports.slingshot.SLING_SLAVE_2);
    private DoubleSolenoid shifter = new DoubleSolenoid(1, Ports.slingshot.SHIFTER_FORWARD_PORT, Ports.slingshot.SHIFTER_REVERSE_PORT);


    public slingshotSubsystem() {
        slingSlave1.follow(slingMaster);
        slingSlave2.follow(slingMaster);

        slingMaster.configPeakCurrentLimit(Constants.slingshot.MAX_MASTER_CURRENT);
        slingMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        slingMaster.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyOpen);
    }

    /**
     * The function to convert ticks to meters
     *
     * @param ticks the ticks from the encoder
     * @return the distance in meters
     */
    public double convertTickToMeters(double ticks) {
        return ticks / Constants.slingshot.TICKS_PER_METER;
    }

    /**
     * The function to convert meters to ticks
     *
     * @param meters the distance in meters
     * @return the ticks of the encoder
     */
    public double convertMetersToTicks(double meters) {
        return meters * Constants.slingshot.TICKS_PER_METER;
    }

    /**
     * This method would shift to a neutral gear
     */
    public void shiftToNeutral() {
        shifter.set(DoubleSolenoid.Value.kReverse);
    }

    /**
     * This method would shift and connect the gear
     */
    public void shiftToConnect() {
        shifter.set(DoubleSolenoid.Value.kForward);
    }

    /**
     * @return the position of the spring in meters
     */
    public double getPosition() {
        return convertTickToMeters(slingMaster.getSelectedSensorPosition());
    }

    /**
     * That method would place the spring in a certain position
     *  In case that the distance is negative ,the position would be changed to 0
     *  In case that the distance is beyond the length of the spring, the position would be changed to the length of the spring
     * @param position the position of the spring in meters
     */
    public void setPosition(double position) {
        if (position < 0) {
            slingMaster.set(ControlMode.Position, 0);
        } else if (position > Constants.slingshot.LENGTH_OF_SPRING) {
            slingMaster.set(ControlMode.Position, Constants.slingshot.LENGTH_OF_SPRING);
        } else {
            slingMaster.set(ControlMode.Position, convertMetersToTicks(position));
        }
    }

    /**
     * That method would move the spring a certain distance
     *  In case that the distance is negative ,the position would be changed to 0
     *  In case that the distance is beyond the length of the spring, the position would be changed to the length of the spring
     * @param distance the distance that the motors would move the spring in meters
     */
    public void loadByDistance(double distance) {
        double position = convertMetersToTicks(getPosition() + distance);
        if (position < 0) {
            slingMaster.set(ControlMode.Position, 0);
        } else if (position > Constants.slingshot.LENGTH_OF_SPRING) {
            slingMaster.set(ControlMode.Position, Constants.slingshot.LENGTH_OF_SPRING);
        } else {
            slingMaster.set(ControlMode.Position, position);
        }
    }

    /**
     * This method would reset the encoder
     */
    public void Reset() {
        slingMaster.setSelectedSensorPosition(0);
    }

    /**
     * If the sling would reach to the limit switch it would reset the encoder and connect the motor to the gear
     */
    public void update() {
        if (slingMaster.getSensorCollection().isFwdLimitSwitchClosed()) {
            Reset();
            shiftToConnect();
        }
    }

    @Override
    protected void initDefaultCommand() {

    }


}
