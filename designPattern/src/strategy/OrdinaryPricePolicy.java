package strategy;

public class OrdinaryPricePolicy implements PricePolicy {
    @Override
    public int calcPrice(int price, int n) {
        return price * n;
    }
}
