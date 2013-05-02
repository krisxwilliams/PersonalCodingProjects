/**
 * @author Kris Williams
 * @eid kxw62
 *
 */

public class Program3 implements IProgram3{
	
	int noOfClasses;
	int maximumGrade;
	GradeFunction gradeCalc; 
	int[][] premiumAllocations;
	int[][][] hourAllocations;
	int previousTotalHours;
			
	@Override
	public void initialize(int numClasses, int maxGrade, GradeFunction gf) {
		noOfClasses = numClasses+1;
		maximumGrade = maxGrade;	
		gradeCalc = gf;
		
	}

	@Override
	public int[] computeHours(int totalHours) {		
		//Optimization to prevent repeated work.
		if(hourAllocations != null && previousTotalHours == totalHours){
			int[] idealHours = generateIdealHours(totalHours);
			return idealHours;
		}
		
		int hoursIndex = totalHours + 1;
		
		previousTotalHours = totalHours;
		premiumAllocations = new int[noOfClasses][hoursIndex];
		hourAllocations = new int[noOfClasses][hoursIndex][noOfClasses];
		
		for(int c = 1; c < noOfClasses; c++){
			
			for(int h = 0; h < hoursIndex; h++){
				int topGradeSoFar = 0;
				int premiumK = 0;
				
				for(int k = 0; k < (h+1); k++){
					int thisGrade = premiumAllocations[c-1][h-k] + gradeCalc.grade(c-1, k);
					if(thisGrade > topGradeSoFar){
						topGradeSoFar = thisGrade;
						premiumK = k;						
					}
				}
				
				setAllocations(c, h, topGradeSoFar, premiumK);
			}
			
		}
		
		int[] idealHours = generateIdealHours(totalHours);
		
		return idealHours;
	}

	@Override
	public int[] computeGrades(int totalHours) {		
		//Optimization to prevent repeated work.
		if(hourAllocations != null && previousTotalHours == totalHours){
			int[] idealHours = generateIdealHours(totalHours);
			int[] idealGrades = generateIdealGrades(idealHours);
			return idealGrades;
		}
		
		int[] idealHours = computeHours(totalHours);
		int[] idealGrades = generateIdealGrades(idealHours);
		
		for(int i = 0; i < idealGrades.length; i++){
			System.out.print(idealGrades[i] + "\t");
		}
		
		return idealGrades;
	}
	
	/**
	 * Method which will set the various allocation properties appropriately after finding the ideal hour allocations.
	 * A few index adjustments were necessary to ensure the integrity of the arrays, i.e. the hourAllocations at the end.
	 * @param classIndex Index of the course being studied at the time.
	 * @param hourIndex Index of the amount of hours available to study with.
	 * @param topGrade Best possible grade of the selection.
	 * @param premiumK Best amount of hours to allocate to this particular issue.
	 */
	private void setAllocations(int classIndex, int hourIndex, int topGrade, int premiumK) {		
		premiumAllocations[classIndex][hourIndex] = topGrade;
		
		for(int i = 0; i < noOfClasses; i++){
			hourAllocations[classIndex][hourIndex][i] = hourAllocations[classIndex-1][hourIndex-premiumK][i];
		}		
		
		hourAllocations[classIndex][hourIndex][classIndex-1] += premiumK;
	}
	
	/**
	 * Method which generates the ideal hour allocations for courses after the hourAllocations property has been populated.
	 * @param totalHours The number of total available studying hours.
	 * @return Returns the final array of ideal hours to allocate for classes.
	 */
	private int[] generateIdealHours(int totalHours) {
		int[] idealHours = new int[noOfClasses-1];
		for(int i = 0; i < noOfClasses-1; i++){
			idealHours[i] = hourAllocations[noOfClasses-1][totalHours][i];
		}
		return idealHours;
	}
	
	/**
	 * Method which generates the ideal grades array.
	 * @param idealHours Array generated using the computeHours method, containing the ideal hours to allocate for each class.
	 * @return Returns the final array of ideal grades if the optimum amount of hours are studied for each.
	 */
	private int[] generateIdealGrades(int[] idealHours) {
		int[] idealGrades = new int[idealHours.length];
		
		for(int i = 0; i < idealHours.length; i++){
			idealGrades[i] = gradeCalc.grade(i, idealHours[i]);
		}
		return idealGrades;
	}
	

}
