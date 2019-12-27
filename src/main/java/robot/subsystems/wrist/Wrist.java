package robot.subsystems.wrist;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;

import static robot.Ports.Wrist.*;
import static robot.Constants.Wrist.*;

public class Wrist extends Subsystem {
    private TalonSRX masterMotor = new TalonSRX(MASTER);
    private VictorSPX slaveMotor = new VictorSPX(SLAVE);

    public Wrist() {
        masterMotor.setInverted(MASTER_INVERTED);
        slaveMotor.setInverted(SLAVE_INVERTED);
        masterMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, PID_SLOT, TIMEOUT_MS);
        masterMotor.config_kP(PID_SLOT, KP, TIMEOUT_MS);
        masterMotor.config_kI(PID_SLOT, KI, TIMEOUT_MS);
        masterMotor.config_kD(PID_SLOT, KD, TIMEOUT_MS);
        slaveMotor.follow(masterMotor);
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void turnTo(double angle) {

    }

    public void turnTo(State state) {

    }

    public double convertTicksToDegrees(double ticks) {
        return ticks / TICKS_PER_DEGREE;
    }

    public double convertDegreesToTicks(double degrees) {
        return degrees * TICKS_PER_DEGREE;
    }

    public double getAngle() {
        return convertTicksToDegrees(masterMotor.getSelectedSensorPosition());
    }

    public enum State {

    }
}
