package frc.robot;

public class Constants {
    
    public static final class DrivebaseConstants {
        // Talons
        public static final int kTalonTopRightId = 3;
        public static final int kTalonBotRightId = 4;
        public static final int kTalonTopLeftId = 1;
        public static final int kTalonBotLeftId = 2;
    }

    public static final class Limelight {
        public static final double kAlignDistance = 10;
        public static final double kShooterSpeedMult = 4.20;
        public static final double kShooterSpeedBase = 6.9;
        public static final double kHoodPositionMult = 1.43;
        public static final double kHoodPositionBase = 0.23;
        
    }

    public static final class ShooterConstants {
        // Talons
        public static final int kHoodMotorTalonId = 0;
        public static final int kShooterMainTalonId = 0;
        public static final int kShooterFollowTalonId = 0;

        // Pids
        // Shooter Pid
        public static final double kShooterP = 0;
        public static final double kShooterI = 0;
        public static final double kShooterD = 0;
        public static final double kShooterF = 0;
        // hood pid
        public static final double kHoodP = 0;
        public static final double kHoodI = 0;
        public static final double kHoodD = 0;

        // Target Ranges
        // Hood
        public static final int kHoodTargetRange = 100;
        // Shooter
        public static final int kShooterTargetRange = 100;

        // Limit Switch
        public static final int kTopLimitSwitchId = 0;
        public static final int kBotLimitSwitchId = 0;
    }

    public static final class BallManagerConstants{
        public static final double kDefaultAngle = 42.0;
        public static final double kDefaultSpeed = 6.9;
        public static final double kHoodShootSpeed = 3.25;
    }

    public static final class TurretConstants{
        public static final double kTicksToDegree = 350;
    }
}
