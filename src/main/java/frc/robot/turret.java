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
    PIDController pid = new PIDController(1, 0, 0);
    AHRS mNavX = new AHRS(Port.kMXP); 
    Limelight limelight = new Limelight();
    TalonSRX talon = new TalonSRX(0);
    Drivebase drivebase = new Drivebase();
    DifferentialDriveOdometry mOdometry = new DifferentialDriveOdometry(new Rotation2d(13), new Pose2d(12, 24, new Rotation2d(0)) );
    boolean is_turning_360 = false;
    boolean is_turning_0 = false;


    public void updateOdometry(){
        mOdometry.update(new Rotation2d(mNavX.getAngle()), drivebase.getLeftEncoderPos(), drivebase.getRightEncoderPos());
    }

    public void go360(){
            double speed = pid.calculate(getTurretAngle(), 360);
            if (getTurretAngle() <= 360 && getTurretAngle() >= 0) {
                talon.set(ControlMode.PercentOutput, speed);
        }
    }

    public void go0(){
        double speed = pid.calculate(getTurretAngle(), 0);
        if (getTurretAngle() <= 360 && getTurretAngle() >= 0) {
            talon.set(ControlMode.PercentOutput, speed);
        }
    }
    
    public double getTurretAngle(){
        return talon.getSelectedSensorPosition()/Constants.TurretConstants.kTicksToDegree;
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
        if (limelight.isTarget()){
            if(limelight.getHorizontalOffset() + getTurretAngle() >= 360){
                is_turning_0 = true;
            }
            else if(limelight.getHorizontalOffset() + getTurretAngle() <= 0){
                is_turning_360 = true;
            }
            else{
            double speed = pid.calculate(limelight.getHorizontalOffset(), 0);
            talon.set(ControlMode.PercentOutput, speed);
            }
        } else {
            double robot_to_goal_angle = 0;
            double robotY = mOdometry.getPoseMeters().getY();
            double robotX = mOdometry.getPoseMeters().getX();
            double true_turret_angle = (getTurretAngle() + mNavX.getAngle()) % 360;
            
            //add check for 0's
            if(robotY < 0 && robotX < 0){
                robot_to_goal_angle = Math.atan(robotY / robotX);
            }
            else if(robotY > 0 && robotX > 0){
                robot_to_goal_angle = Math.atan(robotY / robotX) + 180;
            }
            else if(robotY > 0 && robotX < 0){
                robot_to_goal_angle = 360 - Math.atan(robotY / robotX);
            }
            else if(robotY < 0 && robotX > 0){
                robot_to_goal_angle = 180 - Math.atan(robotY / robotX);
            }
            double speed = pid.calculate(true_turret_angle, robot_to_goal_angle);
            talon.set(ControlMode.PercentOutput, speed);
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
        double speed = pid.calculate(currentPos, setPointDeg % 360);
        if (getTurretAngle() <= 360 && getTurretAngle() >= 0) {
            talon.set(ControlMode.PercentOutput, speed);
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
        talon.set(ControlMode.PercentOutput, 0);
    }

}
