package robot.Climb.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import static robot.Robot.climb;

/**
 *The command to move the robot along it's rail after it has finished climbing
 *There are two constructors, one for using a manual command to drive it along the rail until requested a stop and the other
 * for using a timer to decide how long it will drive along the rail
 */
public class RailDrive extends Command {

    private Timer timer = new Timer();
    private double delay = 0;//in seconds
    public RailDrive() {
        requires(climb);
    }

    public RailDrive(double delay){
        this.delay = delay;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        climb.setRailMotorSpeed(0.5);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (delay > 0)
            return timer.get() >= delay;
        else
            return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        climb.setRailMotorSpeed(0);
    }

    // Called when another command which requires one or more of the same
// subsystems is scheduled to run
    protected void interrupted() {
    }
}