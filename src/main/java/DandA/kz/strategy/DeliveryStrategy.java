package DandA.kz.strategy;

public class DeliveryStrategy implements OrderStrategy {
    @Override
    public void processOrder() {
        System.out.println("Processing delivery order.");
    }
}
