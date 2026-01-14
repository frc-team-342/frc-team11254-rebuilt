// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.ResetMode;
import com.revrobotics.PersistMode;
import com.revrobotics.spark.SparkMax;
import static frc.robot.Constants.DriveConstants.*;

public class TankDrive extends SubsystemBase {
    private SparkMax frontLeft;
    private SparkMax frontRight;
    private SparkMax backLeft;
    private SparkMax backRight;
    private DifferentialDrive tankDrive;
    private SparkMaxConfig frontLeftConfig;
    private SparkMaxConfig frontRightConfig;
    private SparkMaxConfig backLeftConfig;
    private SparkMaxConfig backRightConfig;
  /** Creates a new TankDrive. */
  public TankDrive() {
    frontLeft = new SparkMax(FRONT_LEFT_MOTOR, MotorType.kBrushless);
    frontRight = new SparkMax(FRONT_RIGHT_MOTOR, MotorType.kBrushless);
    backLeft = new SparkMax(BACK_LEFT_MOTOR, MotorType.kBrushless);
    backRight = new SparkMax(BACK_RIGHT_MOTOR, MotorType.kBrushless);
    frontLeftConfig = new SparkMaxConfig();
    frontRightConfig = new SparkMaxConfig();
    backLeftConfig = new SparkMaxConfig();
    backRightConfig = new SparkMaxConfig();
    tankDrive = new DifferentialDrive(frontLeft, frontRight);
      

    frontLeftConfig
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(60)
      .inverted(true);

    frontRightConfig
      .idleMode(IdleMode.kBrake)
      .smartCurrentLimit(60);

    backLeftConfig.apply(frontLeftConfig).follow(FRONT_LEFT_MOTOR);
    backRightConfig.apply(frontRightConfig).follow(FRONT_RIGHT_MOTOR);

    frontLeft.configure(frontLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    frontRight.configure(frontRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    backLeft.configure(backLeftConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
    backRight.configure(backRightConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
  }


  public void drive(XboxController driver){
    tankDrive.arcadeDrive(MathUtil.applyDeadband(driver.getLeftY(), 0.05), MathUtil.applyDeadband(driver.getRightY(), 0.05));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
