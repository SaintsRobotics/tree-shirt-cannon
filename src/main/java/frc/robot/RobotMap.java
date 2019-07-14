/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

// All fields are static.  Only those that need to be public are.
  private static SpeedController frontLeft;
  private static SpeedController midLeft;
  private static SpeedController backLeft;
  private static SpeedController frontRight;
  private static SpeedController midRight;
  private static SpeedController backRight;

  public static SpeedControllerGroup leftWheels = new SpeedControllerGroup(frontLeft, midLeft, backLeft);
  public static SpeedControllerGroup rightWheels = new SpeedControllerGroup(frontRight, midRight, backRight);

  public static Relay relayZero = new Relay(0);
  public static Relay relayOne = new Relay(1);
  public static Relay relayTwo = new Relay(2);
  public static Relay relayThree = new Relay(3);

}
