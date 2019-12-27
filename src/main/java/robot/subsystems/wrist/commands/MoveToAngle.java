package robot.subsystems.wrist.commands;

import edu.wpi.first.wpilibj.command.Command;

import static robot.Robot.wrist;

public class MoveToAngle extends Command {
    private double angle;

    public MoveToAngle(double angle) {
        requires(wrist);
        this.angle = angle;
    }

    @Override
    protected void initialize() {
        wrist.turnTo(angle);
    }

    @Override
    protected void execute() {
        wrist.turnTo(angle);
    }

    @Override
    protected boolean isFinished() {
        return Math.abs(wrist.getAngle() - angle) < (angle * 0.05);
    }

    @Override
    protected void end() {
        wrist.end();
    }
}
