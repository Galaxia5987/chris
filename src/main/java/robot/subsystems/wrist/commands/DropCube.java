package robot.subsystems.wrist.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import static robot.Robot.gripper;

public class DropCube extends Command {
    private double speed;
    private double timeout;
    private Timer timer = new Timer();

    public DropCube(double speed, double timeout) {
        requires(gripper);
        this.speed = speed;
        this.timeout = timeout;
    }

    public DropCube(double speed) {
        this(speed, 0);
    }

    @Override
    protected void initialize() {
        timer.reset();
        timer.start();
        gripper.setSpeed(-speed);
    }

    @Override
    protected void execute() {
        gripper.setSpeed(-  speed);
    }

    @Override
    protected boolean isFinished() {
        return timeout > 0 && timer.get() > timeout;
    }

    @Override
    protected void end() {
        timer.stop();
        gripper.setSpeed(0);
    }
}
