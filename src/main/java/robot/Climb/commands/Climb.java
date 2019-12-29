package robot.Climb.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Constants;

import static robot.Robot.climb;

public class Climb extends Command {

    private double height;

    /**
     *The main command for the climb system, will lift the robot up until the requested {@link #height}
     */

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
        return climb.getHeight()-0.01 >= height;
    }

    @Override
    protected void interrupted() {

    }

    @Override
    protected void end() {
        climb.holdInPlace();
    }
}
