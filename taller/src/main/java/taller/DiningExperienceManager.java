package taller;

import java.util.Scanner;


public class DiningExperienceManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Define the menu and prices
        String[] menu = {"Burger", "Pizza", "Pasta", "Salad", "Soup"};
        double[] prices = {10.0, 12.0, 15.0, 8.0, 6.0};

        // Define variables for order
        int[] quantities = new int[menu.length];
        int totalQuantity = 0;
        double totalCost = 0.0;

        // Display menu and get order
        System.out.println("Welcome to the Dining Experience Manager!");
        System.out.println("Here is our menu:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i+1) + ". " + menu[i] + " - $" + prices[i]);
        }
        System.out.println("Please enter the number of the meal you would like to order, followed by the quantity.");
        System.out.println("For example, to order 2 burgers, enter '1 2'.");
        System.out.println("When you are finished ordering, enter '0 0'.");

        int mealNumber;
        int quantity;
        boolean validOrder = true;
        while (validOrder) {
            mealNumber = scanner.nextInt();
            quantity = scanner.nextInt();

            if (mealNumber == 0 && quantity == 0) {
                break;
            }

            // Validate meal number
            if (mealNumber < 1 || mealNumber > menu.length) {
                System.out.println("Invalid meal number. Please try again.");
                continue;
            }

            // Validate quantity
            if (quantity <= 0 || quantity > 100) {
                System.out.println("Invalid quantity. Please enter a positive integer between 1 and 100.");
                continue;
            }

            // Add meal to order
            quantities[mealNumber-1] += quantity;
            totalQuantity += quantity;
            totalCost += prices[mealNumber-1] * quantity;

            // Check meal availability
            if (quantities[mealNumber-1] > 10) {
                System.out.println("Sorry, we have run out of " + menu[mealNumber-1] + ". Please select a different meal.");
                quantities[mealNumber-1] -= quantity;
                totalQuantity -= quantity;
                totalCost -= prices[mealNumber-1] * quantity;
            }
        }

        // Calculate cost
        if (totalQuantity > 5 && totalQuantity <= 10) {
            totalCost *= 0.9;
        } else if (totalQuantity > 10) {
            totalCost *= 0.8;
        }
        if (totalCost > 100) {
            totalCost -= 25.0;
        } else if (totalCost > 50) {
            totalCost -= 10.0;
        }
        totalCost += 5.0;

        // Confirm order
        System.out.println("You have ordered:");
        for (int i = 0; i < menu.length; i++) {
            if (quantities[i] > 0) {
                System.out.println(quantities[i] + " " + menu[i]);
            }
        }
        System.out.println("Total cost: $" + totalCost);
        System.out.println("Enter '1' to confirm your order, or '0' to cancel and make changes.");
        int confirmation = scanner.nextInt();
        if (confirmation == 0) {
            totalCost = -1.0;
        }

        // Output result
        if (totalCost == -1.0) {
            System.out.println("Order canceled.");
        } else {
            System.out.println("Thank you for your order! Your total cost is $" + totalCost + ".");
        }
    }
}
