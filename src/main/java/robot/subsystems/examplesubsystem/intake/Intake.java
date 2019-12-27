package robot.subsystems.examplesubsystem.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import static robot.Constants.Intake.*;
import static robot.Ports.Intake.*;

public class Intake extends Subsystem {
    private Solenoid solenoid = new Solenoid(SOLENOID);
    private VictorSPX motor = new VictorSPX(MOTOR);

    public Intake(){
        motor.setInverted(IS_MOTOR_INVERTED);
    }

    @Override
    protected void initDefaultCommand() {

    }

    /**
     * set the direction of the solenoid
     */
    public void setPosition(Direction direction){
        if (direction == direction.UP)
            solenoid.set(false);
        else
            solenoid.set(true);
    }

    /**
     * turn the wheels of the intake
     * @param speed the speed the wheels spin
     */
    public void turnWheels(double speed){
        motor.set(ControlMode.PercentOutput, speed);
    }

    public enum Direction{
        UP,
        DOWN
    }


}
