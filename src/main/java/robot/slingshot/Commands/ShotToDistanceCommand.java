package robot.slingshot.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import robot.Constants;
import robot.Robot;

/**
 * This command would shot the cargo to a certain distance
 */
public class ShotToDistanceCommand extends Command {


    private double targetDistance;// the desired distance for the cargo
    private double loadPosition;// where the spring should be
    private Timer counter = new Timer();

    public ShotToDistanceCommand(double targetDistance) {
        requires(Robot.m_slingshot);
        this.targetDistance = targetDistance;
    }

    @Override
    protected void initialize() {
        loadPosition = calculateForcePerDistance(targetDistance) / Constants.slingshot.K_SPRING;//calculate the position of the spring
        Robot.m_slingshot.setPosition(loadPosition);
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
        return (loadPosition - Robot.m_slingshot.getPosition()) < Constants.slingshot.TOLERANCE;
    }

    @Override
    protected void interrupted() {

    }

    @Override
    protected void end() {
        Robot.m_slingshot.shiftToNeutral();
    }

    /**
     * This method would calculate the force you need to apply on the cargo to shot him to certain distance
     *
     * @param distance the distance in meters
     * @return the force you need to apply in newtons
     */
    protected double calculateForcePerDistance(double distance) {
        return 0;//TODO find the actual calculation (the calculation would be found through an experiment)
    }
}
