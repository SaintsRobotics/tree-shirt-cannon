// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.DrivetrainSubsystem;

public class DrivetrainCommand extends Command {
  private DrivetrainSubsystem m_drivetrain;

  public DrivetrainCommand(DrivetrainSubsystem drivetrain) {
    super(drivetrain);
    m_drivetrain = drivetrain;
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    m_drivetrain.arcadeDrive(-OI.xboxController.getRawAxis(OI.LEFT_STICK_Y),
        OI.xboxController.getRawAxis(OI.RIGHT_STICK_X));
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    m_drivetrain.arcadeDrive(0, 0);
  }

  @Override
  protected void interrupted() {
    end();
  }
}
