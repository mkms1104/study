package strategy;

public class MemberDiscountPricePolicy implements PricePolicy {
    @Override
    public int calcPrice(int price, int n) {
        return price * n ;
    }
}
