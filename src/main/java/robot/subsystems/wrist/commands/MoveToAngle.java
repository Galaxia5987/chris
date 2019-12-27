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
    }

    @Override
    protected void execute() {
    }

    @Override
    protected boolean isFinished() {
        return Math.abs();
    }

    @Override
    protected void end() {
    }
}
