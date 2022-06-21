// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

/** Subsystem that controls the drivetrain of the robot. */
public class DriveSubsystem extends SubsystemBase {
  private final DifferentialDrive m_drive = new DifferentialDrive(
      new MotorControllerGroup(
          new WPI_TalonSRX(DriveConstants.kLeftMotor1Port), new WPI_TalonSRX(DriveConstants.kLeftMotor2Port)),
      new MotorControllerGroup(
          new WPI_TalonSRX(DriveConstants.kRightMotor1Port), new WPI_TalonSRX(DriveConstants.kRightMotor2Port)));

  private double m_fwd, m_rot, m_maxOutput;

  /**
   * Creates a new {@link DriveSubsystem}.
   */
  public DriveSubsystem() {
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_fwd = fwd;
    m_rot = rot;
    m_drive.arcadeDrive(m_fwd, m_rot);
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more
   * slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_maxOutput = maxOutput;
    m_drive.setMaxOutput(m_maxOutput);
  }

  @Override
  public void initSendable(SendableBuilder builder) {
    super.initSendable(builder);
    builder.addDoubleProperty("Move Speed", () -> m_fwd, null);
    builder.addDoubleProperty("Rotation Speed", () -> m_rot, null);
    builder.addDoubleProperty("Max Output", () -> m_maxOutput, null);
  }
}
