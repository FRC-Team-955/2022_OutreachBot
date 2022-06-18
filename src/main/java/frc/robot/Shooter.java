package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.math.controller.PIDController;

public class Shooter {

    public Shooter(){
        mShooterFollowTalon.follow(mShooterMainTalon);
    }
    
    // Talons
    TalonSRX mHoodMotorTalon = new TalonSRX(Constants.ShooterConstants.kHoodMotorTalonId);
    TalonSRX mShooterMainTalon = new TalonSRX(Constants.ShooterConstants.kShooterMainTalonId);
    TalonSRX mShooterFollowTalon = new TalonSRX(Constants.ShooterConstants.kShooterFollowTalonId);
    
    // PIDs
    PIDController mShooterPID = new PIDController(Constants.ShooterConstants.kShooterP, 
                                            Constants.ShooterConstants.kShooterI, 
                                            Constants.ShooterConstants.kShooterD);

    PIDController mHoodPID = new PIDController(Constants.ShooterConstants.kHoodP, 
                                                Constants.ShooterConstants.kHoodI, 
                                                Constants.ShooterConstants.kHoodD);

    // Gets hood angle from the limelight then sets the hood to the given angle
    // Returns true if the hood is withing the given angle
    public boolean adjustHood(double limelight_ticks){
        double hood_ticks = mHoodMotorTalon.getSelectedSensorPosition();
        double hood_pid = mHoodPID.calculate(hood_ticks, limelight_ticks);
        mHoodMotorTalon.setSelectedSensorPosition(hood_pid);

        if (hood_ticks > limelight_ticks - Constants.ShooterConstants.kHoodTargetRange
        || hood_ticks < limelight_ticks + Constants.ShooterConstants.kHoodTargetRange){
            return true;
        } else {
            return false;
        }
    }
    
    // gets speed of the motor from the limelight and sets the speed to the motor
    // returns true if within a certain velocity
    public boolean revShooter(double limelight_speed){
        double rev_speed = mShooterMainTalon.getSelectedSensorVelocity(0);
        double rev_pid = mShooterPID.calculate(rev_speed, limelight_speed);
        mShooterMainTalon.set(ControlMode.PercentOutput, rev_pid);

        if (rev_speed > limelight_speed - Constants.ShooterConstants.kShooterTargetRange 
        || rev_speed < limelight_speed + Constants.ShooterConstants.kShooterTargetRange){
            return true;
        } else {
            return false;
        }
    }
}
