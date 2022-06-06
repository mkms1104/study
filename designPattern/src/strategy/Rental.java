package strategy;

public class Rental {
    private Member member;
    private Book book;
    private PricePolicy pricePolicy;
    private int n;

    public Rental(Member member, Book book, PricePolicy pricePolicy, int n) {
        this.member = member;
        this.book = book;
        this.pricePolicy = pricePolicy;
        this.n = n;
    }

    public int getPrice() {
        return pricePolicy.calcPrice(book.getPrice(), n);
    }
}
