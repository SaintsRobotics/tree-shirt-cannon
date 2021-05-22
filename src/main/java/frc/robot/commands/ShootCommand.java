// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CannonSubsystem;

public class ShootCommand extends Command {
  private CannonSubsystem m_cannon;

  /** Creates a new ShootCommand. */
  public ShootCommand(CannonSubsystem cannon) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_cannon = cannon;
    requires(m_cannon);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_cannon.Shoot();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  // @Override
  // public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
