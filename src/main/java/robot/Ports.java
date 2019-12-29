package robot;

import static org.apache.commons.lang3.ObjectUtils.CONST;

/**
 * A class holding all of the ports on the robot.
 * Place mechanism-specific ports inside their own sub-class.
 * When accessing a mechanism-specific port, call Ports.[MECHANISM].[PORT_NAME]
 */
public class Ports {


    public static class ClimbSubsystem {
        public static final int CLIMB_MASTER = CONST(0);
        public static final int CLIMB_SLAVE = CONST(0);
        public static final int RAIL_MOTOR = CONST(0);
    }


    public static class Intake {
        public static final int SOLENOID = 0;
        public static final int MOTOR = 0;
    }
    public static class ExampleSubsystem1 {
        //public static int TALON_PORT = 1;
    }

    public class Wrist {
        public static final int MASTER = 0;
        public static final int SLAVE = 0;
    }

    public static class slingshot{
        public static int SLING_MASTER = 0;
        public static int SLING_SLAVE_1 = 0;
        public static int SLING_SLAVE_2 = 0;
        public static int SHIFTER_FORWARD_PORT =0;
        public static int SHIFTER_REVERSE_PORT =0;
    }

    public static class Gripper {
        public static final int MASTER = 0; //TODO: Change to the real port
    }


}
