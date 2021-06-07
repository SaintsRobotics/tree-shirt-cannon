// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DrivetrainCommand extends CommandBase {
  private DrivetrainSubsystem m_drivetrain;

  private DoubleSupplier m_moveSpeed;
  private DoubleSupplier m_turnSpeed;
  private BooleanSupplier m_boost;

  /** Creates a new DrivetrainCommand. */
  public DrivetrainCommand(DrivetrainSubsystem drivetrain, DoubleSupplier moveSpeed, DoubleSupplier turnSpeed,
      BooleanSupplier boost) {
    m_drivetrain = drivetrain;
    addRequirements(m_drivetrain);

    m_moveSpeed = moveSpeed;
    m_turnSpeed = turnSpeed;
    m_boost = boost;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_drivetrain.arcadeDrive(m_moveSpeed.getAsDouble(), m_turnSpeed.getAsDouble(), m_boost.getAsBoolean());
  }

  @Override
  public void end(boolean interrupted) {
    m_drivetrain.arcadeDrive(0, 0, false);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
