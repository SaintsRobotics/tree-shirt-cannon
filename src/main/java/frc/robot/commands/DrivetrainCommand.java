// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DrivetrainCommand extends CommandBase {
  private DrivetrainSubsystem m_drivetrain;

  /** Creates a new DrivetrainCommand. */
  public DrivetrainCommand(DrivetrainSubsystem drivetrain) {
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_drivetrain.arcadeDrive(-RobotContainer.m_controller.getRawAxis(1), RobotContainer.m_controller.getRawAxis(4));
  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.arcadeDrive(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
