// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends CommandBase {
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_fwd, m_rot, m_boost;

  /** Creates a new {@link DriveCommand}. */
  public DriveCommand(DriveSubsystem drive, DoubleSupplier fwd, DoubleSupplier rot, DoubleSupplier boost) {
    m_drive = drive;
    addRequirements(m_drive);

    m_fwd = fwd;
    m_rot = rot;
    m_boost = boost;
  }

  @Override
  public void execute() {
    m_drive.arcadeDrive(m_fwd.getAsDouble(), m_rot.getAsDouble());

    m_drive.setMaxOutput(DriveConstants.kNormalCoefficient
        + (m_boost.getAsDouble() * (DriveConstants.kBoostCoefficient - DriveConstants.kNormalCoefficient)));
  }
}
