// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants.CannonConstants;
import frc.robot.Constants.DriveConstants;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 */
public final class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;

  // All fields are static. Only those that need to be public are.

  public static final class DriveHardware {
    // The motors on the left side of the drive.
    private static final SpeedControllerGroup leftMotors = new SpeedControllerGroup(
        new WPI_TalonSRX(DriveConstants.kLeftMotor1Port),
        new WPI_TalonSRX(DriveConstants.kLeftMotor2Port));

    // The motors on the right side of the drive.
    private static final SpeedControllerGroup rightMotors = new SpeedControllerGroup(
        new WPI_TalonSRX(DriveConstants.kRightMotor1Port),
        new WPI_TalonSRX(DriveConstants.kRightMotor2Port));

    // The robot's drive
    public static final DifferentialDrive differentialDrive = new DifferentialDrive(leftMotors, rightMotors);
  }

  public static final class CannonHardware {
    public static final Relay leftRelay = new Relay(CannonConstants.kLeftRelayPort);
    public static final Relay rightRelay = new Relay(CannonConstants.kRightRelayPort);
  }
}
