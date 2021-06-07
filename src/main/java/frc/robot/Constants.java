// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

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
    public static final int A_BUTTON = 1;
    public static final int B_BUTTON = 2;

    public static final class Drivetrain {
        /** Coefficient to multiply the speed of the robot while not boosting */
        public static final double NORMAL_COEFFICIENT = 0.75;

        /** Coefficient to multiply the speed of the robot while boosting */
        public static final double BOOST_COEFFICIENT = 0.9;
    }

    public static final class Cannon {
        /**
         * The number of seconds the {@link Relay} needs to be powered to ensure it
         * becomes fully opened or closed.
         */
        public static final double ON_DURATION = 1;

        /** Closes the valve by running the {@link Relay} forward. */
        public static final Value CLOSE_VALVE = Value.kForward;

        /** Opens the valve by running the {@link Relay} in reverse. */
        public static final Value OPEN_VALVE = Value.kReverse;

        /** Turns the {@link Relay} off. */
        public static final Value OFF = Value.kOff;
    }
}
