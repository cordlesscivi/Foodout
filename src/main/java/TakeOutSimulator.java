import java.util.Scanner;
import java.util.InputMismatchException;

public class TakeOutSimulator {
    private Customer customer;
    private FoodMenu menu;
    private Scanner input;

    public TakeOutSimulator(Customer customer, FoodMenu menu, Scanner input) {
        this.customer = customer;
        this.menu = menu;
        this.input = input;
    }

    private <T> T getResponse(String userInputPrompt, UserInputRetriever<T> userInputRetriever) {
        while (true) {
            System.out.println(userInputPrompt);
            try {
                int userInput = input.nextInt();
                return userInputRetriever.produceOutput(userInput);
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введенные данные должны быть целыми числами. Попробуйте еще раз.");
                input.nextLine();
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка: введенный номер недействителен. Попробуйте снова.");
            }
        }
    }

    public boolean shouldSimulate() {
        String userPrompt = "Введите 1 продолжить или 0 выйти";


        UserInputRetriever<Boolean> retriever = new UserInputRetriever<Boolean>() {
            @Override
            public Boolean produceOutput(int selection) {
                if (selection == 1) {

                    if (customer.getMoney() >= menu.getLowestCostFood().getPrice()) {
                        return true;
                    } else {
                        System.out.println("К сожалению у вас закончились средства...");
                        return false;
                    }
                } else if (selection == 0) {
                    return false;
                } else {
                    throw new IllegalArgumentException();
                }
            }
        };


        return getResponse(userPrompt, retriever);
    }

    public Food getMenuSelection() {
        System.out.println("\n" + menu);
        String userPrompt = "Выберите блюдо: ";

        UserInputRetriever<Food> retriever = new UserInputRetriever<Food>() {
            @Override
            public Food produceOutput(int selection) {
                Food food = menu.getFood(selection);
                if (food == null) {
                    throw new IllegalArgumentException();
                }
                return food;
            }
        };

        return getResponse(userPrompt, retriever);
    }

    public Boolean isStillOrderingFood() {
        String userPrompt = "Введите 1-что бы продолжить или 0-оформить заказ";

        UserInputRetriever<Boolean> retriever = new UserInputRetriever<Boolean>() {
            @Override
            public Boolean produceOutput(int selection) {
                if (selection == 1) {
                    return true;
                } else if (selection == 0) {
                    return false;
                } else {
                    throw new IllegalArgumentException(); // 
                }
            }
        };
        return getResponse(userPrompt, retriever);
    }

    public void checkoutCustomer(ShoppingBag<Food> shoppingBag){
        System.out.println("Ваш платеж обрабатывается ожидайте...\n");
        int finalMoney = customer.getMoney() - shoppingBag.getTotalPrice();
        customer.setMoney(finalMoney);
        System.out.println("Ваш остаток на счете " + finalMoney);
        System.out.println("Приятного вам вечера) ");
    }

    public void takeOutPrompt(){
        ShoppingBag<Food> shoppingBag = new ShoppingBag<>();
        int customerMoneyLeft = customer.getMoney();
        while(true){
            System.out.println("У вас на счету: " + customerMoneyLeft);
            Food choosingFood = getMenuSelection();

            if(customerMoneyLeft >= choosingFood.getPrice()){

                customerMoneyLeft = customerMoneyLeft - choosingFood.getPrice();
                shoppingBag.addItem(choosingFood);

            }else{System.out.println("К сожалению у вас нету денежных средств на покупку данного блюда выберете другое");}

            if (!isStillOrderingFood()) {
                break;
            }
        }
        checkoutCustomer(shoppingBag);
    }
    public void startTakeOutSimulator(){
        System.out.println("Hello, welcome to my restaurant!");
        while (shouldSimulate()) {
            System.out.println("Welcome " + customer.getName() + "!");
            takeOutPrompt();
        }
    }
}