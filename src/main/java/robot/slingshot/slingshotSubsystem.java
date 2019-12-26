package robot.slingshot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
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

    }


    @Override
    protected void initDefaultCommand() {

    }
}
