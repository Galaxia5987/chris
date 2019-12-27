package robot.subsystems.wrist;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

import static robot.Ports.Wrist.*;

public class Wrist extends Subsystem {
    private TalonSRX masterMotor = new TalonSRX(MASTER);
    private VictorSPX slaveMotor = new VictorSPX(SLAVE);

    public Wrist() {

        slaveMotor.follow(masterMotor);
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void turnTo(double angle) {

    }

    public void turnTo(State state) {

    }


    public enum State {

    }
}
