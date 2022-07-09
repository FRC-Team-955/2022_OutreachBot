package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
  private Drivebase mDrive = new Drivebase();
  private BallManager mBallManager = new BallManager();
  private Intake mIntake = new Intake();
  private Spindex mSpindex = new Spindex();
  private Turret mTurret = new Turret();

  //keep as joy no new name because it is tradition
  private Joystick mJoy0 = new Joystick(0);
  private Joystick mJoy1 = new Joystick(1);
  int intake_button_toggle = -1;
  int intake_extension_toggle = -1;
  int spindex_toggle = 1;

  @Override
  public void robotInit() {}

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {
    
    mTurret.alignTurret();
    //Joystick 0
    mDrive.drive();
    if(mJoy0.getRawAxis(3) > 0.75){
      mBallManager.shoot();
    }
    if(mJoy0.getRawButtonPressed(6)){
      mBallManager.defaultShoot();
    }

    //Joystick 1
    if(mJoy1.getRawButtonPressed(2)){
      intake_button_toggle = -intake_button_toggle;
    }
    if(intake_button_toggle == 1){
      mIntake.runIntake();
    }
    else{
      mIntake.stopIntake();
    }

    if(mJoy1.getRawAxis(3) > 0.75){
      intake_extension_toggle = -intake_extension_toggle;
    }
    if(intake_extension_toggle == 1){
      mIntake.extendIntake();
    }
    else{
      mIntake.retractIntake();
      intake_button_toggle = -1;
    }

    if(mJoy1.getRawButtonPressed(1)){
      spindex_toggle = -spindex_toggle;
    }
    if(spindex_toggle == 1){
      mSpindex.spinSpindex();
    }
    else{
      mSpindex.stopSpindex();
    }
    

  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
