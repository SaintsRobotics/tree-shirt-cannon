
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.CannonConstants;
import frc.robot.subsystems.CannonSubsystem;

/**
 * Closes the valve by running the relay in reverse. To be called when the robot
 * is first enabled to ensure that the valve is closed before firing.
 */
public class CloseValveCommand extends CommandBase {
  private CannonSubsystem m_cannon;
  private Timer m_timer;

  /** Creates a new {@link CloseValveCommand}. */
  public CloseValveCommand(CannonSubsystem cannon) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_cannon = cannon;
    addRequirements(m_cannon);

    m_timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Starting the timer does NOT zero it.
    m_timer.reset();
    m_timer.start();
    m_cannon.set(CannonConstants.kCloseValue);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_cannon.set(CannonConstants.kOffValue);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_timer.get() > CannonConstants.kOnDuration;
  }
}
