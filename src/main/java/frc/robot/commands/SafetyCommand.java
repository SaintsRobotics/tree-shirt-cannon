// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;

/** Prevents a command from running if the condition is not met. */
public class SafetyCommand extends InstantCommand {
  private final BooleanSupplier m_safety;
  private final CommandBase m_command;

  /**
   * Creates a new {@link SafetyCommand}.
   * 
   * @param safety  Safety to prevent a command from being scheduled if
   *                {@link BooleanSupplier#getAsBoolean getAsBoolean}
   *                returns false.
   * @param command The command to schedule.
   */
  public SafetyCommand(BooleanSupplier safety, CommandBase command) {
    m_safety = safety;
    m_command = command;
  }

  @Override
  public void initialize() {
    if (m_safety.getAsBoolean()) {
      m_command.schedule();
    }
  }
}
