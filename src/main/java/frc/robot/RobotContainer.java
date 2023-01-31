// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.CannonConstants;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.CannonSubsystem;
import frc.robot.subsystems.DriveSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems
 * commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final XboxController m_controller = new XboxController(OIConstants.kControllerPort);

  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final CannonSubsystem m_leftCannon = new CannonSubsystem(CannonConstants.kLeftRelayPort);
  private final CannonSubsystem m_rightCannon = new CannonSubsystem(CannonConstants.kRightRelayPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    configureBindings();

    // Command that drives with joysticks and boosts with right trigger.
    m_robotDrive.setDefaultCommand(
        new RunCommand(
            () -> {
              m_robotDrive.arcadeDrive(
                  scaleInput(MathUtil.applyDeadband(-m_controller.getLeftY(), OIConstants.kControllerDeadband)),
                  scaleInput(MathUtil.applyDeadband(-m_controller.getRightX(), OIConstants.kControllerDeadband)));
              m_robotDrive.setMaxOutput(
                  DriveConstants.kNormalCoefficient + m_controller.getRightTriggerAxis()
                      * (DriveConstants.kBoostCoefficient - DriveConstants.kNormalCoefficient));
            }, m_robotDrive));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the {@link Trigger#Trigger(java.util.function.BooleanSupplier)}
   * constructor with an arbitrary predicate, or via the named factories in
   * {@link edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses
   * for {@link CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller PS4}
   * controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick
   * Flight joysticks}.
   */
  private void configureBindings() {
    // Fires the left cannon when the A button is pressed (only if the left bumper
    // is held)
    new JoystickButton(m_controller, Button.kA.value).onTrue(
        new InstantCommand(() -> {
          if (m_controller.getLeftBumper()) {
            m_leftCannon.shoot();
          }
        }));

    // Fires the right cannon when the B button is pressed (only if the left bumper
    // is held)
    new JoystickButton(m_controller, Button.kB.value).onTrue(
        new InstantCommand(() -> {
          if (m_controller.getLeftBumper()) {
            m_rightCannon.shoot();
          }
        }));

    // Fires both cannons when the X button is pressed (only if the left bumper is
    // held)
    new JoystickButton(m_controller, Button.kX.value).onTrue(
        new InstantCommand(() -> {
          if (m_controller.getLeftBumper()) {
            m_leftCannon.shoot();
            m_rightCannon.shoot();
          }
        }));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }

  /**
   * Scales an input to allow for finer control at low speeds.
   *
   * @return scaled input
   */
  private double scaleInput(double input) {
    return Math.abs(input) * input;
  }
}