package DandA.kz.strategy;

public class DineInStrategy implements OrderStrategy {
    @Override
    public void processOrder() {
        System.out.println("Processing dine-in order.");
    }
}
