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
    // The timer defaults to 0 when the robot starts so it needs to be greater than
    // 0 before we should shoot.
    if (m_timer.get() == 0) {
      m_relay.set(CannonConstants.kOffValue);
    }
    // Opens the valve and fires the cannon.
    else if (m_timer.get() < CannonConstants.kOnDuration) {
      m_relay.set(CannonConstants.kOpenValue);
    }
    // Closes the valve after 1 second has passed.
    else if (m_timer.get() < 2 * CannonConstants.kOnDuration) {
      m_relay.set(CannonConstants.kCloseValue);
    }
    // Resets the timer 1 second after the valve closes, which turns off the relay.
    else {
      m_timer.stop();
      m_timer.reset();
    }
  }

  /** Shoots the cannon by opening and then closing the valve. */
  public void shoot() {
    m_timer.reset();
    m_timer.start();
  }
}
