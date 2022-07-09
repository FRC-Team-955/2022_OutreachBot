package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;

public class Shooter {

    public Shooter(){
        mShooterFollowTalon.follow(mShooterMainTalon);
    }
    
    // Talons
    WPI_TalonSRX mHoodMotorTalon = new WPI_TalonSRX(Constants.ShooterConstants.kHoodMotorTalonId);
    WPI_TalonSRX mShooterMainTalon = new WPI_TalonSRX(Constants.ShooterConstants.kShooterMainTalonId);
    WPI_TalonSRX mShooterFollowTalon = new WPI_TalonSRX(Constants.ShooterConstants.kShooterFollowTalonId);
    
    // Limit Switch
    DigitalInput mTopLimitSwitch = new DigitalInput(Constants.ShooterConstants.kTopLimitSwitchId);
    DigitalInput mBotLimitSwitch = new DigitalInput(Constants.ShooterConstants.kBotLimitSwitchId);

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
        if (limelight_ticks >= 100){
            limelight_ticks = 100;
        } else if (limelight_ticks <= 0){
            limelight_ticks = 0;
        }

        double hood_ticks = mHoodMotorTalon.getSelectedSensorPosition();
        double hood_pid = mHoodPID.calculate(hood_ticks, limelight_ticks);
        mHoodMotorTalon.setVoltage(hood_pid);

        if (mBotLimitSwitch.get()){
            mHoodMotorTalon.setSelectedSensorPosition(0);
        } else if (mTopLimitSwitch.get()){
            mHoodMotorTalon.setSelectedSensorPosition(100);
        }

        if (hood_ticks > limelight_ticks - Constants.ShooterConstants.kHoodTargetRange
        || hood_ticks < limelight_ticks + Constants.ShooterConstants.kHoodTargetRange){
            return true;
        } else {
            return false;
        }
    }
    
    // Gets speed of the motor from the limelight and sets the speed to the motor
    // Returns true if within a certain velocity
    public boolean revShooter(double limelight_speed){
        double rev_speed = mShooterMainTalon.getSelectedSensorVelocity();
        double rev_pid = mShooterPID.calculate(rev_speed, limelight_speed);
        mShooterMainTalon.setVoltage(Constants.ShooterConstants.kShooterF * limelight_speed + rev_pid);

        if (rev_speed > limelight_speed - Constants.ShooterConstants.kShooterTargetRange 
        || rev_speed < limelight_speed + Constants.ShooterConstants.kShooterTargetRange){
            return true;
        } else {
            return false;
        }
    }
}
