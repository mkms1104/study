package strategy;

public class Member {
    private String name; // 이름
    private int accPrice; // 누적 대여 금액

    public Member(String name) {
        this.name = name;
        this.accPrice = 0;
    }

    public void addAccPrice(int price) {
        accPrice += price;
    }

    public String getName() {
        return name;
    }

    public int getAccPrice() {
        return accPrice;
    }
}
