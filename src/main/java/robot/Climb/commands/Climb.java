package robot.Climb.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

import static robot.Robot.climb;

public class Climb extends Command {

    private double height;

    public Climb(double height) {
        requires(climb);
        this.height = height;
    }

    @Override
    protected void initialize() {
        climb.setHeight(height);
    }

    @Override
    protected void execute() {
        climb.climb();

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void interrupted() {

    }

    @Override
    protected void end() {
        climb.holdInPlace();
    }
}
