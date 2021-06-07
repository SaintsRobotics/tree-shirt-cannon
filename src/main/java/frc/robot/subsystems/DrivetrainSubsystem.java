// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class DrivetrainSubsystem extends SubsystemBase {
  private static final double SPEED_REDUCER = 0.75;
  private static final double BOOST_COEFFICIENT = .9;

  private DifferentialDrive m_differentialDrive;
  private double m_moveSpeed;
  private double m_rotateSpeed;

  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem(SpeedController leftMotor, SpeedController rightMotor) {
    m_differentialDrive = new DifferentialDrive(leftMotor, rightMotor);

  }

  @Override
  public void periodic() {
    if (!RobotContainer.m_controller.getRawButton(6) && !(RobotContainer.m_controller.getRawAxis(3) > 0.75)) {
      m_moveSpeed *= SPEED_REDUCER;
      m_rotateSpeed *= SPEED_REDUCER;
    } else {
      m_moveSpeed *= BOOST_COEFFICIENT;
      m_rotateSpeed *= BOOST_COEFFICIENT;
    }

    SmartDashboard.putNumber("move speed", m_moveSpeed);
    SmartDashboard.putNumber("rotate speed", m_rotateSpeed);
    m_differentialDrive.arcadeDrive(m_moveSpeed, m_rotateSpeed);
  }

  public void arcadeDrive(double moveSpeed, double rotateSpeed) {
    m_moveSpeed = moveSpeed;
    m_rotateSpeed = rotateSpeed;
  }
}
