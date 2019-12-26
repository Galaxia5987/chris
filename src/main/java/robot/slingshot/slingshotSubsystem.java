package robot.slingshot;

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
 * An example subsystem to represent the basic structure of a robot subsystem
 */
public class slingshotSubsystem extends Subsystem {
    private TalonSRX slingMaster = new TalonSRX(Ports.slingshot.SLING_MASTER);
    private VictorSPX slingSlave1 = new VictorSPX(Ports.slingshot.SLING_SLAVE_1);
    private VictorSPX slingSlave2 = new VictorSPX(Ports.slingshot.SLING_SLAVE_2);
    private DoubleSolenoid shifter = new DoubleSolenoid(1, Ports.slingshot.SHIFTER_FORWARD_PORT,Ports.slingshot.SHIFTER_REVERSE_PORT);


    public slingshotSubsystem() {
        slingSlave1.follow(slingMaster);
        slingSlave2.follow(slingMaster);

        slingMaster.configPeakCurrentLimit(Constants.slingshot.MAX_MASTER_CURRENT);
        slingMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

        slingMaster.configForwardLimitSwitchSource(LimitSwitchSource.FeedbackConnector, LimitSwitchNormal.NormallyClosed);
    }

    public double convertTickToMeters(double ticks){
        return ticks/Constants.slingshot.TICKS_PER_METER;
    }

    public double convertMetersToTicks(double meters){
        return meters*Constants.slingshot.TICKS_PER_METER;
    }

    public void shiftToNeutral(){
        shifter.set(DoubleSolenoid.Value.kReverse);
    }

    public void shiftToConnect(){
        shifter.set(DoubleSolenoid.Value.kForward);
    }

    public double getPosition(){
        return convertTickToMeters(slingMaster.getSelectedSensorPosition());
    }

    public void setPosition(double position){
        slingMaster.set(ControlMode.Position, convertMetersToTicks(position));
    }

    public void loadByDistance(double distance){
        slingMaster.set(ControlMode.Position, convertMetersToTicks(getPosition() + distance));
    }

    public void Reset(){
        slingMaster.setSelectedSensorPosition(0);
    }

    public void update(){
        if (slingMaster.getSensorCollection().isFwdLimitSwitchClosed()){
            Reset();
            shiftToConnect();
        }
    }

    @Override
    protected void initDefaultCommand() {

    }


}
