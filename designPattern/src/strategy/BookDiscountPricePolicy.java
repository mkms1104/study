package strategy;

public class BookDiscountPricePolicy implements PricePolicy {
    @Override
    public int calcPrice(int price, int n) {
        return price * n;
    }
}
