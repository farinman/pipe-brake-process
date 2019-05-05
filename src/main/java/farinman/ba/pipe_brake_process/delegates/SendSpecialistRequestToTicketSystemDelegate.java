package farinman.ba.pipe_brake_process.delegates;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

@Named("sendSpecialistRequestToTicketSystemAdapter")
public class SendSpecialistRequestToTicketSystemDelegate implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		
	}
	
}
