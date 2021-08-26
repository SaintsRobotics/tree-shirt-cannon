// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Subsystem that controls the drivetrain of the robot. */
public class DriveSubsystem extends SubsystemBase {
  private DifferentialDrive m_drive;

  /**
   * Creates a new {@link DriveSubsystem}.
   * 
   * @param differentialDrive the drivetrain of the robot
   */
  public DriveSubsystem(DifferentialDrive differentialDrive) {
    m_drive = differentialDrive;
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
    SmartDashboard.putNumber("Move Speed", fwd);
    SmartDashboard.putNumber("Rotate Speed", rot);
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more
   * slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
    SmartDashboard.putNumber("Max Output", maxOutput);
  }
}
