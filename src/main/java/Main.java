import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Введите ваше имя");
        String customerName = input.nextLine();

        System.out.println("Введите пожалуйста какой у вас бюджет на этот вечер: ");
        int money = 0;
        try {
            money = input.nextInt();
            input.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Неверный формат ввода попробуйте еще раз.");
            input.nextLine();
        }
        Customer customer = new Customer(customerName,money);
        FoodMenu menu = new FoodMenu();

        TakeOutSimulator takeOutSimulator = new TakeOutSimulator(customer, menu, input);
        takeOutSimulator.startTakeOutSimulator();
    }
}
