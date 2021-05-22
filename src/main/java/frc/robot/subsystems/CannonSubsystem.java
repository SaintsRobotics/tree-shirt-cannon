// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CannonSubsystem extends Subsystem {
  // the minimum number of seconds the relay needs to be powered to ensure it
  // becomes fully opened or closed
  private static final double ON_DURATION = 1;

  // Value.kForward closes the valve and Value.kReverse opens it
  // definitely not very intuitive
  private static final Value CLOSE_VALVE = Value.kForward;
  private static final Value OPEN_VALVE = Value.kReverse;
  private static final Value OFF = Value.kOff;

  private Relay m_relay;
  private Timer m_timer;

  /** Creates a new CannonSubsystem2. */
  public CannonSubsystem(Relay relay) {
    m_relay = relay;

    // close the valve when first constructed
    m_relay.set(CLOSE_VALVE);

    m_timer = new Timer();
    m_timer.start();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (m_timer.get() > ON_DURATION * 2) {
      m_relay.set(OFF);
    } else if (m_timer.get() > ON_DURATION) {
      m_relay.set(CLOSE_VALVE);
    }
  }

  @Override
  protected void initDefaultCommand() {

  }

  public void Shoot() {
    m_timer.reset();
    m_relay.set(OPEN_VALVE);
  }
}
