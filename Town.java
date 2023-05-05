import java.util.ArrayList;

public class Town implements Comparable<Town> {

	private String name;
	private ArrayList<Town> adjacent;
	
	/*
	 * Constructor that requires town's name
	 * @param name 
	 */
	public Town(String name)
	{
		this.name = name;
		adjacent = new ArrayList<Town>();
	}
	
	/* 
	 * Copy Constructor
	 * @param templateTown - an instance of town
	 */
	public Town(Town templateTown)
	{
		this(templateTown.getName());
		setAdjacentTowns(templateTown.getAdjacentTown());
	}
	
	/*
	 * 
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/*
	 * @return town's name
	 */
	public String getName() {
		return name;
	}

	/*
	 * Compare two towns
	 * @return 0, if names are equal, a positive or negative number if the names are NOT equal
	 */
	@Override
	public int compareTo(Town o) {
		if(o.getName().equalsIgnoreCase(name))
		{
			return 0;
		}
		else {
			return name.compareToIgnoreCase(o.getName());
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Town)
		{
			Town t = (Town)obj;
			return name.equals(t.name);
		}
		return false;
	}
	
	/*
	 * @return hashCode - the hashcode for the name of the town
	 */
	public int hashCode()
	{
		return name.hashCode();
	}
	
	/*
	 * @return String format of road
	 */
	public String toString()
	{
		return name; 
	}
	
	/*
	 * Sets the list of adjacent towns
	 * @param towns - Arraylist of adjacent towns
	 */
	public void setAdjacentTowns(ArrayList<Town> towns)
	{
		for(int i=0; i<towns.size(); i++)
		{
			adjacent.add(towns.get(i));
		}
	}
	
	/*
	 * Adds a town into the list of adjacent towns
	 * @param town - town to be added to the list
	 */
	public void addAdjacentTown(Town town)
	{
		adjacent.add(town);
	}
	
	/*
	 * @return the list of adjacent towns of a town
	 */
	public ArrayList<Town> getAdjacentTown()
	{
		return adjacent;
	}

	
}
