package farinman.ba.pipe_brake_process.delegates;

import java.util.Optional;

import javax.inject.Named;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import farinman.ba.pipe_brake_process.entities.Building;
import farinman.ba.pipe_brake_process.entities.Device;
import farinman.ba.pipe_brake_process.entities.Dwelling;
import farinman.ba.pipe_brake_process.repositories.BuildingRepository;
import farinman.ba.pipe_brake_process.repositories.DeviceRepository;
import farinman.ba.pipe_brake_process.repositories.DwellingRepository;


/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 */

@Named("getBuildingAndDwellingDataAdapter")
public class GetBuildingAndDwellingDataDelegate implements JavaDelegate {
	
	//Repositories
	@Autowired
	private DeviceRepository deviceRepository;
	//private DwellingRepository dwellingRepository;
	//private BuildingRepository buildingRepository;
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		
		Device device = new Device();
		Dwelling dwelling = new Dwelling();
		Building building = new Building();
		String deviceId = (String) execution.getVariable("deviceId");
		//Device finden
		Optional<Device> optionalDevice = deviceRepository.findById(deviceId);
		//Prüfen ob Device vorhanden		
		if(optionalDevice.isPresent()) {
			//Falls vorhanden, dem Objekt Device das Resultat zuordnen
			device = optionalDevice.get();
		}
		
		dwelling = device.getDwelling();
		building = dwelling.getBuilding();
		
		//int buildingState = building.getBuildingState();
		execution.setVariable("humidity", 65);
		//execution.setVariable("buildingState", buildingState);
		execution.setVariable("dwelling", dwelling);
		execution.setVariable("building", building);
		execution.setVariable("device", device);

	}

} 
