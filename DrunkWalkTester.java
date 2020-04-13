package edu.cuny.csi.csc330.lab5;

import java.util.*;

public class DrunkWalkTester {
	
	private Scanner input;

	public DrunkWalkTester() {
		init();  
	}
	
	private void init() {
		input = new Scanner(System.in);
	}
	
	public void runTest(int steps ) { 
		
		
		System.out.print("Enter the starting avenue value: ");
		int avenue = input.nextInt();
		System.out.print("Enter the starting street value: ");
		int street = input.nextInt();
		
		//////////////////////// start test 
		// make the Drunkard with initial position
		DrunkWalker billy = new DrunkWalker(avenue,street);

		// have him move/step 200 time 
		billy.fastForward(steps);
		
		// get his current location
		String billyLocation = billy.getLocation();
		
		// get distance from start
		int billyDistance = billy.howFar();

		System.out.println("\n\nBilly's " + billyLocation);
		System.out.println("That's " + billyDistance + " blocks from start.");
		
		System.out.println("\nInformation About Billy's Journey");
		billy.displayWalkDetails();
		
		System.out.println("\n--------------------------------------\n");
		
		
		/** 
		 * Expand the test above to include the following ... 
		 * Create a 2nd instances of DrunkWalker - Harvey  
		 * Have then race each - which instance (billy or harvey)  
		 * manages to walk a greater distance with 200 steps?  
		 * 
		 * Also invoke the displayWalkDetails() on both instances.
 */
		
		// creating a new instance with same starting location
		DrunkWalker harvey = new DrunkWalker(avenue, street);
		
		// make harvey take 200 steps
		harvey.fastForward(steps);
		
		// get harvey's current location
		String harveyLocation = harvey.getLocation();
		
		// get harvey's distance from start
		int harveyDistance = harvey.howFar();
		
		System.out.println("Harvey's " + harveyLocation);
		System.out.println("That's " + harveyDistance + " blocks from start.");
		
		System.out.println("\nInformation About Harvey's Journey");
		harvey.displayWalkDetails();
		
		System.out.println("\n--------------------------------------");
		
		
		// who won the race?
		if (billyDistance > harveyDistance)
			System.out.println("\nBilly Won The Race! He traveled farther than Harvey.");
		else if (billyDistance < harveyDistance)
			System.out.println("\nHarvey Won The Race! He traveled farther than Billy.");
		else
			System.out.println("\nNo One Won The Race! Both traveled the same distance.");
	

	}

	/**
	 * @param args 
	 * 
	 */
	public static void main(String[] args) {
		DrunkWalkTester tester = new DrunkWalkTester();
		
		//steps updated from 2000 to 200
		int steps = 200; 
		if(args.length == 1) {
			steps = Integer.parseInt(args[0]);
		}
		
		tester.runTest(steps); 
		
		System.exit(0);

	}

}
