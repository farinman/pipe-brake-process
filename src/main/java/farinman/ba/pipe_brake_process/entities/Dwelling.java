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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "Dwelling")
public class Dwelling implements Serializable{
	// Automatisch generierte Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private int floor;
    private int door;
    
    @ManyToMany()
    @JoinTable(name = 	"dwellingPersons",
    	joinColumns = @JoinColumn(name = "DWELLING_ID"),
    	inverseJoinColumns = @JoinColumn(name = "PERSON_ID")
    )
    private List<Person> persons = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "BUILDING_ID")
    private Building building;
    

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "DWELLING_ID")
	private List<Device> deviceList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public int getDoor() {
		return door;
	}

	public void setDoor(int door) {
		this.door = door;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	public Building getBuilding() {
		return building;
	}
    
}
