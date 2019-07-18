/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.CloseValve;

public class Cannon extends Subsystem {
  private static final double OPEN_DURATION = 1;
  private Relay m_relay;
  private State m_state;
  private Timer m_timer;
  private double m_relayOnTime;

  private enum State {
    START, OPENING, OPENED, CLOSING, CLOSED
  }

  public Cannon(Relay relay) {
    this.m_relay = relay;
    this.m_state = State.START;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CloseValve(this));
  }

  public void openValve() {
    switch (this.m_state) {
    case START:
      this.closeValve(); // if open is called before the *default command* runs, the valve won't open
      break;

    case OPENING:
      if (m_relayOnTime > OPEN_DURATION) {
        this.m_state = State.OPENED;
      } else {
        this.m_relay.set(Value.kForward);
      }
      break;

    case OPENED:

    }

  }

  public void closeValve() {
    // if valve is already closed, nothing happens

  }

}
