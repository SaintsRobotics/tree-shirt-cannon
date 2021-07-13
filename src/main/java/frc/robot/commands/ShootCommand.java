// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.CannonConstants;
import frc.robot.subsystems.CannonSubsystem;

public class ShootCommand extends CommandBase {
  private CannonSubsystem m_cannon;
  private Timer m_timer;

  /** Creates a new {@link ShootCommand}. */
  public ShootCommand(CannonSubsystem cannon) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_cannon = cannon;
    addRequirements(m_cannon);

    m_timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // opens the valve, closes the valve, then turns off the relay after shooting
    if (m_timer.get() > CannonConstants.kOnDuration) {
      System.out.println("Execute");
      m_cannon.set(CannonConstants.kCloseValue);
      SmartDashboard.putNumber("timer", m_timer.get());
    } else {
      m_cannon.set(CannonConstants.kOpenValue);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_cannon.set(CannonConstants.kOffValue);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return m_timer.get() > CannonConstants.kOnDuration * 2;
  }
}
