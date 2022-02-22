// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
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
  public static final class DriveHardware {
    // The motors on the left side of the drive.
    private static final MotorControllerGroup leftMotors = new MotorControllerGroup(
        new WPI_TalonSRX(DriveConstants.kLeftMotor1Port), new WPI_TalonSRX(DriveConstants.kLeftMotor2Port));

    // The motors on the right side of the drive.
    private static final MotorControllerGroup rightMotors = new MotorControllerGroup(
        new WPI_TalonSRX(DriveConstants.kRightMotor1Port), new WPI_TalonSRX(DriveConstants.kRightMotor2Port));

    public static final DifferentialDrive differentialDrive = new DifferentialDrive(leftMotors, rightMotors);

    static {
      rightMotors.setInverted(true);
    }
  }

  public static final class CannonHardware {
    public static final Relay leftRelay = new Relay(CannonConstants.kLeftRelayPort);
    public static final Relay rightRelay = new Relay(CannonConstants.kRightRelayPort);
  }
}
