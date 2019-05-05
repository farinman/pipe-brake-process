package farinman.ba.pipe_brake_process.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity(name = "Area")
public class Area implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String description;
    
    @ManyToMany()
    @JoinTable(name = 	"facilityWorkersAreas",
    	joinColumns = @JoinColumn(name = "AREA_ID"),
    	inverseJoinColumns = @JoinColumn(name = "FACILITY_WORKER_ID")
    )
    private List<FacilityWorker> facilityWorkers = new ArrayList<>();
    private String name;
    
    @OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "AREA_ID")
	private List<Building> buildingList;
    
    /** Getter und Setter **/	   

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public List<FacilityWorker> getFacilityWorkers() {
		return facilityWorkers;
	}

	public String getName() {
		return name;
	}

	public List<Building> getBuildingList() {
		return buildingList;
	}
    
	
	
    
}
