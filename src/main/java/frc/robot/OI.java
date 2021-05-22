/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.ShootCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  // CONSTANT VALUES
  // use the getRawAxis(int) and getRawButton(int) methods inherited from
  // GeneralHID
  public static int LEFT_STICK_X = 0;
  public static int LEFT_STICK_Y = 1;
  public static int RIGHT_STICK_X = 4;
  public static int RIGHT_STICK_Y = 5;
  public static int LEFT_TRIGGER = 2;
  public static int RIGHT_TRIGGER = 3;
  public static int BUTTON_A = 1;
  public static int BUTTON_B = 2;
  public static int BUTTON_X = 3;
  public static int BUTTON_Y = 4;
  public static int BACK_BUTTON = 7;
  public static int START_BUTTON = 8;
  public static int LEFT_BUMPER = 5;
  public static int RIGHT_BUMPER = 6;
  public static int LEFT_STICK_BUTTON = 9;
  public static int RIGHT_STICK_BUTTON = 10;

  public static Joystick xboxController = new Joystick(0);

  private Button buttonA = new JoystickButton(xboxController, BUTTON_A);
  private Button buttonB = new JoystickButton(xboxController, BUTTON_B);

  public OI() {
    buttonA.whenPressed(new ShootCommand(Robot.m_leftCannon));
    buttonB.whenPressed(new ShootCommand(Robot.m_rightCannon));
  }
}
