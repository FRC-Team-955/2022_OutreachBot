package frc.robot;

public class Constants {
    
    public static final class DrivebaseConstants {
        // Talons
        public static final int kTalonTopRightId = 3;
        public static final int kTalonBotRightId = 4;
        public static final int kTalonTopLeftId = 1;
        public static final int kTalonBotLeftId = 2;
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
        // hood pid
        public static final double kHoodP = 0;
        public static final double kHoodI = 0;
        public static final double kHoodD = 0;

        // Target Ranges
        // Hood
        public static final int kHoodTargetRange = 100;
        // Shooter
        public static final int kShooterTargetRange = 100;
    }
}
