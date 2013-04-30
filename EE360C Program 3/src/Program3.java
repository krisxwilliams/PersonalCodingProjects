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
		int[][] Hours_vs_Classes = new int[noOfClasses][totalHours];
		
		for(int i = 0; i < noOfClasses; i++){
			for(int j = 0; j < totalHours; j++){
				Hours_vs_Classes[i][j] = gradeCalc.grade(i, j);
			}
		}
		
		for(int i = 0; i < noOfClasses; i++){
			for(int j = 0; j < totalHours; j++){
				System.out.print(Hours_vs_Classes[i][j] + " ");
			}
			System.out.println("\n");
		}
				
		return null;
	}

	@Override
	public int[] computeGrades(int totalHours) {
		return null;
	}
	

}
