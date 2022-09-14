import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calc = new Calculator();
        try {calc.in(sc.nextLine());}
        catch (NumberFormatException e){
            System.out.println("Error. Only integers/Possible mismatch numbers format");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}