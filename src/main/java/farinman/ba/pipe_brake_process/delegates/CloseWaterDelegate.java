package farinman.ba.pipe_brake_process.delegates;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import farinman.ba.pipe_brake_process.entities.Device;
import farinman.ba.pipe_brake_process.repositories.DeviceRepository;

@Named("closeWaterAdapter")
public class CloseWaterDelegate implements JavaDelegate{

	@Autowired
	DeviceRepository deviceRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Device device = (Device) execution.getVariable("device");
		String deviceId = device.getId();
		String action = "closeWater";
		String accessToken = "a1041533a33b1618277a3e4f4bb0532aa95b3498";
		final String url = "https://api.particle.io/v1/devices/"+deviceId+"/"+action+"?access_token="+accessToken;
		//final String url = "https://api.particle.io/v1/devices/{deviceId}/{action}/?acces_token={accesToken}";
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

	    RestTemplate restTemplate = new RestTemplate();
	    
	    ResponseEntity<String> s = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	    TimeUnit.SECONDS.sleep(32);
	    if(s.getStatusCode() == HttpStatus.OK) {
	    	device.setWaterClosed(true);
	    }
	    
	    deviceRepository.saveAndFlush(device);
		
	}
}
