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
        if (direction == direction.UP) // TODO: check which boolean is UP and Down
            solenoid.set(true);
        else
            solenoid.set(false);
    }

    /**
     * @return return the position of the intake
     */
    public Direction getCurrentPosition(){
        if (solenoid.get())
            return Direction.UP;
        else
            return Direction.DOWN;
    }

    /**
     * turn the wheels of the intake
     * @param speed the speed the wheels spin
     */
    public void spinWheels(double speed){
        motor.set(ControlMode.PercentOutput, speed);
    }

    public enum Direction{
        UP,
        DOWN
    }


}
