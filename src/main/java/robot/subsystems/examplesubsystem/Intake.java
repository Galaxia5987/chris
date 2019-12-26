package robot.subsystems.examplesubsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import static robot.Constants.Intake.*;
import static robot.Ports.Intake.*;

public class Intake extends Subsystem {
    private Solenoid solenoid = new Solenoid(SOLENOID);
    private VictorSPX motor = new VictorSPX(MOTOR);
    private double speed;

    public Intake(double speed){
        this.speed = speed;
        motor.setInverted(IS_MOTOR_INVERTED);
    }

    @Override
    protected void initDefaultCommand() {

    }

    /**
     * set the direction of the solenoid down
     */
    public void goUp(){
        solenoid.set(UP);
    }

    /**
     * set the direction of the solenoid down
     */
    public void goDown(){
        solenoid.set(DOWN);
    }

    /**
     * turn the wheels of the intake
     * @param speed the speed the wheels spin
     */
    public void turnWheels(double speed){
        motor.set(ControlMode.PercentOutput, speed);
    }



}
