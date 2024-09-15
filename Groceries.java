package Package;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Groceries {
	
	// This method calculates the total cost for each person
	public static HashMap<String, Double> getTotals(HashMap<String [], Double> purchases ) {
		HashMap<String, Double> totals = new HashMap<String, Double>();
		// Loop through each purchase
		for (Entry<String [], Double> entry : purchases.entrySet()) {
			// Calculate the cost per person for the current purchase
			double splitCost = entry.getValue() / entry.getKey().length;
			// Add the cost to each person's total
			for (String name : entry.getKey()) {
				if (!totals.containsKey(name)) totals.put(name, 0.0);
				totals.put(name, totals.get(name) + splitCost);
			}
		}
		return totals;
	}

	public static void main(String[] args) {
		HashMap<String [], Double> purchases = new HashMap<String[], Double>();
		Scanner scan = new Scanner(System.in);
		double total = 0.0;
		// infinite loop until the user breaks 
		while (true) {
			System.out.println("Enter a grocery cost: ");
			// ensures cost input is a double
			try {
				Double inputCost = Double.valueOf(scan.nextLine());
				// track total cost
				total += inputCost;
				String names = "";
				while (true) {
	                System.out.println("Enter who is paying for the item: ");
	                names = scan.nextLine();
	                // checks if names have numbers in them
	                if (names.matches(".*\\d.*")) {
	                    System.out.println("Names should not contain numbers. Please enter the names again.");
	                } else {
	                    break;
	                }
	            }
				String [] namesList = names.split("\\s+");
				// populate HashMap with names of those that purchased as keys and the cost of the item as values
				purchases.put(namesList, inputCost);
			} catch (NumberFormatException e) {
				System.out.println("Wrong input. Please enter a valid number for the grocery cost.");
	            continue;
			}
			
			System.out.println("Enter 'x' to quit or 'enter' to continue");
			String quit = scan.nextLine();
			if (quit.equals("x")) {
				break;
			} else {
				continue;
			}	
		}
		HashMap<String, Double> totals = getTotals(purchases);
		// loop through each person and output their total
		for (Entry<String, Double> entry : totals.entrySet()) {
			System.out.printf("%s's total is " + "$%.2f\n", entry.getKey(), entry.getValue());
		}
		System.out.printf("Total grocery bill is $%.2f", total);
		scan.close();
	}
}