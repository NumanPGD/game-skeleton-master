import java.util.Scanner;
import java.util.Random;

public class Agent2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (random.nextBoolean()) {
                System.out.println("PICK");
            } else {
                System.out.println("PASS");
            }
        }
        scanner.close();
    }
}
