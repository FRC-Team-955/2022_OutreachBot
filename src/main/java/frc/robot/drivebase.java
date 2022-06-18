package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.Joystick;

public class drivebase {
    // variables
    private Joystick joy = new Joystick(0);
    private TalonSRX talonTopRight = new TalonSRX(3);
    private TalonSRX talonBotRight = new TalonSRX(4);
    private TalonSRX talonTopLeft = new TalonSRX(1);
    private TalonSRX talonBotLeft = new TalonSRX(2);
    DifferentialDrive diffDrive = new DifferentialDrive(talonTopRight, talonTopLeft);


    public void drive(){
        talonBotRight.set(ControlMode.Follower, 3);
        talonBotLeft.set(ControlMode.Follower, 1);
        diffDrive.arcadeDrive(joy.getRawAxis(1), joy.getRawAxis(4));
    }

    public double getLeftEncoderPos(){
        return talonTopLeft.getSelectedSensorPosition(0);
    }

    public double getRightEncoderPos(){
        return talonTopRight.getSelectedSensorPosition(0);
    }
}