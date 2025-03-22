package DandA.kz.strategy;

public class TakeawayStrategy implements OrderStrategy {
    @Override
    public void processOrder() {
        System.out.println("Processing takeaway order.");
    }
}