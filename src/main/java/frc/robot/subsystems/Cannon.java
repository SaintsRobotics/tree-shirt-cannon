/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Cannon extends Subsystem {
private Relay m_relay;

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Cannon(Relay relay) {
    relay = this.m_relay;
  }

  @Override
  public void initDefaultCommand() {
    // setDefaultCommand(new MySpecialCommand());

    
  }

  public void openValve() {
    // if valve is already open, nothing happens
    

  }

  public void closeValve() {
    // if valve is already closed, nothing happens

  }


}
