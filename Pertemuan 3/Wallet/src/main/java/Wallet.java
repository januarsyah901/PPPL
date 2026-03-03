import java.util.ArrayList;
import java.util.List;

public class Wallet {
    private Owner owner;
    private List<String> cards;
    private int totalMoney;

    public Wallet() {
        this.cards = new ArrayList<>();
        this.totalMoney = 0;
    }

    // set & get owner
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
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
    public void deposit(int amount) {
        if (amount > 0) {
            totalMoney += amount;
        }
    }

    public boolean withdraw(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
        if (amount > totalMoney) {
            throw new InsufficientFundsException("Amount cannot be greater than total money");
        }
        if (amount == 0) {
            throw new IllegalArgumentException("Amount cannot be zero");
        }
        totalMoney -= amount;
        return false;
    }

    public boolean validBalance(int amount) {
        if (amount < 0) {
            return false;
        }
        return true;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

}
