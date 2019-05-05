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
 * 
 */

@Named("calculateFloodingRiskAdapter")
public class CalculateFloodingRiskDelegate implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("<---------------------------- Flood Risk ---------------------------->");
		execution.setVariable("test", "foo");
	}

}
