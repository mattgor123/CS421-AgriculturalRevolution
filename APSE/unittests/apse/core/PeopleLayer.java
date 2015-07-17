package apse.core;

import java.util.ArrayList;
import java.util.List;
/**
 * Simple subclass of ActionLayer to be used for testing purposes
 * @author Matt Gorelik
 *
 */
public class PeopleLayer extends APSEActionLayer {
	public List<Person> peopleList;
	public boolean updated;
	public boolean removeHasBeenCalled;
	public PeopleLayer()
	{
		this.peopleList = new ArrayList<Person>();
		this.updated = false;
	}
	@Override
	public void update(APSEModelDelegate delegate) {
		updated = true;
		for(Person person:peopleList)
		{
			person.update(delegate);
		}			
	}

	@Override
	public void insertActor(APSEActor actor) {
		peopleList.add((Person)actor);
	}

	@Override
	public void removeFlaggedActors() {
		removeHasBeenCalled = true;
		for(int i = 0; i < peopleList.size(); i++)
		{
			if(peopleList.get(i).isFlagged())
			{
				peopleList.remove(i);
				i--;
			}
		}		
	}
}