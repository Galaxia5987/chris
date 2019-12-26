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


}
