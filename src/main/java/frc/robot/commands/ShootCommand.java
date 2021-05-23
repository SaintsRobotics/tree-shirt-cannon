// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.CannonSubsystem;

/** Add your docs here. */
public class ShootCommand extends InstantCommand {
  private CannonSubsystem m_cannon;

  /** Add your docs here. */
  public ShootCommand(CannonSubsystem cannon) {
    m_cannon = cannon;
    requires(m_cannon);
  }

  @Override
  protected void initialize() {
    m_cannon.shoot();
  }
}
