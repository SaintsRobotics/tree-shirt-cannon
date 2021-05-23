// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

  /** Creates a new CannonSubsystem. */
  public CannonSubsystem(String name, Relay relay) {
    super(name);
    m_relay = relay;
    m_timer = new Timer();
    m_timer.start();
  }

  @Override
  public void periodic() {
    if (m_timer.get() > ON_DURATION * 2) {
      m_relay.set(OFF);
    } else if (m_timer.get() > ON_DURATION) {
      m_relay.set(CLOSE_VALVE);
    }

    SmartDashboard.putString(super.getName() + " State", m_relay.get().toString());
    SmartDashboard.putNumber(super.getName() + " Timer Value", m_timer.get());
  }

  @Override
  protected void initDefaultCommand() {

  }

  public void shoot() {
    m_timer.reset();
    m_relay.set(OPEN_VALVE);
  }
}
