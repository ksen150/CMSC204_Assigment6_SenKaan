import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Graph implements GraphInterface<Town, Road> {

	ArrayList<Town> town;
	static ArrayList<LinkedList> edge;
	Map<Town, Town> previousNode = new HashMap<>();
	
	/*
	 * Default constructor that creates an object of the graph
	 */
	public Graph()
	{
		town = new ArrayList<Town>();
		edge = new ArrayList<LinkedList>();
	}

	  /**
     * Returns an edge connecting source vertex to target vertex if such
     * vertices and such edge exist in this graph. Otherwise returns
     * null. If any of the specified vertices is null
     * returns null
     *
     * In undirected graphs, the returned edge may have its source and target
     * vertices in the opposite order.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return an edge connecting source vertex to target vertex.
     */
	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		Road road = new Road(sourceVertex, destinationVertex, "road");
		int sourceIndx=0;
		int destinationIndx=0;
		boolean findSource = false;
		boolean findDestination = false;
		if(sourceVertex==null || destinationVertex==null)
		{
			return null;
		}
		
		for(int i=0; i<town.size(); i++)
		{
			if(town.get(i).compareTo(sourceVertex) == 0)
			{
				sourceIndx = i;
				findSource = true;
			}
			if(town.get(i).compareTo(destinationVertex) == 0)
			{
				destinationIndx = i;
				findDestination = true;
			}
			if(findSource==true && findDestination==true)
			{
				break;
			}
		}
		
		ListIterator<Road> t = edge.get(sourceIndx).listIterator(0);
		while(t.hasNext()) 
		{
			Road r = t.next();
			if(road.equals(r))
			{
				return r;
			}
		}
		return null;
	}

	  /**
     * Creates a new edge in this graph, going from the source vertex to the
     * target vertex, and returns the created edge. 
     * 
     * The source and target vertices must already be contained in this
     * graph. If they are not found in graph IllegalArgumentException is
     * thrown.
     *
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description for edge
     *
     * @return The newly created edge if added to the graph, otherwise null.
     *
     * @throws IllegalArgumentException if source or target vertices are not
     * found in the graph.
     * @throws NullPointerException if any of the specified vertices is null.
     */
	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		int sourceIndx=0;
		int destinationIndx=0;
		boolean findSource = false;
		boolean findDestination = false;
		if(sourceVertex == null || destinationVertex == null)
		{
			throw new NullPointerException();
		}
		Road e = new Road(sourceVertex, destinationVertex, weight, description);
		
		for(int i=0; i<town.size(); i++)
		{
			if(town.get(i).compareTo(sourceVertex) == 0)
			{
				sourceIndx = i;
				findSource = true;
			}
			if(town.get(i).compareTo(destinationVertex) == 0)
			{
				destinationIndx = i;
				findDestination = true;
			}
			if(findSource == true && findDestination == true)
			{
				break;
			}
		}
		
		if(findSource == false || findDestination == false)
		{
			throw new IllegalArgumentException();
		}
		else {
			if(!edge.get(destinationIndx).contains(e) && !edge.get(sourceIndx).contains(e))
			{
				edge.get(sourceIndx).add(e);
				edge.get(destinationIndx).add(e);
				sourceVertex.addAdjacentTown(destinationVertex);
				destinationVertex.addAdjacentTown(sourceVertex);
				return e;
			}
		}
		return null;
	}

	/**
     * Adds the specified vertex to this graph if not already present. More
     * formally, adds the specified vertex, v, to this graph if
     * this graph contains no vertex u such that
     * u.equals(v). If this graph already contains such vertex, the call
     * leaves this graph unchanged and returns false. In combination
     * with the restriction on constructors, this ensures that graphs never
     * contain duplicate vertices.
     *
     * @param v vertex to be added to this graph.
     *
     * @return true if this graph did not already contain the specified
     * vertex.
     *
     * @throws NullPointerException if the specified vertex is null.
     */
	@Override
	public boolean addVertex(Town v) {
		for(int i=0; i<town.size(); i++)
		{
			if(town.get(i).compareTo(v) == 0)
			{
				return false;
			}
		}
		town.add(v);
		if(edge.size() == 0)
		{
			edge.add(new LinkedList<Road>());
		}
		else {
			edge.add(new LinkedList<Road>());
		}
		return true;
	}
	
	   /**
     * Returns true if and only if this graph contains an edge going
     * from the source vertex to the target vertex. In undirected graphs the
     * same result is obtained when source and target are inverted. If any of
     * the specified vertices does not exist in the graph, or if is
     * null, returns false.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     *
     * @return true if this graph contains the specified edge.
     */
	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		int sourceIndx=0;
		int destinationIndx=0;
		boolean findSource = false;
		boolean findDestination = false;
		
		for(int i=0; i<town.size(); i++)
		{
			if(town.get(i).compareTo(sourceVertex) == 0)
			{
				sourceIndx = i;
				findSource = true;
			}
			if(town.get(i).compareTo(destinationVertex) == 0)
			{
				destinationIndx = i;
				findDestination = true;
			}
			if(findSource==true && findDestination==true)
			{
				break;
			}
		}
		if(findSource == true && findDestination == true)
		{
			ListIterator<Road> t = edge.get(sourceIndx).listIterator(0);
			while(t.hasNext())
			{
				Road r = t.next();
				if(r.contains(sourceVertex) && r.contains(destinationVertex))
				{
					return true;
				}
			}
		}
		return false;
	}

	  /**
     * Returns true if this graph contains the specified vertex. More
     * formally, returns true if and only if this graph contains a
     * vertex u such that u.equals(v). If the
     * specified vertex is null returns false.
     *
     * @param v vertex whose presence in this graph is to be tested.
     *
     * @return true if this graph contains the specified vertex.
     */
	@Override
	public boolean containsVertex(Town v) {
		for(int i=0; i<town.size(); i++)
		{
			if(town.get(i).compareTo(v) == 0)
			{
				return true;
			}
		}
		return false;
	}
	
	 /**
     * Returns a set of the edges contained in this graph. The set is backed by
     * the graph, so changes to the graph are reflected in the set. If the graph
     * is modified while an iteration over the set is in progress, the results
     * of the iteration are undefined.
     *
     *
     * @return a set of the edges contained in this graph.
     */
	@Override
	public Set<Road> edgeSet() {
		Set<Road> r = new HashSet<Road>();
		for(int i=0; i<edge.size(); i++)
		{
			ListIterator<Road> t = edge.get(i).listIterator(0);
			while(t.hasNext())
			{
				Road road = t.next();
				if(!r.contains(road))
				{
					r.add(road);
				}
			}
		}
		return r;
	}

	/**
     * Returns a set of all edges touching the specified vertex (also
     * referred to as adjacent vertices). If no edges are
     * touching the specified vertex returns an empty set.
     *
     * @param vertex the vertex for which a set of touching edges is to be
     * returned.
     *
     * @return a set of all edges touching the specified vertex.
     *
     * @throws IllegalArgumentException if vertex is not found in the graph.
     * @throws NullPointerException if vertex is null.
     */
	@Override
	public Set<Road> edgesOf(Town vertex) {
		int indx = 0;
		Set<Road> r = new HashSet<Road>();
		for(int i=0; i<town.size(); i++)
		{
			if(town.get(i).compareTo(vertex) == 0)
			{
				indx = i;
			}
		}
		ListIterator<Road> t = edge.get(indx).listIterator(0);
		while(t.hasNext())
		{
			Road road = t.next();
			if(!r.contains(road))
			{
				r.add(road);
			}
		}
		return r;
	}
	
	  /**
     * Removes an edge going from source vertex to target vertex, if such
     * vertices and such edge exist in this graph. 
     * 
     * If weight >- 1 it must be checked
     * If description != null, it must be checked 
     * 
     * Returns the edge if removed
     * or null otherwise.
     *
     * @param sourceVertex source vertex of the edge.
     * @param destinationVertex target vertex of the edge.
     * @param weight weight of the edge
     * @param description description of the edge
     *
     * @return The removed edge, or null if no edge removed.
     */
	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		int sourceIndx = 0, destinationIndx = 0;
		Road road = new Road(sourceVertex, destinationVertex, weight, description);
		if(containsEdge(sourceVertex, destinationVertex))
		{
			for(int i=0; i<town.size(); i++)
			{
				boolean findSource = false;
				if(town.get(i).compareTo(sourceVertex) == 0)
				{
					sourceIndx = i;
					findSource = true;
				}
				boolean findDestination = false;
				if(town.get(i).compareTo(destinationVertex) == 0)
				{
					destinationIndx = i;
					findDestination = true;
				}
				if(findSource == true && findDestination == true)
				{
					break;
				}
			}
			ListIterator<Road> t = edge.get(sourceIndx).listIterator(0);
			ListIterator<Road> t1 = edge.get(destinationIndx).listIterator(0);
			while(t.hasNext())
			{
				Road r = t.next();
				if(r.equals(road))
				{
					if(edge.get(sourceIndx).remove(r))
					{
						break;
					}
				}
			}
			while(t1.hasNext())
			{
				Road r = t1.next();
				if(r.equals(road))
				{
					if(edge.get(destinationIndx).remove(r))
					{
						break;
					}
				}
			}
			return road;
		}
		return null;
	}

	/**
     * Removes the specified vertex from this graph including all its touching
     * edges if present. More formally, if the graph contains a vertex 
     * u such that u.equals(v), the call removes all edges
     * that touch u and then removes u itself. If no
     * such u is found, the call leaves the graph unchanged.
     * Returns true if the graph contained the specified vertex. (The
     * graph will not contain the specified vertex once the call returns).
     *
     * If the specified vertex is null returns false.
     *
     * @param v vertex to be removed from this graph, if present.
     *
     * @return true if the graph contained the specified vertex;
     * false otherwise.
     */
	@Override
	public boolean removeVertex(Town v) {
		int townIndx = 0;
		if(containsVertex(v))
		{
			for(int i=0; i<town.size(); i++)
			{
				if(town.get(i).compareTo(v) == 0)
				{
					townIndx = i;
					break;
				}
			}
			for(int i=0; i<v.getAdjacentTown().size(); i++)
			{
				Road r = getEdge(v.getAdjacentTown().get(i), v);
				if(containsEdge(v.getAdjacentTown().get(i), v))
				{
					removeEdge(v.getAdjacentTown().get(i),v,r.getWeight(),r.getName());
				}
			}
			town.remove(townIndx);
			edge.remove(townIndx);
			return true;
		}
		return false;
	}
	
	  /**
     * Returns a set of the vertices contained in this graph. The set is backed
     * by the graph, so changes to the graph are reflected in the set. If the
     * graph is modified while an iteration over the set is in progress, the
     * results of the iteration are undefined.
     *
     *
     * @return a set view of the vertices contained in this graph.
     */
	@Override
	public Set<Town> vertexSet() {
		Set<Town> t = new HashSet<Town>();
		for(int i=0; i<town.size(); i++)
		{
			if(!t.contains(town.get(i)))
			{
				t.add(town.get(i));
			}
		}
		return t;
	}

	  /**
     * Find the shortest path from the sourceVertex to the destinationVertex
     * call the dijkstraShortestPath with the sourceVertex
     * @param sourceVertex starting vertex
     * @param destinationVertex ending vertex
     * @return An arraylist of Strings that describe the path from sourceVertex
     * to destinationVertex
     * They will be in the format: startVertex "via" Edge "to" endVertex weight
	 * As an example: if finding path from Vertex_1 to Vertex_10, the ArrayList<String>
	 * would be in the following format(this is a hypothetical solution):
	 * Vertex_1 via Edge_2 to Vertex_3 4 (first string in ArrayList)
	 * Vertex_3 via Edge_5 to Vertex_8 2 (second string in ArrayList)
	 * Vertex_8 via Edge_9 to Vertex_10 2 (third string in ArrayList)
     */ 
	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		ArrayList<String> path = new ArrayList<String>();
		String name;
		String path1 = "";
		dijkstraShortestPath(sourceVertex);
		Town current = destinationVertex;
		Town previous = previousNode.get(destinationVertex);
		while(current.compareTo(sourceVertex) != 0)
		{
			name = getEdge(current, previous).getName();
			path1 = previous.getName() + " via " + name + " to " + current.getName() + " " + getEdge(current, previous).getWeight() + " mi";
			path.add(0, path1);
			current = previous;
			previous = previousNode.get(current);
		}
		return path;
	}

	 /**
     * Dijkstra's Shortest Path Method.  Internal structures are built which
     * hold the ability to retrieve the path, shortest distance from the
     * sourceVertex to all the other vertices in the graph, etc.
     * @param sourceVertex the vertex to find shortest path from
     * 
     */
	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		Map<Town, Integer> pathWeight = new HashMap<>();
		Set<Town> visited = new HashSet<>();
		Comparator<Road> comparator = new Comparator<Road>()
		{
			@Override
			public int compare(Road o1, Road o2) {
				return o1.getWeight()-o2.getWeight();
			}
			
		};
		PriorityQueue<Road> priority = new PriorityQueue<>(comparator);
		int edgeDistance, newDistance;
		
		priority.addAll(sortEdges(sourceVertex));
		visited.add(sourceVertex);
		pathWeight.put(sourceVertex, 0);
		
		while(!priority.isEmpty())
		{
			Town town1, town2;
			Road path = priority.remove();
			town1 = path.getSource();
			town2 = path.getDestination();
			if(!visited.contains(town1))
			{
				visited.add(town1);
				for(Road r: sortEdges(town1))
				{
					if(!priority.contains(r))
					{
						priority.add(r);
					}
				}
				previousNode.put(town1, town2);
				edgeDistance = pathWeight.get(town2);
				newDistance = edgeDistance + path.getWeight();
				pathWeight.put(town1, newDistance);
			}
			if(!visited.contains(town2))
			{
				visited.add(town2);
				for(Road r: sortEdges(town2))
				{
					if(!priority.contains(r))
					{
						priority.add(r);
					}
				}
				previousNode.put(town2, town1);
				edgeDistance = pathWeight.get(town1);
				newDistance = edgeDistance + path.getWeight();
				pathWeight.put(town2, newDistance);
			}
			if(visited.contains(town1))
			{
				edgeDistance = pathWeight.get(town1);
				newDistance = pathWeight.get(town2) + path.getWeight();
				if(newDistance < edgeDistance)
				{
					pathWeight.put(town1, newDistance);
					previousNode.put(town1, town2);
				}
			}
			if(visited.contains(town2))
			{
				edgeDistance = pathWeight.get(town2);
				newDistance = pathWeight.get(town1) + path.getWeight();
				if(newDistance < edgeDistance)
				{
					pathWeight.put(town2, newDistance);
					previousNode.put(town2, town1);
				}
			}
			else {
				continue;
			}
		}	
	}
	
	public ArrayList<Road> sortEdges(Town sourceVertex)
	{
		ArrayList<Road> sort = new ArrayList<Road>();
		Road r = null;
		int townIndx = 0;
		for(int i=0; i<town.size(); i++)
		{
			if(town.get(i).compareTo(sourceVertex) == 0)
			{
				townIndx = i;
				break;
			}
		}
		Iterator<Road> t = edge.get(townIndx).iterator();
		while(t.hasNext())
		{
			if(sort.size() == 0)
			{
				sort.add(t.next());
			}
			else {
				r = t.next();
				sort.add(r);
			}
		}
		return sort;
	}	
}
