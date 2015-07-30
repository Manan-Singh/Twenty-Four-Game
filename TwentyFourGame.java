import java.util.Scanner;
import java.util.ArrayList;

public class TwentyFourGame {
	
	private static ArrayList<ArrayList<Integer>> permutations = new ArrayList<ArrayList<Integer>>();
	public static boolean doesNotEqual24 = true;
	
	public static void main(String[] args){
		
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter in four numbers: ");
		ArrayList<Integer> digits = new ArrayList<Integer>();
		digits.add(reader.nextInt());
		digits.add(reader.nextInt());
		digits.add(reader.nextInt());
		digits.add(reader.nextInt());
		permute(digits);
		//Tests
		//System.out.println(permutations.size());
		//viewPermutations();
		for(int i = 0; i < permutations.size(); i++){
			case1(permutations.get(i).get(0), permutations.get(i).get(1), permutations.get(i).get(2), permutations.get(i).get(3));
			case2(permutations.get(i).get(0), permutations.get(i).get(1), permutations.get(i).get(2), permutations.get(i).get(3));
			case3(permutations.get(i).get(0), permutations.get(i).get(1), permutations.get(i).get(2), permutations.get(i).get(3));
			case4(permutations.get(i).get(0), permutations.get(i).get(1), permutations.get(i).get(2), permutations.get(i).get(3));
			case5(permutations.get(i).get(0), permutations.get(i).get(1), permutations.get(i).get(2), permutations.get(i).get(3));
		}
		if(doesNotEqual24 == true){
			System.out.println("There are no equations with those four numbers that equal 24.");
		}
	}
	
	//Get all possible permutations of the the four digits
	public static void permute(ArrayList<Integer> toCarry, ArrayList<Integer> digits){
		if(digits.size() == 1){
			ArrayList<Integer> toBePassed = (ArrayList<Integer>) toCarry.clone();
			ArrayList<Integer> clone = (ArrayList<Integer>) digits.clone();
			toBePassed.add(clone.get(0));
			permutations.add(toBePassed);
		}else{
			ArrayList<Integer> clone = (ArrayList<Integer>) digits.clone();
			for(int i = 0; i < digits.size(); i++){
				ArrayList<Integer> toBePassed = (ArrayList<Integer>)toCarry.clone();
				toBePassed.add(digits.get(i));
				clone.remove(i);
				permute(toBePassed, clone);
				clone.add(i, toBePassed.get(toBePassed.size() - 1));
			}
		}
	}
	
	public static void permute(ArrayList<Integer> digits){
		permute(new ArrayList<Integer>(), digits);
	}
	
	public static void viewPermutations(){
		for(int i = 0; i < permutations.size(); i++){
			for(int j = 0; j < permutations.get(i).size(); j++){
				System.out.print(permutations.get(i).get(j) + " | ");
			}
			System.out.println();
		}
	}
	
	public static String getOperatorChar(int x){
		switch(x){
			case 1:
				return "+";
			case 2: 
				return "-";
			case 3: 
				return "*";
			case 4:
				return "/";
			default:
				return "?";
		}
	}
	
	public static double operate(double x, int operation, double y){
		switch (operation) {
			case 1:
				return (double)x + y;
			case 2:
				return (double)x - y;
			case 3:
				return (double)x * y;
			case 4:
				if(y == 0){
					//Just in case of division by zero errors, return a decimal so that 
					//it can never return a perfect "24"
					return Math.PI;
				}else{
					return (double)x / y;
				}
			default:
					return 0.0;
		}
	}
	
	public static void case1(int a, int b, int c, int d){
		for(int i = 1; i < 5; i++){
			for(int j = 1; j < 5; j++){
				for(int n = 1; n < 5; n++){
					if(Math.abs(operate(a, i, operate(b, j, operate(c, n, d))) - 24) <= 10e-6){
						doesNotEqual24 = false;
						System.out.println(a + " " + getOperatorChar(i) + " (" + b + " " + getOperatorChar(j) + " (" + c + " " + getOperatorChar(n) + " " + d + ") ) = "
								+ "24");
					}
				}
			}
		}
	}
	
	public static void case2(int a, int b, int c, int d){
		for(int i = 1; i < 5; i++){
			for(int j = 1; j < 5; j++){
				for(int n = 1; n < 5; n++){
					//System.out.println(operate(operate(a, i, b), j, operate(c, n, d)));
					if(Math.abs(operate(operate(a, i, b), j, operate(c, n, d)) - 24) <= 10e-6){
						doesNotEqual24 = false;
						System.out.println("(" + a + " " + getOperatorChar(i) + " " + b + ") " + getOperatorChar(j) + " (" + c + " " + getOperatorChar(n) + " " + d + ") = "
								+ "24");
					}
				}
			}
		}
	}
	
	public static void case3(int a, int b, int c, int d){
		for(int i = 1; i < 5; i++){
			for(int j = 1; j < 5; j++){
				for(int n = 1; n < 5; n++){
					//System.out.println(operate(operate(a, i, operate(b, j, c)), n, d));
					if(Math.abs(operate(operate(a, i, operate(b, j, c)), n, d) - 24) <= 10e-6){
						doesNotEqual24 = false;
						System.out.println("(" + a + " " + getOperatorChar(i) + " (" + b + " " + getOperatorChar(j) + " " + c + ") ) " + getOperatorChar(n) + " " + d + " = "
								 + "24");
					}
				}
			}
		}
	}
	
	public static void case4(int a, int b, int c, int d){
		for(int i = 1; i < 5; i++){
			for(int j = 1; j < 5; j++){
				for(int n = 1; n < 5; n++){
					//System.out.println(operate(operate(operate(a, i, b), j, c), n, d));
					if(Math.abs(operate(operate(operate(a, i, b), j, c), n, d) - 24) <= 10e-6){
						doesNotEqual24 = false;
						System.out.println("( (" + a + " " + getOperatorChar(i) + " " + b + ") " + getOperatorChar(j) + " " + c + ") " + getOperatorChar(n) + " " + d + " = "
								+ "24");
					}
				}
			}
		}
	}
	
	public static void case5(int a, int b, int c, int d){
		for(int i = 1; i < 5; i++){
			for(int j = 1; j < 5; j++){
				for(int n = 1; n < 5; n++){
					//System.out.println(operate(a, i, operate(operate(b, j, c), n, d)));
					if(Math.abs(operate(a, i, operate(operate(b, j, c), n, d)) - 24) <= 10e-6){
						doesNotEqual24 = false;
						System.out.println(a + " " + getOperatorChar(i) + " ( (" + b + " " + getOperatorChar(j) + " " + c + ") " + getOperatorChar(n) + " " + d + ") = "
								+ "24");
					}
				}
			}
		}
	}
}