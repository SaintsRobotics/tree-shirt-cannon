// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CannonConstants;

/** Subsystem that controls a single cannon of the robot. */
public class CannonSubsystem extends SubsystemBase {
  private final Relay m_relay;
  private final Timer m_timer = new Timer();

  /**
   * Creates a new {@link CannonSubsystem}.
   * 
   * @param channel The channel of the {@link Relay}.
   */
  public CannonSubsystem(int channel) {
    m_relay = new Relay(channel);
  }

  @Override
  public void periodic() {
    if (m_timer.get() < CannonConstants.kOnDuration) {
      m_relay.set(CannonConstants.kOpenValue); // FIRE!
    }
    // Closes the valve after 1 second has passed.
    else if (m_timer.get() < 2 * CannonConstants.kOnDuration) {
      m_relay.set(CannonConstants.kCloseValue);
    }
    // Turns the relay off 1 second after the valve closes.
    else {
      m_relay.set(CannonConstants.kOffValue);
    }
  }

  public void shoot() {
    m_timer.reset();
    m_timer.start();
  }
}
