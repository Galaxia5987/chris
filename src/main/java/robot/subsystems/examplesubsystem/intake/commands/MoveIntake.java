package robot.subsystems.examplesubsystem.intake.commands;

import edu.wpi.first.wpilibj.command.Command;
import robot.subsystems.examplesubsystem.intake.Intake.*;

import static robot.Robot.intake;

public class MoveIntake extends Command {
    private Direction direction;

    public MoveIntake(Direction direction){
        requires(intake);
        this.direction = direction;
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
