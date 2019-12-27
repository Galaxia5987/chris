package robot.subsystems.wrist;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.subsystems.wrist.commands.InsertCube;

import static robot.Constants.Wrist.*;
import static robot.Ports.Wrist.MASTER;
import static robot.Ports.Wrist.SLAVE;

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
        setDefaultCommand(new InsertCube(5));
    }

    public void turnTo(double angle) {
        angle %= 360;
        masterMotor.set(ControlMode.MotionMagic, convertDegreesToTicks(angle) + applyForceByAngle(angle));
    }

    public void turnTo(State state) {
        masterMotor.set(ControlMode.PercentOutput, state.applyForces());
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

    public double applyForceByAngle(double angle) {
        return convertDegreesToTicks(Math.cos(angle));
    }

    public void end() {
        masterMotor.config_kF(PID_SLOT, applyForceByAngle(getAngle()), TIMEOUT_MS);
    }

    public enum State {
        GET {
            @Override
            public double applyForces() {
                return -50;
            }
        },
        SHOOT {
            @Override
            public double applyForces() {
                return 50;
            }
        },
        REVERSE_SHOOT {
            @Override
            public double applyForces() {
                return 50;
            }
        };

        public abstract double applyForces();
    }
}
