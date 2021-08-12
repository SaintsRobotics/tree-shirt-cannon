// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.RobotMap.CannonHardware;
import frc.robot.RobotMap.DriveHardware;
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
  // The robot's subsystems and commands are defined here...
  private final DriveSubsystem m_robotDrive = new DriveSubsystem(DriveHardware.differentialDrive);
  private final CannonSubsystem m_leftCannon = new CannonSubsystem("Left Cannon", CannonHardware.leftRelay);
  private final CannonSubsystem m_rightCannon = new CannonSubsystem("Right Cannon", CannonHardware.rightRelay);
  
  private final XboxController m_controller = new XboxController(OIConstants.kControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new RunCommand(
            () -> m_robotDrive.arcadeDrive(
                -m_controller.getY(Hand.kLeft),
                m_controller.getX(Hand.kRight)),
                m_robotDrive));
  }

  /**
   * Sets both left and right valves to be closed. Call this method when teleop
   * begins to make sure valves are closed on start.
   */
  public void initializeCannons() {
    // new CloseValveCommand(m_rightCannon).schedule();
    // new CloseValveCommand(m_leftCannon).schedule();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Drive faster when the right bumper is held
    new JoystickButton(m_controller, Button.kBumperRight.value)
        .whenPressed(() -> m_robotDrive.setMaxOutput(DriveConstants.kBoostCoefficient))
        .whenReleased(() -> m_robotDrive.setMaxOutput(DriveConstants.kNormalCoefficient));

    // safety button to prevent cannons from firing when not held down
    JoystickButton safetyButton = new JoystickButton(m_controller, Button.kBumperLeft.value);

    // Fires the left cannon when the A button is pressed (only if the left bumper
    // is held)
    new JoystickButton(m_controller, Button.kA.value)
        .whenPressed(new SafetyCommand(() -> safetyButton.get(), new ShootCommand(m_leftCannon)));

    // Fires the right cannon when the B button is pressed (only if the left bumper
    // is held)
    new JoystickButton(m_controller, Button.kB.value)
        .whenPressed(new SafetyCommand(() -> safetyButton.get(), new ShootCommand(m_rightCannon)));

    // Fires both cannons when the X button is pressed (only if the left bumper is
    // held)
    new JoystickButton(m_controller, Button.kX.value)
        .whenPressed(new SafetyCommand(() -> safetyButton.get(), new ShootCommand(m_leftCannon)))
        .whenPressed(new SafetyCommand(() -> safetyButton.get(), new ShootCommand(m_rightCannon)));
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
