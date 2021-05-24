// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.BooleanSupplier;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.subsystems.CannonSubsystem;

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
    super(cannon);
    m_cannon = cannon;
    m_safetySupplier = safetySupplier;
  }

  @Override
  protected void initialize() {
    // can only shoot if safety button is held
    if (m_safetySupplier.getAsBoolean()) {
      m_cannon.shoot();
    }
  }
}
