
public class Driver {
	
	public static void main(String[] args){
		//GradeFunction grader = new ConcreteRandomGrade(4, 5);
		GradeFunction grader = new ThreebyFourGrader();
		Program3 driver = new Program3();
		driver.initialize(3, 100, grader);
		//driver.computeHours(3);
		driver.computeGrades(3);
	}
	
}
