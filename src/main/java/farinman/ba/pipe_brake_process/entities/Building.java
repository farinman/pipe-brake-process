package farinman.ba.pipe_brake_process.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity(name = "Building")
public class Building implements Serializable{
	
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	private String street;
	private String buildingNumber;
	private int postCode;
	private String place;
	private String country;
	private int buildingState;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "BUILDING_ID")
	private List<Dwelling> dwellingList;
	
	//private List<Worker> worker;
	
	public Long getId() {
		return id;
	}
	
	public String getStreet() {
		return street;
	}

	public String getBuildingNumber() {
		return buildingNumber;
	}
	
	public Integer getPostCode() {
		return postCode;
	}
	
	public String getPlace() {
		return place;
	}
	
	public String getCountry() {
		return country;
	}
	
	public int getBuildingState() {
		return buildingState;
	}
	
	
	public List<Dwelling> getDwellingList() {
		return dwellingList;
	}

}
