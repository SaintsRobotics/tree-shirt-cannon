// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/** Subsystem that controls a single cannon of the robot. */
public class CannonSubsystem extends SubsystemBase {
  private Relay m_relay;

  /**
   * Creates a new {@link CannonSubsystem}.
   * 
   * @param relay The {@link Relay} that opens and closes the valve.
   */
  public CannonSubsystem(Relay relay) {
    m_relay = relay;
  }

  /**
   * Sets the value of the relay.
   * 
   * @param value The {@link Value} to set the relay to. (kOff, kOn, kReverse, or
   *              kForward)
   */
  public void set(Value value) {
    m_relay.set(value);
  }
}
