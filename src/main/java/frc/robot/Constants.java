// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    public static final int kLeftMotor1Port = 1;
    public static final int kLeftMotor2Port = 2;
    public static final int kRightMotor1Port = 3;
    public static final int kRightMotor2Port = 4;

    /** Coefficient to multiply the speed of the robot while not boosting */
    public static final double kNormalCoefficient = 0.5;

    /** Coefficient to multiply the speed of the robot while boosting */
    public static final double kBoostCoefficient = 1.0;
  }

  public static final class CannonConstants {
    public static final int kLeftRelayPort = 0;
    public static final int kRightRelayPort = 1;

    /**
     * The number of seconds the {@link Relay} needs to be powered to ensure it
     * becomes fully opened or closed. Do NOT power the relay for longer than one
     * second to prevent the solenoid from overheating.
     */
    public static final double kOnDuration = 1;

    /**
     * Closes the valve by running the {@link Relay} reverse. (Red LED on the relay,
     * -12V)
     */
    public static final Value kCloseValue = Value.kReverse;

    /**
     * Opens the valve by running the {@link Relay} forward. (Green LED on the
     * relay, +12V)
     */
    public static final Value kOpenValue = Value.kForward;

    /**
     * Sets both {@link Relay} outputs to ground, latching the solenoid in its last
     * commanded state. (Orange LED on the relay)
     */
    public static final Value kOffValue = Value.kOff;
  }

  public static final class OIConstants {
    public static final int kControllerPort = 0;
  }
}
