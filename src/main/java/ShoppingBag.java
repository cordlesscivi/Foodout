import java.util.HashMap;
import java.util.Map;

// Create ShoppingBag class here

public class ShoppingBag<T extends PricedItem<Integer>> {
    private Map<T, Integer> shoppingBag;

    public ShoppingBag() {
        this.shoppingBag = new HashMap<>();
    }

    public void addItem(T item) {
        if (shoppingBag.get(item) != null) {
            shoppingBag.put(item, shoppingBag.get(item) + 1);
        } else {
            shoppingBag.put(item, 1);
        }
    }

    public Integer getTotalPrice() {
        int totalPrice = 0;
        for (T item : shoppingBag.keySet()) {
            int itemPrice = item.getPrice();
            int quantity = shoppingBag.get(item);
            totalPrice = totalPrice + (itemPrice * quantity);
        }
        return totalPrice;
    }
}