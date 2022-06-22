package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;

public class Drivebase {
    // Talons
    private Joystick mJoy = new Joystick(0);
    private TalonSRX mTalonTopRight = new TalonSRX(Constants.DrivebaseConstants.kTalonTopRightId);
    private TalonSRX mTalonBotRight = new TalonSRX(Constants.DrivebaseConstants.kTalonBotRightId);
    private TalonSRX mTalonTopLeft = new TalonSRX(Constants.DrivebaseConstants.kTalonTopLeftId);
    private TalonSRX mTalonBotLeft = new TalonSRX(Constants.DrivebaseConstants.kTalonBotLeftId);
    private DifferentialDrive mDiffDrive = new DifferentialDrive(mTalonTopRight, mTalonTopLeft);

    public void drive(){
        mTalonBotRight.set(ControlMode.Follower, Constants.DrivebaseConstants.kTalonTopRightId);
        mTalonBotLeft.set(ControlMode.Follower, Constants.DrivebaseConstants.kTalonTopLeftId);
        mDiffDrive.arcadeDrive(mJoy.getRawAxis(1), mJoy.getRawAxis(4));
    }

    public double getLeftEncoderPos(){
        return mTalonTopLeft.getSelectedSensorPosition();
    }

    public double getRightEncoderPos(){
        return mTalonTopRight.getSelectedSensorPosition();
    }
}