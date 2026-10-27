import java.util.ArrayList;
import java.util.List;



public class FoodMenu {
    private List<Food> menu;

    public FoodMenu() {
        this.menu = new ArrayList<>();
        menu.add(new Food("Бургер", "Сочный мясной бургер", 10));
        menu.add(new Food("Пицца", "Очень много сыра и пеперони", 15));
        menu.add(new Food("Салат", "Вкусный освежающий", 8));
    }

    @Override
    public String toString() {
        String total = "";
        for (int i = 0; i < menu.size(); i++) {
            total = total + (i + 1) + ". " + menu.get(i) + "\n";
        }
        return total;
    }

    public Food getFood(int index) {
        int adjustedIndex = index - 1;
        if (adjustedIndex >= 0 && adjustedIndex < menu.size()) {
            return menu.get(adjustedIndex);
        } else {
            return null;
        }
    }

    public Food getLowestCostFood() {
        if (menu.isEmpty()) {
            return null;
        }
        Food lowestCostFood = menu.get(0);
        for (int i = 1; i < menu.size(); i++) {
            Food currentFood = menu.get(i);
            if (currentFood.getPrice() < lowestCostFood.getPrice()) {
                lowestCostFood = currentFood;
            }
        }
        return lowestCostFood;
    }
}