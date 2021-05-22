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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.CloseValve;

public class Cannon extends Subsystem {
  private static final double ON_DURATION = 1; // the minimum number of seconds the relay needs to be powered to ensure
                                               // it becomes fully opened or closed

  private String m_name;
  private Relay m_relay;
  private State m_state;
  private Timer m_timer = new Timer();
  private double m_relayStartTime; // the time at which the relay was turned to forward or reverse (not off)

  // Value.kForward closes the valve and Value.kReverse opens it
  // definitely not very intuitive
  private Value m_closeValve = Value.kForward;
  private Value m_openValve = Value.kReverse;
  private Value m_off = Value.kOff;

  private enum State {
    START, OPENING, OPENED, CLOSING, CLOSED
  }
  // set the environment to the requirements for that state, then set the state
  // itself

  public Cannon(String name, Relay relay) {
    this.m_name = name;
    this.m_relay = relay;
    this.m_timer.start();
    this.m_state = State.START;
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CloseValve(this));
  }

  // remember, this is being called continuously as long as the button is being
  // held
  public void openValve() {
    switch (this.m_state) {
      case START: // in case you enable the bot while holding down the fire button
        this.closeValve(); // if open is called before the *default command* runs, the valve won't open
        break;

      case OPENING:
        if (this.m_timer.get() - this.m_relayStartTime > ON_DURATION) {
          this.m_relay.set(m_off);
          this.m_state = State.OPENED;
        }
        break;

      case OPENED:
        break;

      case CLOSING:
        this.m_relay.set(m_off); // should turn relay off before switching directions. (deduced from tshirtcannon
                                 // repo)
        this.m_relay.set(m_openValve);
        this.m_relayStartTime = this.m_timer.get();
        this.m_state = State.OPENING;
        break;

      case CLOSED:
        this.m_relay.set(m_openValve);
        this.m_relayStartTime = this.m_timer.get();
        this.m_state = State.OPENING;
        break;
    }

  }

  public void closeValve() {
    switch (this.m_state) {
      case START: // same code as case OPENED because we're assuming that the valve is opened when
                  // it first starts
        this.m_relay.set(m_closeValve);
        this.m_relayStartTime = this.m_timer.get();
        this.m_state = State.CLOSING;
        break;

      case OPENING:
        if (this.m_timer.get() - this.m_relayStartTime > ON_DURATION) {
          this.m_relay.set(m_off);
          this.m_state = State.OPENED;
        }
        break;

      case OPENED:
        this.m_relay.set(m_closeValve);
        this.m_relayStartTime = this.m_timer.get();
        this.m_state = State.CLOSING;
        break;

      case CLOSING:
        if (this.m_timer.get() - this.m_relayStartTime > ON_DURATION) {
          this.m_relay.set(m_off);
          this.m_state = State.CLOSED;
        }
        break;

      case CLOSED:
        break;

    }
  }

  public void telemetry() {
    SmartDashboard.putString(this.m_name + " relay state", this.m_relay.get().name());
    SmartDashboard.putNumber(this.m_name + " timer", this.m_timer.get());
    SmartDashboard.putString(this.m_name + " cannon state", this.m_state.name());
  }

}
