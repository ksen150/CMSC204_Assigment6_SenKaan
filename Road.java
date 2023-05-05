
public class Road implements Comparable<Road> {

	private Town town0;
	private Town town1;
	private int weight;
	private String name;
	
	/*
	 * Constructor that creates a new road 
	 * @param source - first town
	 * @param destination - second town
	 * @oaram weight - distance from one town to the other
	 * @param name - name of the road 
	 */
	public Road(Town source, Town destination, int weight, String name)
	{
		this.town0 = source;
		this.town1 = destination;
		this.weight = weight;
		this.name = name;
	}
	
	/*
	 * Constructor with weight preset to 1
	 * @param source - first town
	 * @param destination - second town
	 * @param name - name of the road
	 */
	public Road(Town source, Town destination, String name)
	{
		this(source,destination,1,name);
	}
	
	/*
	 * Returns true only if the edge contains the given town
	 * @param town - a vertex of the graph 
	 * @return true - only if the edge is connected to the given vertex
	 */
	public boolean contains(Town town)
	{
		return ((town.getName().equalsIgnoreCase(town0.getName()))) || (town.getName().equalsIgnoreCase(town1.getName()));
	}
	
	/*
	 * @return 0 if the road names are the same, a positive or negative number if the road names are not the same
	 */
	@Override
	public int compareTo(Road o) {
		return name.compareToIgnoreCase(o.getName());
	}
	
	/*
	 * Returns true if each of the ends of the road r is the same as the ends of this road. Remember that a road
	 * that goes from point A to point B is the same as a road that goes from point B to point A
	 * @param r - the road object to compare it to 
	 * @return true if each of the ends of the road r is the same as the ends of this road 
	 */
	@Override
	public boolean equals(Object r)
	{
		if(!(r instanceof Road))
		{
			return false;
		}
		else {
			String rSource = ((Road) r).getSource().getName();
			String rDestination = ((Road) r).getDestination().getName();
			if((town0.getName().equalsIgnoreCase(rSource)||town0.getName().equalsIgnoreCase(rDestination))&&(town1.getName().equalsIgnoreCase(rSource)||town1.getName().equalsIgnoreCase(rDestination)))
			{
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	/*
	 * @return String format of road
	 */
	public String toString()
	{
		
		return name + " is between " + town0.getName() + " and " + town1.getName() + " and is " + weight + " miles apart";
	}
	
	public Town getSource() {
		return town0;
	}

	public void setSource(Town source) {
		this.town0 = source;
	}

	public Town getDestination() {
		return town1;
	}

	public void setDestination(Town destination) {
		this.town1 = destination;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
