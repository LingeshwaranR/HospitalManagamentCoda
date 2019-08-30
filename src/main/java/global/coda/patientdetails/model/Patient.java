package global.coda.patientdetails.model;
import java.util.*;
public class Patient {
	private int id;
	private int age;
	private String name;
	private List<String> address;

	

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List<String> getAddress() {
		return address;
	}

	public void setAddress(List<String> address) {
		this.address = address;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return  id + "," + name + "," + age + "," + address ;
	}
	
	
}
