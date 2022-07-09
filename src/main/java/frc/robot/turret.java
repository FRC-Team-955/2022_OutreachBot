package frc.robot;
import edu.wpi.first.math.controller.PIDController;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Turret {
    // controlls rotation of the shooter
    PIDController pid = new PIDController(1, 0, 0);
    Limelight limelight = new Limelight();
    TalonSRX talon = new TalonSRX(0);
   

    public void alignTurret(){
        if (limelight.isTarget()){
        double speed = pid.calculate(limelight.getHorizontalOffset(), 0);
        talon.set(ControlMode.PercentOutput, speed);
        } else {
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
        double speed = pid.calculate(setPointDeg % 360, currentPos);
        if (talon.getSelectedSensorPosition(0) <= 1000 || talon.getSelectedSensorPosition(0) <= 0) {
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
