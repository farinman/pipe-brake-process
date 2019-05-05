package farinman.ba.pipe_brake_process.delegates;

import java.util.List;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;

import farinman.ba.pipe_brake_process.entities.Area;
import farinman.ba.pipe_brake_process.entities.Building;
import farinman.ba.pipe_brake_process.entities.FacilityWorker;
import farinman.ba.pipe_brake_process.helpers.SendMailHelper;

@Named("sendSpecialistRequestToTicketSystemAdapter")
public class SendSpecialistRequestToTicketSystemDelegate extends SendMailHelper implements JavaDelegate{


	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Building building = (Building)execution.getVariable("building");
		Area area = building.getArea();
		List<FacilityWorker> facilityWorkerList = area.getFacilityWorkers();
		String subject = "Feuchtigkeits-Anomalie "+building.getStreet()+" "+building.getBuildingNumber();
		
		
		/*for (FacilityWorker worker : facilityWorkerList) {
			String text = "Hallo ";
			text += worker.getName()+"\n\n";
			text += "Es wurde eine Feuchtugkeits-Anomalie wahrgenommen.\n\n";
			text += "Strasse: "+building.getStreet()+" "+building.getBuildingNumber()+"\n";
			text += "Ort / PLZ: "+building.getPlace()+" "+building.getPostCode()+"\n\n";
			text += "----------Gebäude----------\n\n";
			text += "Land: "+building.getCountry()+"\n\n";
			text += "----------Wohnung----------\n\n";
			text += "Stockwerk: \n";
			text += "Türe \n\n";
			text += "-------Kontaktperson-------\n\n";
			text += "Name: \n";
			text += "Nachname \n";
			
			sendSimpleMessage(worker.getMail(), subject, text, "blub");
		}
		System.out.println("das läuft noch");*/
	}
	
}
