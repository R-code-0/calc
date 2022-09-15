import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        calculate();
    }

    private static void calculate() {
        StringBuilder set = new StringBuilder(sc.nextLine());
        Calculator calc = new Calculator();
        do {
            try {
                calc.in(set.toString());
            } catch (NumberFormatException e) {
                System.out.println("Error: use only integers or possible mismatch numbers format");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("End to stop");
            set.replace(0, set.length(), sc.nextLine());
        } while (!set.toString().equalsIgnoreCase("end"));
    }
}