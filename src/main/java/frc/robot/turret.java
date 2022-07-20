package frc.robot;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.SerialPort.Port;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

public class Turret {
    // controlls rotation of the shooter
    PIDController mPid = new PIDController(1, 0, 0);
    AHRS mNavX = new AHRS(Port.kMXP); 
    Limelight mLimelight = new Limelight();
    TalonSRX mTalon = new TalonSRX(0);
    Drivebase mDrivebase = new Drivebase();
    DifferentialDriveOdometry mOdometry = new DifferentialDriveOdometry(new Rotation2d(13), new Pose2d(12, 24, new Rotation2d(0)) );
    boolean is_turning_360 = false;
    boolean is_turning_0 = false;


    public void updateOdometry(){
        mOdometry.update(new Rotation2d(mNavX.getAngle()), mDrivebase.getLeftEncoderPos(), mDrivebase.getRightEncoderPos());
    }

    public void go360(){
            double speed = mPid.calculate(getTurretAngle(), 360);
            if (getTurretAngle() <= 360 && getTurretAngle() >= 0) {
                mTalon.set(ControlMode.PercentOutput, speed);
        }
    }

    public void go0(){
        double speed = mPid.calculate(getTurretAngle(), 0);
        if (getTurretAngle() <= 360 && getTurretAngle() >= 0) {
            mTalon.set(ControlMode.PercentOutput, speed);
        }
    }
    
    public double getTurretAngle(){
        return mTalon.getSelectedSensorPosition()/Constants.TurretConstants.kTicksToDegree;
    }

    public void alignTurret(){

        if(is_turning_0){
            go0();
        }
        else if(getTurretAngle() < 10){
            is_turning_0 = false;
        }
        if(is_turning_360){
            go360();
        }
        else if(getTurretAngle() > 350){
            is_turning_360 = false;
        }
        if (mLimelight.isTarget()){
            if(mLimelight.getHorizontalOffset() + getTurretAngle() >= 360){
                is_turning_0 = true;
            }
            else if(mLimelight.getHorizontalOffset() + getTurretAngle() <= 0){
                is_turning_360 = true;
            }
            else{
            double speed = mPid.calculate(mLimelight.getHorizontalOffset(), 0);
            mTalon.set(ControlMode.PercentOutput, speed);
            }
        } else {
            double robot_to_goal_angle = 0;
            double robot_Y = mOdometry.getPoseMeters().getY();
            double robot_X = mOdometry.getPoseMeters().getX();
            double true_turret_angle = (getTurretAngle() + mNavX.getAngle()) % 360;
            
            //add check for 0's
            if(robot_Y < 0 && robot_X < 0){
                robot_to_goal_angle = Math.atan(robot_Y / robot_X);
            }
            else if(robot_Y > 0 && robot_X > 0){
                robot_to_goal_angle = Math.atan(robot_Y / robot_X) + 180;
            }
            else if(robot_Y > 0 && robot_X < 0){
                robot_to_goal_angle = 360 - Math.atan(robot_Y / robot_X);
            }
            else if(robot_Y < 0 && robot_X > 0){
                robot_to_goal_angle = 180 - Math.atan(robot_Y / robot_X);
            }
            double speed = mPid.calculate(true_turret_angle, robot_to_goal_angle);
            mTalon.set(ControlMode.PercentOutput, speed);
          /*  double robotX = 2;
            double robotY = 4;
            double goalX = 5;
            double goalY = 2.5;
            double adj = robotX - goalX;
            double opp = robotY - goalY;
            double theta = Math.atan(opp / adj);
            double angleToTurn = */
        }
    }

    public void setDesiredAngle(double setPointDeg, double currentPos){
        double speed = mPid.calculate(currentPos, setPointDeg % 360);
        if (getTurretAngle() <= 360 && getTurretAngle() >= 0) {
            mTalon.set(ControlMode.PercentOutput, speed);
        }
    }

    public boolean isOnTarget(double setPointDeg, double currentPos){
        if (currentPos <= setPointDeg + 10 && currentPos >= setPointDeg - 10) {
            return true;
        } else {
            return false;
        }
    }

    // public double getAngle(){
    //  return 
    // }

    public void stop(){
        mTalon.set(ControlMode.PercentOutput, 0);
    }

}
