import java.util.Scanner;
import java.util.InputMismatchException;

public class NumberGame {
    public static void main(String[] args) {
        try (Scanner ob = new Scanner(System.in)) {
            int TargetNumber = (int) (Math.random() * 100);
            int Attempts = 0;

            int userNumber = 0;

            do {
                try {

                    System.out.println("Guess the number(1-100): ");
                    userNumber = ob.nextInt();

                    if (userNumber == TargetNumber) {
                        System.out.println("wooooho!! you got it Right");
                        break;

                    } else if (userNumber > TargetNumber) {
                        System.out.println("you are too far ");
                    } else {
                        System.out.println("you are too behind");

                    }
                    Attempts++;
                } catch (InputMismatchException e) {
                    System.out.println("Enter the Integer type value(1-100) to display or continue these game: ");
                    ob.nextLine();
                }

            } while (TargetNumber >= 0);

            System.out.print("The hidden number was: ");
            System.out.println(TargetNumber);
            System.out.println("you have attempted " + Attempts + " times to achieve your Target");
        }

    }
}