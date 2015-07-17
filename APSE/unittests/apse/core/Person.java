package apse.core;
/**
 * Simple Actor subclass to be used for testing purposes
 * @author Matt Gorelik
 *
 */
public class Person extends APSEActor {

	int age;
	boolean updated;
	String type;
	public Person(String type)
	{
		this.age = 0;
		this.updated = false;
		this.type = type;
	}
	
	@Override
	public void update(APSEModelDelegate delegate) {
		this.age++;
		this.updated = true;
	}

	@Override
	public String getType() {
		return type;
	}	
}