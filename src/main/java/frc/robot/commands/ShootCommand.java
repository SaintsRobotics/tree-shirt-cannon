// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CannonSubsystem;

public class ShootCommand extends Command {
  private CannonSubsystem m_cannon;

  public ShootCommand(CannonSubsystem cannon) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    m_cannon = cannon;
    requires(m_cannon);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    m_cannon.Shoot();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
