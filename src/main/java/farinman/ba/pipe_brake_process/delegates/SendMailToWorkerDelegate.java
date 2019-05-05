package farinman.ba.pipe_brake_process.delegates;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

/**
 * 
 * 
 * 
 * 
 * 
 * 
 */
@Named("sendMailToWorkerAdapter")
public class SendMailToWorkerDelegate implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("<---------------------------- SEND MESSAGE ---------------------------->");
		String activity = (String) execution.getVariable("activity");
		System.out.println("activity: "+activity);
		
	}

}
