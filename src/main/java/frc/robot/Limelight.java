package frc.robot;
import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;

import edu.wpi.first.math.controller.PIDController;


public class Limelight {
  // gets horizontal and vertical offsets from target
    PIDController mPIDControllerTurretSpeed = new PIDController(1, 0, 0);
    PIDController mPIDControllerDriveBaseSpeed = new PIDController(1, 0, 0);
    private PhotonCamera mLimeLight = new PhotonCamera("GloWorm");
    PhotonPipelineResult mResult = new PhotonPipelineResult();
    PhotonTrackedTarget mTarget = new PhotonTrackedTarget();
    // NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    // NetworkTableEntry tx = table.getEntry("tx");
    // NetworkTableEntry ty = table.getEntry("ty");
    // NetworkTableEntry tv = table.getEntry("tv");



    boolean isTarget(){
       if(mResult.hasTargets()){
           return true;
       }
       return false;
    }

    boolean getTarget(){
        mResult = mLimeLight.getLatestResult();
        if(isTarget()){
            mTarget = mResult.getBestTarget();
            return true;
        }
    return false;
    }

    double getTurretSpeed(){
        if(isTarget()){
        double set_pt = mPIDControllerTurretSpeed.calculate(getHorizontalOffset(),0);
        return set_pt;
        }
        else{
            return 0;
        }
    }

    double getShooterSpeed(){
        if(isTarget()){
            return getVerticalOffset() * Constants.Limelight.kShooterSpeedMult + Constants.Limelight.kShooterSpeedBase;
            }
            else{
                return 0;
            }
    }


    //VERY CONFUSED
    double getHoodPosition(){
        if(isTarget()){
            return getVerticalOffset() * Constants.Limelight.kShooterSpeedMult + Constants.Limelight.kShooterSpeedBase;
            }
            else{
                return 0;
            }
    }
    
    double getHorizontalOffset(){
        if(getTarget()){
            return mTarget.getYaw();
        }
        return 42069;
    }

    double getVerticalOffset(){
        if(getTarget()){
            return mTarget.getPitch();
        }
        return 42069;
    }

    boolean isAlligned(){
        if(getHorizontalOffset() < Constants.Limelight.kAlignDistance && getHorizontalOffset() > -Constants.Limelight.kAlignDistance){
            return true;
        }
        return false;
    }

}
//     public boolean checkLimelight(){
//         if (tv.getDouble(0.0) == 1) {
//             return true;
//         } else {
//             return false;
//         }
//     }

//     public double getHorizontalOffset(){
//         double x = tx.getDouble(77090054.7480306);
//         return x;
//     }

//     public double getVerticalOffset(){
//         double y = ty.getDouble(77090054.7480306);
//         return y;
//     }
// }
