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

}
