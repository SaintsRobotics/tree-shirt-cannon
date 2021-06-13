// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Subsystem that controls a cannon of the robot. */
public class CannonSubsystem extends SubsystemBase {
  private Relay m_relay;

  /**
   * Creates a new {@link CannonSubsystem}.
   * 
   * @param name  the name of the {@link CannonSubsystem}
   * @param relay the {@link Relay} that opens and closes the valve
   */
  public CannonSubsystem(String name, Relay relay) {
    setName(name);
    m_relay = relay;
  }

  @Override
  public void periodic() {
    SmartDashboard.putString(getName() + " State", m_relay.get().toString());
  }

  public void set(Value value) {
    m_relay.set(value);
  }
}
