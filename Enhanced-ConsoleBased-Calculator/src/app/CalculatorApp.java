package app;
import java.util.Scanner;
import service.CalculatorService;

public class CalculatorApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CalculatorService calculator = new CalculatorService();

        System.out.println("1. Add");
        System.out.println("2. Subtract");
        System.out.println("3. Multiply");
        System.out.println("4. Divide");
        System.out.println("5. Square Root");
        System.out.println("6. Power");

        int choice = scanner.nextInt();

        try {
            switch (choice) {

                case 1: {
                    System.out.print("Enter first number: ");
                    double a = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    double b = scanner.nextDouble();
                    System.out.println("Addition: " + calculator.add(a, b));
                    break;
                }

                case 2: {
                    System.out.print("Enter first number: ");
                    double a = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    double b = scanner.nextDouble();
                    System.out.println("Subtraction: " + calculator.subtract(a, b));
                    break;
                }

                case 3: {
                    System.out.print("Enter first number: ");
                    double a = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    double b = scanner.nextDouble();
                    System.out.println("Multiplication: " + calculator.mul(a, b));
                    break;
                }

                case 4: {
                    System.out.print("Enter first number: ");
                    double a = scanner.nextDouble();
                    System.out.print("Enter second number: ");
                    double b = scanner.nextDouble();
                    System.out.println("Division: " + calculator.div(a, b));
                    break;
                }

                case 5: {
                    System.out.print("Enter number: ");
                    double a = scanner.nextDouble();
                    System.out.println("Square Root: " + calculator.sqr(a));
                    break;
                }

                case 6: {
                    System.out.print("Enter base: ");
                    double a = scanner.nextDouble();
                    System.out.print("Enter exponent: ");
                    double b = scanner.nextDouble();
                    System.out.println("Power: " + calculator.power(a, b));
                    break;
                }

                default:
                    System.out.println("Invalid choice");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}
