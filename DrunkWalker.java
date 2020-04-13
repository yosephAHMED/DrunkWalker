package edu.cuny.csi.csc330.lab5;

import java.math.*;
import java.util.*;


public class DrunkWalker {
	
	private Intersection startIntersection;
	private Intersection currentIntersection;
	private List<Intersection> stepHistory = new ArrayList<Intersection>();
	private Map<Intersection, Integer> intersectionCount = new HashMap<Intersection, Integer>();
	
	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!  
	// add other data members here including Collection instances that you will use to 
	//  to track history and statistics ... 
	

	private DrunkWalker() {
		init();
	}
	
	
	/**
	 * 
	 * @param avenue
	 * @param street
	 */
	public DrunkWalker(int avenue, int street) {	
		this.startIntersection = new Intersection(avenue, street);
		init();
		
	}
	
	
	private void init() {
		// What should be do here to initialize an instance?? 
		if (this.startIntersection == null)
			this.startIntersection = new Intersection();
		
		currentIntersection = new Intersection(startIntersection.getAvenue(), startIntersection.getStreet());
	}
	
	
	/**
	 * step in a random direction 
	 */
	public void step() {
		
		takeAStep(); 
		
		/**  !!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * Now, update the Collections that manage the following:
		 * 
		 *  1) add this next step/intersection to a "history" List that will contain the sequence of all 
		 *  steps taken by this DrunkWalker instance 
		 *  
		 *  2) add this next step to a Intersection -> Counter Map ... The Map 
		 *  Collection can and should be of Generics "Type" <Intersection, Integer> 
		 *  key = Intersection 
		 *  value = Count Value  
		 *  Need to do something like this: 
		 *  if intersection is not saved in Map yet as a key yet, add a key->value pair of Intersection->1 
		 *  else if intersection value is there, the existing key->value pair need to be replaced as 
		 *   Intersection->existing_count+1 
		 *   
		 */
		
		//appends the specified element to the end of the list
		stepHistory.add(this.currentIntersection);
		
		//if (TRUE) the key is inside our map return the associated value, else if (FALSE) return 0
		int count = intersectionCount.containsKey(currentIntersection) ? intersectionCount.get(currentIntersection) : 0;
		intersectionCount.put(currentIntersection, count + 1 );
		
		
		
	}
	
	
	private void takeAStep() {
		Direction dir = Direction.NONE.getNextRandom(); 
		
		/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		 * now what do we do based on the random Direction created? 
		 * How do we go about updating the "currentIntersection" instance to reflect the 
		 * direction/step that was just selected? 
		 */
		
		//we need to create a new intersection and then set our current intersection as a reference
		Intersection newLocation = new Intersection(currentIntersection.getAvenue(), currentIntersection.getStreet());
		
		/**
		 * SOUTH = street - 1; NORTH = street + 1; WEST = avenue + 1; EAST = avenue - 1;
		 */
		
		switch(dir) {
		case SOUTH:
			newLocation.setStreet(newLocation.getStreet()-1);
			break;
		case WEST:
			newLocation.setAvenue(newLocation.getAvenue()+1);
			break;
		case NORTH:
			newLocation.setStreet(newLocation.getStreet()+1);
			break;
		case EAST:
			newLocation.setAvenue(newLocation.getAvenue()-1);
			break;
		case NORTHWEST:
			newLocation.setStreet(newLocation.getStreet()+1);
			newLocation.setAvenue(newLocation.getAvenue()+1);
			break;
		case NORTHEAST:
			newLocation.setStreet(newLocation.getStreet()+1);
			newLocation.setAvenue(newLocation.getAvenue()-1);
			break;
		case SOUTHWEST:
			newLocation.setStreet(newLocation.getStreet()-1);
			newLocation.setAvenue(newLocation.getAvenue()+1);
			break;
		case SOUTHEAST:
			newLocation.setStreet(newLocation.getStreet()-1);
			newLocation.setAvenue(newLocation.getAvenue()-1);
			break;
		default:
			System.out.println("No step taken.");
			System.exit(0);
		}
		
		//making currentIntersection a reference to our new intersection
		currentIntersection = newLocation;
		
	}


	/** !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	 * toString() 
	 * @return
	 */
	
	@Override
	public String toString() {
		return "DrunkWalker [currentIntersection=" + currentIntersection + "]";
	}


	/**
	 * generate string that contains current intersection/location info 
	 */
	public String getLocation() {
		
		return String.format("Current location: DrunkWalker [avenue=%d, street=%d]", 
				currentIntersection.getAvenue(), currentIntersection.getStreet() ); 
		
	}

	
	/**
	 * Take N number of steps 
	 * @param steps
	 */
	public void fastForward(int steps) {
		
		for (int i = 0; i < steps; i++)
			step();
		
	}
	
	
	/**
	 * Display information about this current walker instance 
	 */
	public void displayWalkDetails() {
		/**
		 * This method needs to display the following information in a neat, readable format 
		 * using calls to System.out.println() or System.out.printf()
		 * 
		 * 1 - starting location 
		 * 2 - current/ending location 
		 * 3 - distance (value returned by howFar() ) 
		 * 4 - number of steps taken - which collection would be able to provide that information, and how?  
		 * 5 - number of unique intersections visited - 
		 * 		which collection would be able to provide that information, and how? 
		 * 6 - Intersections visited more than once 
		 * 
		 */
		
		//total_steps = # of elements in stepHistory
		//unique_intersection = # of unique keys in intersectionCount
		
		System.out.printf("\nStarting Location: %s", startIntersection);
		System.out.printf("\nEnding Location: %s", currentIntersection);
		System.out.printf("\nDistance Traveled: %d", howFar());
		System.out.printf("\nTotal Steps: %d", stepHistory.size());
		System.out.printf("\nNumber of Unique Intersections Visited: %d\n\n", intersectionCount.entrySet().size());
		
		
		intersectionCount.entrySet().forEach(location -> {
			if (location.getValue() > 1)
				System.out.println(location.getKey() + " was visited: " + location.getValue() + " times.");
		});
		
	}
	
	
	/**
	 * X Y Coordinate distance formula 
	 *  |x1 - x2| + |y1 - y2|   
	 * @return
	 */
	public int howFar() {
		
		int startAvenue = startIntersection.getAvenue(); 
		int finalAvenue = currentIntersection.getAvenue();
		int startStreet = startIntersection.getStreet();
		int finalStreet = currentIntersection.getStreet();
	 
		return (Math.abs(startAvenue - finalAvenue)) + (Math.abs(startStreet - finalStreet)); 
		
	}


	public static void main(String[] args) {
		
		// create Drunkard with initial position (ave,str)
		DrunkWalker billy = new DrunkWalker(6,23);
		
		for(int i = 1 ; i <= 3 ; ++i ) {
			billy.step(); 
			System.out.printf("billy's location after %d steps: %s\n",
					i, billy.getLocation() );
		}
		
		
		System.out.println("\nbilly's location before fastForward(): " + billy.getLocation());
		
		// have him move 25  random intersections
		billy.fastForward(25);
		
		// get his current location
		String location = billy.getLocation();
		// get distance from start
		int distance = billy.howFar();
		
		System.out.println("Current location after fastForward(): " + location);
		System.out.println("That's " + distance + " blocks from start.");
		
		// incorrect .fastForward() placement
		// billy.fastForward(25);
		
		System.out.println("\n----Journey Details----");
		billy.displayWalkDetails();

	}

}
