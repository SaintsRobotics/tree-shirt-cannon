package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.subsystems.Drivetrain;

public class DrivetrainCommand extends Command {
    private Drivetrain m_drivetrain;
    public DrivetrainCommand(Drivetrain drivetrain){
        m_drivetrain = drivetrain;
        requires(m_drivetrain);
    }
    protected void initialize(){

    }
    protected void execute() {

		this.m_drivetrain.arcadeDrive(OI.xboxController.getRawAxis(OI.LEFT_STICK_Y), OI.xboxController.getRawAxis(OI.RIGHT_STICK_X));
	}
    //TODO: access the drivetrain subsystem
    protected boolean isFinished(){
        return false;
    }
    protected void end() {
		this.m_drivetrain.arcadeDrive(0, 0);
	}
    @Override
	protected void interrupted() {
		end();
	}
}