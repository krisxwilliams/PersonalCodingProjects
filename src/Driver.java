
public class Driver {
	
	public static void main(String[] args){
		GradeFunction grader = new ConcreteRandomGrade(5, 10);
		Program3 driver = new Program3();
		driver.initialize(5, 10, grader);
		driver.computeHours(25);
	}
	
}
