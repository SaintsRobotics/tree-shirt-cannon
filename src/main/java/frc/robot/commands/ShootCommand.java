// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.CannonSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
/** Shoots the {@link CannonSubsystem}. */
public class ShootCommand extends InstantCommand {
  private CannonSubsystem m_cannon;
  private BooleanSupplier m_safetySupplier;

  /**
   * Creates a new {@link ShootCommand}.
   * 
   * @param cannon         the {@link CannonSubsystem} this command requires
   * @param safetySupplier the safety button that prevents the
   *                       {@link CannonSubsystem} from firing when not held
   */
  public ShootCommand(CannonSubsystem cannon, BooleanSupplier safetySupplier) {
    m_cannon = cannon;
    addRequirements(m_cannon);
    m_safetySupplier = safetySupplier;
  }

  @Override
  public void initialize() {
    // can only shoot if safety button is held
    if (m_safetySupplier.getAsBoolean()) {
      m_cannon.shoot();
    }
  }
}
