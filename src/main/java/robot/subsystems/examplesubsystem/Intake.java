package robot.subsystems.examplesubsystem;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import static robot.Ports.Intake.*;

public class Intake extends Subsystem {
    private Solenoid solenoid = new Solenoid(SOLENOID);
    private VictorSPX motor = new VictorSPX(MOTOR);
    private double speed;
    
    public Intake(double speed){
        this.speed = speed;
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void goUp(){}

    public void goDown(){}

    public void turnWheels(double speed){}



}
