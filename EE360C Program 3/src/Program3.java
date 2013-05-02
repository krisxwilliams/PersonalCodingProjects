public class Program3 implements IProgram3{
	
	int noOfClasses;
	int maximumGrade;
	GradeFunction gradeCalc; 
			
	@Override
	public void initialize(int numClasses, int maxGrade, GradeFunction gf) {
		noOfClasses = numClasses+1;
		maximumGrade = maxGrade;	
		gradeCalc = gf;
		
	}

	@Override
	public int[] computeHours(int totalHours) {
		//Must establish a matrix to track grades versus hours spent in classes
		int hoursIndex = totalHours + 1;
		
		int[][] premiumAllocations = new int[noOfClasses][hoursIndex];
		int[][][] hourAllocations = new int[noOfClasses][hoursIndex][noOfClasses];
		
		for(int i = 1; i < noOfClasses; i++){
			int[] hourDistribution = new int [noOfClasses];
			
			for(int j = 0; j < hoursIndex; j++){
				int topGradeSoFar = 0;
				int premiumK = 0;
				
				for(int k = 0; k < (j+1); k++){
					int thisGrade = premiumAllocations[i-1][j-k] + gradeCalc.grade(i-1, k);
					if(thisGrade > topGradeSoFar){
						topGradeSoFar = thisGrade;
						premiumK = k;						
					}
				}
				
				premiumAllocations[i][j] = topGradeSoFar;
				
				for(int x = 0; x < noOfClasses; x++){
					hourAllocations[i][j][i] = hourAllocations[i-1][j-premiumK][x];
				}				
				hourAllocations[i][j][i-1] += premiumK;
			}
			
		}
		
		
		for(int i = 1; i < noOfClasses; i++){
			for(int j = 0; j < hoursIndex; j++){
				System.out.print(premiumAllocations[i][j] + "\t");
			}
			System.out.print("\n");
		}
		
		
		System.out.println();
		for(int i = 0; i < noOfClasses; i++){
			System.out.print(hourAllocations[noOfClasses-1][hoursIndex-1][i] + "\t");		
		}
		
		return null;
	}

	@Override
	public int[] computeGrades(int totalHours) {
		return null;
	}
}
