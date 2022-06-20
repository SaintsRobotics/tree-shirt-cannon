// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.CannonConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.SafetyCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.CannonSubsystem;
import frc.robot.subsystems.DriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final XboxController m_controller = new XboxController(OIConstants.kControllerPort);

  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final CannonSubsystem m_leftCannon = new CannonSubsystem(CannonConstants.kLeftRelayPort);
  private final CannonSubsystem m_rightCannon = new CannonSubsystem(CannonConstants.kRightRelayPort);

  // A supplier that returns whether the left bumper is currently held. The left
  // bumper acts as a safety that prevents the cannons from firing when not held
  // down
  private final BooleanSupplier m_safetySupplier = () -> new JoystickButton(m_controller, Button.kLeftBumper.value)
      .get();

  // Commands to shoot the left and right cannons, respectively, but only if the
  // left bumper is held.
  private final SafetyCommand m_leftShootCommand = new SafetyCommand(m_safetySupplier, new ShootCommand(m_leftCannon));
  private final SafetyCommand m_rightShootCommand = new SafetyCommand(m_safetySupplier,
      new ShootCommand(m_rightCannon));

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // Subsystems can not have the same name in the simulator.
    m_leftCannon.setName("Left Cannon");
    m_rightCannon.setName("Right Cannon");

    m_robotDrive.setDefaultCommand(
            new DriveCommand(m_robotDrive, () -> -m_controller.getLeftY(), () -> m_controller.getRightX(), () -> m_controller.getRightTriggerAxis()));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Fires the left cannon when the A button is pressed (only if the left bumper
    // is held)
    new JoystickButton(m_controller, Button.kA.value).whenPressed(m_leftShootCommand);

    // Fires the right cannon when the B button is pressed (only if the left bumper
    // is held)
    new JoystickButton(m_controller, Button.kB.value).whenPressed(m_rightShootCommand);

    // Fires both cannons when the X button is pressed (only if the left bumper is
    // held)
    new JoystickButton(m_controller, Button.kX.value).whenPressed(m_leftShootCommand).whenPressed(m_rightShootCommand);
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
