package frc;

import frc.robot.Shooter;

public class BallManager {
    private Shooter shooter = new Shooter();
    public void shoot(double limelight_ticks, double limelight_speed){
        shooter.adjustHood(limelight_ticks);
        shooter.revShooter(limelight_speed);
    }
}
