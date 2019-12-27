package robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;
import robot.subsystems.slingshot.Commands.ShotToDistanceCommand;
import robot.subsystems.wrist.commands.DropCube;
import robot.subsystems.wrist.commands.MoveToAngle;

public class Shoot extends CommandGroup {
    public Shoot(double angle, double distance, double speed){
        addSequential(new MoveToAngle(angle));
        addSequential(new ShotToDistanceCommand(distance));
        addParallel(new DropCube(speed));

    }
}
