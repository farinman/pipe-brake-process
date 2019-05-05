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

@Entity(name = "Person")
public class Person implements Serializable{
	// Automatisch generierte Id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String lastname;
    @ManyToMany(cascade = {
    		CascadeType.PERSIST,
    		CascadeType.MERGE
    })
    @JoinTable(name = 	"dwellingPersons",
    	joinColumns = @JoinColumn(name = "PERSON_ID"),
    	inverseJoinColumns = @JoinColumn(name = "DWELLING_ID")
    )
    private List<Dwelling> dwellings = new ArrayList<>();
    
    private Boolean principalTenant;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastname() {
		return lastname;
	}

	public List<Dwelling> getDwellings() {
		return dwellings;
	}

	public Boolean isPrincipalTenant() {
		return principalTenant;
	}
    
    
}
