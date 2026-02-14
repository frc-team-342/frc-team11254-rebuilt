// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.PersistMode;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.SparkClosedLoopController;
import static frc.robot.Constants.climbconstants.*;
import static frc.robot.Constants.shooterConstants.GEAR_RATIO;

public class climber extends SubsystemBase {
  private SparkMax climbMotorRight;
  private SparkMax climbMotorLeft;
  private SparkMaxConfig climbMotorRightConfig;
  private SparkMaxConfig climbMotorLeftConfig;
  private SparkClosedLoopController sparkControl;
  private RelativeEncoder climbEncoder;
  private SparkClosedLoopController climbPID;
  /** Creates a new climber. */
  public climber() {
    climbMotorRight = new SparkMax(RIGHTCLIMB, MotorType.kBrushed);
    climbMotorLeft = new SparkMax(LEFTCLIMB, MotorType.kBrushed);

    climbMotorLeftConfig = new SparkMaxConfig();
    climbMotorRightConfig = new SparkMaxConfig();
    climbPID = climbMotorRight.getClosedLoopController();

    climbEncoder = climbMotorRight.getEncoder();

    climbMotorLeftConfig
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(30)
      .inverted(true);
    climbMotorRightConfig
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(30);
    climbMotorRightConfig.closedLoop.pid(.1,0,.1).outputRange(-1, 1);//range for output still needs to be found
      

    climbMotorRight.configure(climbMotorRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    climbMotorLeft.configure(climbMotorLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);

    climbMotorLeftConfig.apply(climbMotorRightConfig).follow(climbMotorRight);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void climbPosition(double rightposition){
    sparkControl.setSetpoint(rightposition, ControlType.kPosition);
  }
  double getClimbPosition(){
    return climbEncoder.getPosition();
  }
  public void cliberSpeed(double climbSpeed){
    climbMotorRight.set(climbSpeed);
  }
  public void climbup(){
    climbPID.setSetpoint(CLIMBERUP, ControlType.kPosition);
  }
  public void climbdown(){
    climbPID.setSetpoint(CLIMBDOWN, ControlType.kPosition);
  }
}//redo code based on elavator removing the funlle code
