// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.PersistMode;
import com.revrobotics.ResetMode;
import com.revrobotics.spark.*;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.*;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.XboxController;
import static frc.robot.Constants.shooterConstants.*;

public class Shooter extends SubsystemBase {
  private SparkMax motor;
  private SparkMaxConfig motorconfig;
  
  /** Creates a new Shooter. */
  public Shooter() {
    motor = new SparkMax(SHOOTER, MotorType.kBrushless);
    motorconfig = new SparkMaxConfig();

    motorconfig
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(60);
      motor.configure(motorconfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
