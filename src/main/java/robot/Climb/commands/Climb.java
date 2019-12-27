package robot.Climb.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;

import static robot.Robot.climb;

public class Climb extends Command {

    public ExampleCommand(double speed) {
        requires(Robot.m_example);
    public Climb(double height) {
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        System.out.println(speed);

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
    }
}
