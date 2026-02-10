import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private String owner;
    private List<String> cards;
    private int totalMoney;

    public Wallet() {
        this.cards = new ArrayList<>();
        this.totalMoney = 0;
    }

    // set & get owner
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    // kartu
    public void addCard(String card) {
        cards.add(card);
    }

    public String takeCard(String card) {
        if (cards.contains(card)) {
            cards.remove(card);
            return card;
        }
        return null;
    }

    public List<String> getCards() {
        return cards;
    }

    // uang
    public void addMoney(int amount) {
        if (amount > 0) {
            totalMoney += amount;
        }
    }

    public boolean takeMoney(int amount) {
        if (amount > 0 && totalMoney >= amount) {
            totalMoney -= amount;
            return true;
        }
        return false;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

}
