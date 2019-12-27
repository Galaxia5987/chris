package robot.subsystems.examplesubsystem.intake.commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import static robot.Robot.intake;

public class PullBall extends Command {
    private Timer timer = new Timer();
    private double speed;
    private double timeout;

    public PullBall(double speed, double timeout){
        this.speed = speed;
        this.timeout = timeout;
        requires(intake);
    }

    @Override
    protected void initialize(){

    }

    @Override
    protected void execute(){

    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end(){}

}
