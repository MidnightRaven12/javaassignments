import java.util.Scanner;
public class chanassignment3 {
	
	//User dialogue
	public static void dialogue() {
		System.out.println("Please enter an input between (1) and (4) for the different conversions. Alternatively, enter (-1) in order to exit.");
		System.out.println("(1) - Miles to Kilometers");
		System.out.println("(2) - Grams to Pounds");
		System.out.println("(3) - Liters to Gallons");
		System.out.println("(4) - Degrees to radians \n");
		System.out.println("(-1) - Escape \n");
	}
	public static void main(String[] args) { 
		
		// Classic Intialization
		float conversion;
		Scanner scanner = new Scanner(System.in);
		dialogue();
		int input = scanner.nextInt();
		System.out.print("\n");
		while (true) {
			if(input == -1) {
				break;
			} else {
				switch (input) {
					case 1:
						System.out.print("Input the distance in miles. ");		
						conversion = scanner.nextFloat();
						System.out.println("\nHere is your distance in kilometers: " + conversion*1.609344 + " km \n" );				
						break;
					
					case 2:
						System.out.print("Input the weight in grams. ");			
						conversion = scanner.nextFloat();
						System.out.println("\nHere is your weight in pounds: " + conversion*0.002204623 + " lb \n");
						break;
					case 3:
						System.out.print("Input the amount of fluid in liters. ");			
						conversion = scanner.nextFloat();
						System.out.println("\nHere is the amount of fluid in gallons: " + conversion*0.264172 + " gal \n");
						break;
					case 4:
						System.out.print("Input the angle in degrees. ");			
						conversion = scanner.nextFloat();
						System.out.println("\nHere is the angle in radians: " + conversion*Math.PI/180 + " rad \n");
						break;
					default:
						System.out.println("You have reached an unknown error.");
									
				}
			}
			dialogue();
			input = scanner.nextInt();
			System.out.println("\n");
		}
     	}
}
