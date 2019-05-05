package farinman.ba.pipe_brake_process.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "FacilityWorker")
@DiscriminatorValue(value = "Facility")
public class FacilityWorker extends Worker implements Serializable{

	@ManyToMany(cascade = {
    		CascadeType.PERSIST,
    		CascadeType.MERGE
    })
    @JoinTable(name = 	"facilityWorkersAreas",
    	joinColumns = @JoinColumn(name = "FACILITY_WORKER_ID"),
    	inverseJoinColumns = @JoinColumn(name = "AREA_ID")
    )
	private List<Area> areas = new ArrayList<>();
	
	public List<Area> getAreas() {
		return areas;
	}

	
}
