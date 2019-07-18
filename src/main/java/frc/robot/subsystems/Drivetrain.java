package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.DrivetrainCommand;

public class Drivetrain extends Subsystem {
    private DifferentialDrive m_differentialDrive;

    public Drivetrain(SpeedController leftMotor, SpeedController rightMotor) {
        m_differentialDrive = new DifferentialDrive(leftMotor, rightMotor);
    }

    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
        m_differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DrivetrainCommand(this));
    }

}