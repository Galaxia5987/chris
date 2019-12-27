package robot.slingshot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Constants;
import robot.Robot;

public class PositionShotCommand extends Command {

    private double targetPosition;


    public PositionShotCommand(double targetPosition) {
        requires(Robot.m_slingshot);
        this.targetPosition = targetPosition;
    }

    @Override
    protected void initialize() {
        Robot.m_slingshot.loadByDistance(targetPosition);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return Math.abs(Robot.m_slingshot.getPosition()- targetPosition)< Constants.slingshot.TOLERANCE;
    }

    @Override
    protected void interrupted() {

    }

    @Override
    protected void end() {
        Robot.m_slingshot.shiftToNeutral();
    }
}
