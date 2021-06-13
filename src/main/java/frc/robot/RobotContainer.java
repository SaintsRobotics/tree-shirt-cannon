// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.DrivetrainCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.CannonSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private final XboxController m_controller = new XboxController(0);

  // The robot's subsystems and commands are defined here...
  private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem(RobotMap.differentialDrive);
  private final CannonSubsystem m_leftCannon = new CannonSubsystem("Left Cannon", RobotMap.leftRelay);
  private final CannonSubsystem m_rightCannon = new CannonSubsystem("Right Cannon", RobotMap.rightRelay);

  private final DrivetrainCommand m_drivetrainCommand = new DrivetrainCommand(m_drivetrain,
      () -> m_controller.getY(Hand.kLeft), () -> m_controller.getX(Hand.kRight),
      () -> m_controller.getBumper(Hand.kRight));

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_drivetrain.setDefaultCommand(m_drivetrainCommand);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    final BooleanSupplier leftBumper = () -> m_controller.getBumper(Hand.kLeft);

    new JoystickButton(m_controller, Button.kA.value).whenPressed(new ShootCommand(m_leftCannon, leftBumper));
    new JoystickButton(m_controller, Button.kB.value).whenPressed(new ShootCommand(m_rightCannon, leftBumper));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
