package robot.slingshot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Constants;
import robot.Robot;

public class LoadAndShotCommand extends Command {

    private double targetDistance;
    private double position;

    public LoadAndShotCommand(double targetDistance) {
        requires(Robot.m_slingshot);
        this.targetDistance = targetDistance;
    }

    @Override
    protected void initialize() {
        position = Robot.m_slingshot.getPosition();
        Robot.m_slingshot.loadByDistance(targetDistance);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return Math.abs((position-Robot.m_slingshot.getPosition())-targetDistance)< Constants.slingshot.TOLERANCE;
    }

    @Override
    protected void interrupted() {

    }

    @Override
    protected void end() {
        Robot.m_slingshot.shiftToNeutral();
    }
}
