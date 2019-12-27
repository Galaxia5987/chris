package robot.slingshot.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import robot.Constants;
import robot.Robot;

public class LoadAndShotCommand extends Command {

    private double targetDistance;
    private double position;
    private Timer counter = new Timer();


    public LoadAndShotCommand(double targetDistance) {
        requires(Robot.m_slingshot);
        this.targetDistance = targetDistance;
    }

    @Override
    protected void initialize() {
        position = Robot.m_slingshot.getPosition();
        Robot.m_slingshot.loadByDistance(targetDistance);
        counter.start();
    }

    @Override
    protected void execute() {
        if (Robot.m_slingshot.getPosition() == 0 && counter.get() > 1) {
            this.end();
        }
    }

    @Override
    protected boolean isFinished() {
        return Math.abs((position - Robot.m_slingshot.getPosition()) - targetDistance) < Constants.slingshot.TOLERANCE;
    }

    @Override
    protected void interrupted() {

    }

    @Override
    protected void end() {
        Robot.m_slingshot.shiftToNeutral();
    }
}
