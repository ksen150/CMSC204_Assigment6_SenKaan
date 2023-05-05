import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class TownGraphManager implements TownGraphManagerInterface {

	Graph graph;
	
	public TownGraphManager()
	{
		graph = new Graph();
	}
	
	/**
	 * Adds a road with 2 towns and a road name
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName name of road
	 * @return true if the road was added successfully
	 */
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Set<Town> towns = graph.vertexSet();
		Town town = null;
		Town town0 = null;
		for(Town t: towns)
		{
			if(t.getName().equalsIgnoreCase(town1))
			{
				town = t;
			}
			if(t.getName().equalsIgnoreCase(town2))
			{
				town0 = t;
			}
			if(town != null && town0 != null)
			{
				break;
			}
		}
		Road road = graph.addEdge(town, town0, weight, roadName);
		if(road == null)
		{
			return false;
		}
		return true;
		
	}

	/**
	 * Returns the name of the road that both towns are connected through
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return name of road if town 1 and town2 are in the same road, returns null if not
	 */
	@Override
	public String getRoad(String town1, String town2) {
		Set<Town> towns = graph.vertexSet();
		Town town = null;
		Town town0 = null;
		for(Town t: towns)
		{
			if(t.getName().equalsIgnoreCase(town1))
			{
				town = t;
			}
			if(t.getName().equalsIgnoreCase(town2))
			{
				town0 = t;
			}
			if(town!=null && town0!=null)
			{
				break;
			}
		}
		Road road = graph.getEdge(town, town0);
		if(road == null)
		{
			return null;
		}
		return road.getName();
	}

	/**
	 * Adds a town to the graph
	 * @param v the town's name  (lastname, firstname)
	 * @return true if the town was successfully added, false if not
	 */
	@Override
	public boolean addTown(String v) {
		Town town = new Town(v);
		return graph.addVertex(town);
	}

	/**
	 * Gets a town with a given name
	 * @param name the town's name 
	 * @return the Town specified by the name, or null if town does not exist
	 */
	@Override
	public Town getTown(String name) {
		Set<Town> towns = new HashSet<Town>(graph.vertexSet());
		Iterator<Town> t = towns.iterator();
		while(t.hasNext())
		{
			Town town = t.next();
			if(town.getName().equalsIgnoreCase(name))
			{
				return town;
			}
		}
		return null;
	}

	/**
	 * Determines if a town is already in the graph
	 * @param v the town's name 
	 * @return true if the town is in the graph, false if not
	 */
	@Override
	public boolean containsTown(String v) {
		Town town = new Town(v);
		return graph.containsVertex(town);
	}

	/**
	 * Determines if a road is in the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return true if the road is in the graph, false if not
	 */
	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town town = new Town(town1);
		Town town0 = new Town(town2);
		return graph.containsEdge(town, town0);
	}

	/**
	 * Creates an arraylist of all road titles in sorted order by road name
	 * @return an arraylist of all road titles in sorted order by road name
	 */
	@Override
	public ArrayList<String> allRoads() {
		Set<Road> roads = graph.edgeSet();
		ArrayList<String> allRoads = new ArrayList<String>();
		for(Road road: roads)
		{
			allRoads.add(road.getName());
		}
		Collections.sort(allRoads);
		return allRoads;
	}

	/**
	 * Deletes a road from the graph
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @param roadName the road name
	 * @return true if the road was successfully deleted, false if not
	 */
	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town town = new Town(town1);
		Town town0 = new Town(town2);
		Road road1 = graph.getEdge(town, town0);
		Road road2 = graph.removeEdge(town, town0, road1.getWeight(), road);
		if(road2 != null)
		{
			return true;
		}
		return false;
		
	}

	/**
	 * Deletes a town from the graph
	 * @param v name of town (lastname, firstname)
	 * @return true if the town was successfully deleted, false if not
	 */
	@Override
	public boolean deleteTown(String v) {
		Town town = new Town(v);
		return graph.removeVertex(town);
	}

	/**
	 * Creates an arraylist of all towns in alphabetical order (last name, first name)
	 * @return an arraylist of all towns in alphabetical order (last name, first name)
	 */
	@Override
	public ArrayList<String> allTowns() {
		Set<Town> towns = graph.vertexSet();
		ArrayList<String> allTowns = new ArrayList<String>();
		for(Town town: towns)
		{
			allTowns.add(town.getName());
		}
		Collections.sort(allTowns);
		return allTowns;
	}

	/**
	 * Returns the shortest path from town 1 to town 2
	 * @param town1 name of town 1 (lastname, firstname)
	 * @param town2 name of town 2 (lastname, firstname)
	 * @return an Arraylist of roads connecting the two towns together, null if the
	 * towns have no path to connect them.
	 */
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Set<Town> towns = graph.vertexSet();
		Set<Road> roads = graph.edgeSet();
		boolean findSource = false, findDestination = false;
		Town town = null, town0 = null;
		ArrayList<String> path = new ArrayList<String>();
		try {
			for(Town t: towns)
			{
				if(t.getName().equalsIgnoreCase(town1))
				{
					town = t; 
				}
				if(t.getName().equalsIgnoreCase(town2))
				{
					town0 = t;
				}
				if(town != null && town0 != null)
				{
					break;
				}
			}
			
			for(Road r: roads)
			{
				if(r.getSource().compareTo(town) == 0 || r.getDestination().compareTo(town) == 0)
				{
					findSource = true;
				}
				if(r.getSource().compareTo(town0) == 0 || r.getDestination().compareTo(town0) == 0)
				{
					findDestination = true;
				}
				if(findSource == true && findDestination == true)
				{
					path = graph.shortestPath(town, town0);
					break;
				}
			}
			
		}
		catch(NullPointerException n)
		{
			
		}
		return path;
	}
}
