package frc.robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;


public class Limelight {
  // gets horizontal and vertical offsets from target
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry tv = table.getEntry("tv");

    public boolean checkLimelight(){
        if (tv.getDouble(0.0) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public double getHorizontalOffset(){
        double x = tx.getDouble(77090054.7480306);
        return x;
    }

    public double getVerticalOffset(){
        double y = ty.getDouble(77090054.7480306);
        return y;
    }
}
