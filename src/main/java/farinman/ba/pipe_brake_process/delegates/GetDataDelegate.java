package farinman.ba.pipe_brake_process.delegates;

import java.util.Optional;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import farinman.ba.pipe_brake_process.entities.Building;
import farinman.ba.pipe_brake_process.entities.Device;
import farinman.ba.pipe_brake_process.entities.Dwelling;
import farinman.ba.pipe_brake_process.repositories.DeviceRepository;


/**
 * Die Klasse GetDataDelegate ermittelt alle Daten die für den Prozess nötig sind und instanziert sie als Prozessvariablen.
 */
@Named("getDataAdapter")
public class GetDataDelegate implements JavaDelegate{

	/** The device repository. */
	@Autowired
	DeviceRepository deviceRepository;
	
	/**
	 * Execute.
	 *
	 * @param execution the execution
	 * @throws Exception the exception
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		
		Device device = new Device();
		Dwelling dwelling = new Dwelling();
		Building building = new Building();
		String deviceId = (String) execution.getVariable("deviceId");
		
		Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
		//Prüfen ob Device vorhanden		
		if(optionalDevice.isPresent()) {
			//Falls vorhanden, dem Objekt Device das Resultat zuordnen
			device = optionalDevice.get();
		}
		
		dwelling = device.getDwelling();
		building = dwelling.getBuilding();
		device.setWarningCounter(device.getWarningCounter()+1);
		deviceRepository.saveAndFlush(device);
		execution.setVariable("device", device);
		execution.setVariable("dwelling", dwelling);
		execution.setVariable("building", building);
		
	}

	
}
