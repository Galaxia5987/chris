package robot.subsystems.wrist.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import robot.subsystems.wrist.Wrist;

import static robot.Robot.wrist;

public class MoveToCube extends Command {
    private double duration;
    private Timer timer = new Timer();

    public MoveToCube(double duration) {
        requires(wrist);
        this.duration = duration;
    }

    @Override
    protected void initialize() {
        timer.reset();
        timer.start();
        wrist.turnTo(Wrist.State.GET);
    }

    @Override
    protected void execute() {
        if (wrist.getAngle() < 45)
            wrist.turnTo(Wrist.State.GET);
    }

    @Override
    protected boolean isFinished() {
        return timer.get() < duration;
    }
}
