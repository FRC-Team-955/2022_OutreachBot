package frc.robot;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Spindex {
    // Talons
    TalonSRX mSpinnerTalon = new TalonSRX(0);
    TalonSRX mLowerShooterTalon = new TalonSRX(0);
    TalonSRX mUpperShooterTalon = new TalonSRX(0);

    // Spins the spindex
    public void spinSpindex() {
        mSpinnerTalon.set(ControlMode.PercentOutput, .5);
    }

    // Stops spinning the spindex
    public void stopSpindex() {
        mSpinnerTalon.set(ControlMode.PercentOutput, 0); 
    }

    // Runs the balls into the shooter
    public void runToShooter() {
        mLowerShooterTalon.set(ControlMode.PercentOutput, -.5);
        mUpperShooterTalon.set(ControlMode.PercentOutput, .5);
    }

    // Stops the balls from going into the shooter
    public void stopToShooter() {
        mLowerShooterTalon.set(ControlMode.PercentOutput, 0);
        mUpperShooterTalon.set(ControlMode.PercentOutput, 0);
    }
}
