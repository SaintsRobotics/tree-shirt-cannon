package frc.robot.subsystems;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.DrivetrainCommand;

public class Drivetrain extends Subsystem {
    private SpeedController m_leftMotor;
    private SpeedController m_rightMotor;
    private DifferentialDrive m_differentialDrive;
    // TODO: wrap the differential drive class as a subsystem
    public Drivetrain(SpeedController leftMotor, SpeedController rightMotor){
        m_leftMotor = leftMotor;
        m_rightMotor = rightMotor;
        m_differentialDrive = new DifferentialDrive(leftMotor, rightMotor);
    }
    public void arcadeDrive(double moveSpeed, double rotateSpeed) {
		m_differentialDrive.arcadeDrive(moveSpeed, rotateSpeed);
	}
    @Override
    public void initDefaultCommand(){
        setDefaultCommand(new DrivetrainCommand(this));
    }


}