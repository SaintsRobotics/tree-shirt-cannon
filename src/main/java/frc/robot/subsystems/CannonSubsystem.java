// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

/** Subsystem that controls a cannon of the robot. */
public class CannonSubsystem extends SubsystemBase {

  private Relay m_relay;
  private Timer m_timer;

  /**
   * Creates a new {@link CannonSubsystem}.
   * 
   * @param name  the name of the {@link CannonSubsystem}
   * @param relay the {@link Relay} that opens and closes the valve
   */
  public CannonSubsystem(String name, Relay relay) {
    setName(name);
    m_relay = relay;
    m_timer = new Timer();
    m_timer.start();
  }

  @Override
  public void periodic() {
    // closes the valve then turns off the relay after shooting
    if (m_timer.get() > Constants.Cannon.ON_DURATION * 2) {
      m_relay.set(Constants.Cannon.OFF);
    } else if (m_timer.get() > Constants.Cannon.ON_DURATION) {
      m_relay.set(Constants.Cannon.CLOSE_VALVE);
    }

    SmartDashboard.putString(getName() + " State", m_relay.get().toString());
    SmartDashboard.putNumber(getName() + " Timer Value", m_timer.get());
  }

  /** Opens and closes the valve which shoots a t-shirt. */
  public void shoot() {
    m_timer.reset();
    m_relay.set(Constants.Cannon.OPEN_VALVE);
  }
}
