package robot.slingshot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.Constants;
import robot.Robot;

public class ShotToDistanceCommand extends Command {


    private double targetDistance;
    private double loadPosition;

    public ShotToDistanceCommand(double targetDistance) {
        requires(Robot.m_slingshot);
        this.targetDistance = targetDistance;
    }

    @Override
    protected void initialize() {
        loadPosition = calculateForcePerDistance(targetDistance)/Constants.slingshot.K_SPRING;
        Robot.m_slingshot.setPosition(loadPosition);
    }

    @Override
    protected void execute() {

    }

    @Override
    protected boolean isFinished() {
        return (loadPosition - Robot.m_slingshot.getPosition())< Constants.slingshot.TOLERANCE;
    }

    @Override
    protected void interrupted() {

    }

    @Override
    protected void end() {
        Robot.m_slingshot.shiftToNeutral();
    }

    protected double calculateForcePerDistance(double distance){
        return 0;//TODO find the actual calculation
    }
}
