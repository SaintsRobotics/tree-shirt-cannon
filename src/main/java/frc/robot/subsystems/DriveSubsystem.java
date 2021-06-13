// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Subsystem that controls the drivetrain of the robot. */
public class DriveSubsystem extends SubsystemBase {
  private DifferentialDrive m_differentialDrive;

  /**
   * Creates a new {@link DriveSubsystem}.
   * 
   * @param differentialDrive the drivetrain of the robot
   */
  public DriveSubsystem(DifferentialDrive differentialDrive) {
    m_differentialDrive = differentialDrive;
  }

  @Override
  public void periodic() {
  }

  /**
   * Drives the robot using arcade drive.
   * 
   * @param moveSpeed   the speed the robot moves forward or backwards (range of
   *                    -1 to 1)
   * @param rotateSpeed the speed the robot rotates (range of -1 to 1)
   * @param isBoosted   whether the robot should drive with a faster boost mode
   */
  public void arcadeDrive(double moveSpeed, double rotateSpeed) {
    m_differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
    SmartDashboard.putNumber("Move Speed", moveSpeed);
    SmartDashboard.putNumber("Rotate Speed", rotateSpeed);
  }
}
