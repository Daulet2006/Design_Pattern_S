package DandA.kz.strategy;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DELIVERY")
public class DeliveryStrategy extends OrderStrategyEntity {

    @Override
    public void processOrder() {
        System.out.println("Processing delivery order.");
    }
}