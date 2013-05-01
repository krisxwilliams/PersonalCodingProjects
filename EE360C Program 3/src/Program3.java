public class Program3 implements IProgram3{
	
	int noOfClasses;
	int maximumGrade;
	GradeFunction gradeCalc; 
			
	@Override
	public void initialize(int numClasses, int maxGrade, GradeFunction gf) {
		//GradesvsHours = new int[numClasses][numClasses];
		noOfClasses = numClasses;
		maximumGrade = maxGrade;	
		gradeCalc = gf;
		
	}

	@Override
	public int[] computeHours(int totalHours) {
		//Must establish a matrix to track grades versus hours spent in classes
		int[][] potentialGradeArray = initalizeGradeArray(totalHours);
		int[][] premiumAllocations = new int[totalHours][noOfClasses];
		
		int[] optimumGrades = new int[totalHours];
		
		for(int i = 1; i < totalHours; i++){
			int topGradeSoFar = 0;
			int premiumJ = 0;
			int premiumK = 0;
			
			for(int j = 0; j < noOfClasses; j++){
				
				for(int k = 1; k < i; k++){
					int potentialGrade = potentialGradeArray[k][j];
					int gradeSum = optimumGrades[i] - potentialGrade + potentialGradeArray[(i-k)][j];
					if(gradeSum > topGradeSoFar){
						topGradeSoFar = gradeSum;
						premiumJ = j;
						premiumK = k;
					}
				}
			}
			premiumAllocations[i] = optimumGrades;	
			premiumAllocations[i][premiumK] = premiumAllocations[i][premiumK] + i - premiumJ;
			optimumGrades[i] = topGradeSoFar;
		}
		
		for(int i = 0; i < totalHours; i++){
			for(int j = 0; j < noOfClasses; j++){
				System.out.print(premiumAllocations[i][j] + " ");
			}
			System.out.println("\n");
		}
				
		return null;
	}

	@Override
	public int[] computeGrades(int totalHours) {
		return null;
	}
	
	private int[][] initalizeGradeArray(int totalHours) {
		int[][] potentialGradeArray = new int[totalHours][noOfClasses];
		
		for(int i = 0; i < totalHours; i++){
			for(int j = 0; j < noOfClasses; j++){
				potentialGradeArray[i][j] = gradeCalc.grade(j, i);
			}
		}
		
		return potentialGradeArray;		
	}
}
