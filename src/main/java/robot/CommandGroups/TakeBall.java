package robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.subsystems.examplesubsystem.intake.Intake;
import robot.subsystems.examplesubsystem.intake.commands.MoveIntake;
import robot.subsystems.examplesubsystem.intake.commands.PullBall;
import robot.subsystems.wrist.commands.InsertCube;
import robot.subsystems.wrist.commands.MoveToAngle;

import javax.swing.*;

public class TakeBall extends CommandGroup {
    public TakeBall(double speed, double timeout){
        addSequential(new MoveIntake(Intake.Direction.DOWN));
        addParallel(new MoveToAngle(0));
        addSequential(new InsertCube(speed, timeout));
        addSequential(new PullBall(speed, timeout));
    }
}
