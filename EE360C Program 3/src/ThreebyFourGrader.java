
public class ThreebyFourGrader implements GradeFunction {

	private int[][] gradeFunction;

    public ThreebyFourGrader(){
    	
    gradeFunction = new int[][]{
  	         { 50, 75, 100, 100 }, //class 0
  	         { 25, 50, 75, 100},   //class 1
  	         { 0, 25, 50, 100}     //class 2
  	     };	
    }

    /*
     * This grade function is dumb: for every hour you spend on any course
     * you get one grade "point"
     */
    public int grade(int classID, int hours){
    	return gradeFunction[classID][hours];
    }	 
}
