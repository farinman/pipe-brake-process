package farinman.ba.pipe_brake_process.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "Device")
public class Device implements Serializable{
	// Manuell gesetzte Id (Particle Device id)
	@Id
    private String id;
	
    @ManyToOne
    @JoinColumn(name = "DWELLING_ID")
    private Dwelling dwelling;
    
    private String locationInDwelling;
    
    private Boolean alreadyInformed;
    
    private int warningCounter;
    
    private Boolean waterClosed;
    
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Dwelling getDwelling() {
		return dwelling;
	}

	public String getLocationInDwelling() {
		return locationInDwelling;
	}

	public Boolean getAlreadyInformed() {
		return alreadyInformed;
	}

	public void setAlreadyInformed(Boolean alreadyInformed) {
		this.alreadyInformed = alreadyInformed;
	}

	public int getWarningCounter() {
		return warningCounter;
	}

	public void setWarningCounter(int warningCounter) {
		this.warningCounter = warningCounter;
	}

	public Boolean getWaterClosed() {
		return waterClosed;
	}

	public void setWaterClosed(Boolean waterClosed) {
		this.waterClosed = waterClosed;
	}
	
	
}
