// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Subsystem that controls the drivetrain of the robot. */
public class DrivetrainSubsystem extends SubsystemBase {
  private DifferentialDrive m_differentialDrive;

  private double m_moveSpeed;
  private double m_rotateSpeed;
  private boolean m_isBoosted;

  /**
   * Creates a new {@link DrivetrainSubsystem}.
   * 
   * @param differentialDrive the drivetrain of the robot
   */
  public DrivetrainSubsystem(DifferentialDrive differentialDrive) {
    m_differentialDrive = differentialDrive;
  }

  @Override
  public void periodic() {
    if (m_isBoosted) {
      m_moveSpeed *= Constants.Drivetrain.BOOST_COEFFICIENT;
      m_rotateSpeed *= Constants.Drivetrain.BOOST_COEFFICIENT;
    } else {
      m_moveSpeed *= Constants.Drivetrain.NORMAL_COEFFICIENT;
      m_rotateSpeed *= Constants.Drivetrain.NORMAL_COEFFICIENT;
    }
    m_differentialDrive.arcadeDrive(m_moveSpeed, m_rotateSpeed);

    SmartDashboard.putNumber("Move Speed", m_moveSpeed);
    SmartDashboard.putNumber("Rotate Speed", m_rotateSpeed);
    SmartDashboard.putBoolean("Is Boosted", m_isBoosted);
  }

  /**
   * Drives the robot using arcade drive.
   * 
   * @param moveSpeed   the speed the robot moves forward or backwards (range of
   *                    -1 to 1)
   * @param rotateSpeed the speed the robot rotates (range of -1 to 1)
   * @param isBoosted   whether the robot should drive with a faster boost mode
   */
  public void arcadeDrive(double moveSpeed, double rotateSpeed, boolean isBoosted) {
    m_moveSpeed = moveSpeed;
    m_rotateSpeed = rotateSpeed;
    m_isBoosted = isBoosted;
  }
}
