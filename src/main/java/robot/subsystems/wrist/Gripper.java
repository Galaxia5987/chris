package robot.subsystems.wrist;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import robot.subsystems.wrist.commands.InsertCube;

import static robot.Constants.Gripper.MASTER_INVERTED;
import static robot.Ports.Gripper.MASTER;

public class Gripper extends Subsystem {
    private VictorSPX master = new VictorSPX(MASTER);

    public Gripper() {
        master.setInverted(MASTER_INVERTED);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new InsertCube(0.5, 5));
    }

    /**
     * Set the speed for the gripper controllers.
     *
     * @param speed the desired speed.
     */
    public void setSpeed(double speed) {
        master.set(ControlMode.PercentOutput, speed);
    }
}
