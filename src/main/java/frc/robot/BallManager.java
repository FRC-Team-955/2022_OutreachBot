package frc.robot;

public class BallManager {
    private Shooter shooter = new Shooter();
    private Spindex spindex = new Spindex();
    private Limelight limelight = new Limelight();
    private void shoot(double limelight_ticks, double limelight_speed){
        if(shooter.adjustHood(limelight_ticks) && shooter.revShooter(limelight_speed)){
        spindex.runToShooter();
        }
    }
    public void shoot(){
        double limelight_speed = limelight.getShooterSpeed();
        double limelight_ticks = limelight.getHoodPosition();
        if(shooter.adjustHood(limelight_ticks) && shooter.revShooter(limelight_speed)){
            spindex.runToShooter();
        }
    }

    public void setShoot(){
        if(shooter.adjustHood(69) && shooter.revShooter(420)){
            spindex.runToShooter();
        }
    }
}

