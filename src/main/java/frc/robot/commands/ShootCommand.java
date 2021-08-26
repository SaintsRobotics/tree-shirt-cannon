// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.CannonConstants;
import frc.robot.subsystems.CannonSubsystem;

/** Opens, then closes the valve, causing the cannon to shoot. */
public class ShootCommand extends CommandBase {
  private CannonSubsystem m_cannon;
  private Timer m_timer;

  /**
   * Creates a new {@link ShootCommand}.
   * 
   * @param cannon The {@link CannonSubsystem} to use.
   */
  public ShootCommand(CannonSubsystem cannon) {
    m_cannon = cannon;
    addRequirements(m_cannon);

    m_timer = new Timer();
  }

  @Override
  public void initialize() {
    m_timer.reset();
    m_timer.start();
    m_cannon.set(CannonConstants.kOpenValue); // FIRE!
  }

  @Override
  public void execute() {
    // Closes the valve after 1 second has passed.
    if (m_timer.get() > CannonConstants.kOnDuration) {
      m_cannon.set(CannonConstants.kCloseValue);
    }
  }

  @Override
  public void end(boolean interrupted) {
    m_cannon.set(CannonConstants.kOffValue);
  }

  @Override
  public boolean isFinished() {
    // The command finished after 2 seconds have elapsed. (1 second after closing
    // the valve)
    return m_timer.get() > CannonConstants.kOnDuration * 2;
  }
}
