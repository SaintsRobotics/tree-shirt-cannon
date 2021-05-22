// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CannonSubsystem;

public class ShootCommand extends Command {
  private CannonSubsystem m_cannon;

  public ShootCommand(CannonSubsystem cannon) {
    m_cannon = cannon;
    requires(m_cannon);
  }

  @Override
  protected void initialize() {
    m_cannon.Shoot();
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
