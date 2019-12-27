package robot;

/**
 * A class holding all of the ports on the robot.
 * Place mechanism-specific ports inside their own sub-class.
 * When accessing a mechanism-specific port, call Ports.[MECHANISM].[PORT_NAME]
 */
public class Ports {
    public static class ExampleSubsystem1 {
        //public static int TALON_PORT = 1;
    }

    public static class slingshot{
        public static int SLING_MASTER = 0;
        public static int SLING_SLAVE_1 = 0;
        public static int SLING_SLAVE_2 = 0;
        public static int SHIFTER_FORWARD_PORT =0;
        public static int SHIFTER_REVERSE_PORT =0;
    }
}
