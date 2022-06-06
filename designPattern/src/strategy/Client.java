package strategy;

public class Client {
    public static void main(String[] args) {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        Book book1 = new Book("book1", 2001, 1000);
        Book book2 = new Book("book2", 2002, 2000);
        Book book3 = new Book("book3", 2003, 3000);

        Rental rental1 = new Rental(member1, book1, new OrdinaryPricePolicy(), 1);
        Rental rental2 = new Rental(member2, book2, new BookDiscountPricePolicy(), 2);
        Rental rental3 = new Rental(member1, book3, new MemberDiscountPricePolicy(), 3);

        System.out.println(rental1.getPrice());
        System.out.println(rental2.getPrice());
        System.out.println(rental3.getPrice());
    }
}
