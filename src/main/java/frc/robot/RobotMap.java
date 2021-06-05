/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
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

  // All fields are static. Only those that need to be public are.
  private static WPI_TalonSRX frontLeft = new WPI_TalonSRX(0);
  private static WPI_TalonSRX backLeft = new WPI_TalonSRX(1);
  private static WPI_TalonSRX frontRight = new WPI_TalonSRX(2);
  private static WPI_TalonSRX backRight = new WPI_TalonSRX(3);

  public static SpeedControllerGroup leftWheels = new SpeedControllerGroup(frontLeft, backLeft);
  public static SpeedControllerGroup rightWheels = new SpeedControllerGroup(frontRight, backRight);

  public static Relay leftRelay = new Relay(0);
  public static Relay rightRelay = new Relay(1);
}
