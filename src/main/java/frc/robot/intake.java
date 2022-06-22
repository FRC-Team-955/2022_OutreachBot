package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class intake {

    private TalonSRX mTalonExtend = new TalonSRX(3);
    private TalonSRX mTalonRunIntake = new TalonSRX(4);

    public void extendIntake(){
        boolean isSet = false;
        if(mTalonExtend.getOutputCurrent() < 5 && isSet == false){
            mTalonExtend.set(ControlMode.PercentOutput, 0.5);
        }
        else{
            mTalonExtend.set(ControlMode.PercentOutput, 0.1);
            isSet = true;
        }
    }
    public void retractIntake(){
        if(mTalonExtend.getOutputCurrent() < 5){
            mTalonExtend.set(ControlMode.PercentOutput, -0.5);
        }
        else{
            mTalonExtend.set(ControlMode.PercentOutput, -0.1);
        }
    }
    public void runIntake(){
        mTalonRunIntake.set(ControlMode.PercentOutput, 0.5);
    }
}
