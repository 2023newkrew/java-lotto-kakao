package buyer;

public class Budget {
    private int budget;

    public Budget(int budget) {
        if (budget < 0) throw new IllegalArgumentException();
        
        this.budget = budget;
    }

    public int getBudget() {
        return budget;
    }

    public boolean hasMoreThan(int price) {
        return budget >= price;
    }

    public void decreaseBudget(int number) {
        if (this.budget < number) {
            throw new RuntimeException("충분한 돈이 없습니다!");
        }
        this.budget -= number;
    }
}
