/**
 * Partially completed Direction ENUM 
 */

package edu.cuny.csi.csc330.lab5;

import edu.cuny.csi.csc330.util.Randomizer;


public enum Direction {
	 NONE, NORTH, EAST, SOUTH, WEST, NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST  ;
	 // !!! Add 4 more Direction Values - NORTHEAST, NORTHWEST, SOUTHWEST, SOUTHEAST
	 
	 // methods 
	 public Direction getFavorite() {
		 return SOUTH;  // It's getting cold! ... 
	 }
	 
	 public Direction getNextRandom() {
		 	
		 	/******************************
		 	 * !!!!!
		 	 * WHAT CHANGES NEED TO BE MADE HERE SO THAT THE 4 NEW RANDOM DIRECTIONS ARE CONSIDERED 
		 	 * We have 4 more directions, so generateInt(1,4) needs to become generateInt(1,8)
		 	 * We need to add additional else if statements that consider our new directions
		 	 */
			int direction = Randomizer.generateInt(1, 8); 
		
			// 1 = south,  2 = west, 3 = north, 4 = east, 5 = northWest, 6 = northEast, 7 = southWest, 8 = southEast
			if(direction == 1) { // south 
				 return SOUTH;
			}
			else if(direction == 2) {   // west 
				 return WEST; 
			}
			else if(direction == 3) {   // north 
				 return NORTH; 
			}
			else if(direction == 4){    // east 
				return EAST; 
			}
			else if(direction == 5) {	//northWest
				return NORTHWEST;
			}
			else if(direction == 6) {	//northEast
				return NORTHEAST;
			}
			else if (direction == 7) {	//southWest
				return SOUTHWEST;
			}
			else {						//southEast
				return SOUTHEAST;
			}
	 }
	 
	 public static void main(String [] args)  {
		 
		 int c = 0; 
		 while(c++ < 100) {  
			 System.out.println(Direction.NONE.getNextRandom() );
		 }
		 
	 }
	 
	 
}
