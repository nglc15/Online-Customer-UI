package labs.lab9;

public class Person{
	String name;
	String email;
	boolean[] pets;
	String amtSpent;
	int location;
	String notes;

	public Person(String dname, String demail, boolean[] dpets, String damtSpent, int dlocation, String dnotes) {
		name = dname;
		email = demail;
		pets = dpets;
		amtSpent = damtSpent;
		location = dlocation;
		notes = dnotes;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean[] getPets() {
		return pets;
	}
	
	public String getSpent() {
		return amtSpent;
	}
	
	public int getLocation() {
		return location;
	}
	
	public String getNotes() {
		return notes;
	}
	
	
	
}