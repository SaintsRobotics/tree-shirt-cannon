package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.commands.DrivetrainCommand;

public class DrivetrainSubsystem extends Subsystem {
    private DifferentialDrive m_differentialDrive;
    private static final double SPEED_REDUCER = 0.75;
    private static final double BOOST_COEFFICIENT = .9;

    public DrivetrainSubsystem(SpeedController leftMotor, SpeedController rightMotor) {
        m_differentialDrive = new DifferentialDrive(leftMotor, rightMotor);
    }

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        if (!OI.xboxController.getRawButton(OI.RIGHT_BUMPER)
                && !(OI.xboxController.getRawAxis(OI.RIGHT_TRIGGER) > 0.75)) {
            moveSpeed *= SPEED_REDUCER;
            rotateSpeed *= SPEED_REDUCER;
        } else {
            moveSpeed *= BOOST_COEFFICIENT;
            rotateSpeed *= BOOST_COEFFICIENT;
        }

        SmartDashboard.putNumber("move speed", moveSpeed);
        SmartDashboard.putNumber("rotate speed", rotateSpeed);
        m_differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DrivetrainCommand(this));
    }

}