package farinman.ba.pipe_brake_process.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "Worker")
@DiscriminatorColumn(name = "WorkerType")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Worker implements Serializable{
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	
	private String name;
	
	private String lastname;
	
	private String phoneNumber;
	
	private String mail;
	
/** Getter und Setter **/	

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getLastname() {
		return lastname;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getMail() {
		return mail;
	}
	
	
}
