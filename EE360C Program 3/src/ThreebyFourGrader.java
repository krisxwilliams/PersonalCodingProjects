/**
 * @author Kris Williams
 * @eid kxw62
 *
 */


public class ThreebyFourGrader implements GradeFunction {

	private int[][] gradeFunction;

    public ThreebyFourGrader(){
    	
    gradeFunction = new int[][]{
  	         { 60, 80, 90, 90 },  
  	         { 10, 40, 50, 85 },   
  	         { 0, 0, 75, 100 }    
  	     };	
    }

    
    public int grade(int classID, int hours){
    	return gradeFunction[classID][hours];
    }	 
}
