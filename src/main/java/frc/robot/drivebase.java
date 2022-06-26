package frc.robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;

public class Drivebase {
    // Talons
    private Joystick mJoy = new Joystick(0);
    private WPI_TalonSRX mTalonTopRight = new WPI_TalonSRX(Constants.DrivebaseConstants.kTalonTopRightId);
    private WPI_TalonSRX mTalonBotRight = new WPI_TalonSRX(Constants.DrivebaseConstants.kTalonBotRightId);
    private WPI_TalonSRX mTalonTopLeft = new WPI_TalonSRX(Constants.DrivebaseConstants.kTalonTopLeftId);
    private WPI_TalonSRX mTalonBotLeft = new WPI_TalonSRX(Constants.DrivebaseConstants.kTalonBotLeftId);
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