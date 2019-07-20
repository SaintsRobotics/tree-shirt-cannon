/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Cannon;

public class OpenValve extends Command {
  private BooleanSupplier m_saftey;
  private Cannon m_cannon;

  public OpenValve(Cannon cannon, BooleanSupplier safteySwitch) {
    m_saftey = safteySwitch;
    m_cannon = cannon;
    requires(m_cannon);
    setInterruptible(false);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (this.m_saftey.getAsBoolean()) {
      m_cannon.openValve();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

}
