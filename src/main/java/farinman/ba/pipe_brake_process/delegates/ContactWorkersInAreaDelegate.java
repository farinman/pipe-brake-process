package farinman.ba.pipe_brake_process.delegates;


import java.util.List;

import javax.inject.Named;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import farinman.ba.pipe_brake_process.entities.Area;
import farinman.ba.pipe_brake_process.entities.Building;
import farinman.ba.pipe_brake_process.entities.FacilityWorker;

/**
 * 
 * 
 * 
 * 
 * 
 * 
 */
@Named("contactWorkersInAreaAdapter")
public class ContactWorkersInAreaDelegate implements JavaDelegate {
	
    @Autowired
    public JavaMailSender emailSender;

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("<---------------------------- SEND MESSAGE ---------------------------->");
		String activity = (String) execution.getVariable("activity");
		System.out.println("activity: "+activity);
		Building building = (Building)execution.getVariable("building");
		Area area = building.getArea();
		List<FacilityWorker> facilityWorkerList = area.getFacilityWorkers();
		String subject = "Feuchtigkeits-Anomalie "+building.getStreet()+" "+building.getBuildingNumber();
		
		
		for (FacilityWorker worker : facilityWorkerList) {
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
			
			System.out.println("MA-Nr: "+worker.getId()+" Name: "+worker.getName()+" Mail: "+worker.getMail());
			sendSimpleMessage(worker.getMail(), subject, text);
		}
		

	}

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setTo(to); 
        message.setSubject(subject); 
        message.setText(text);
        emailSender.send(message);

    }
}
