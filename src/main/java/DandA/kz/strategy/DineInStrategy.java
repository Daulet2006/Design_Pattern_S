package DandA.kz.strategy;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DINE_IN")
public class DineInStrategy extends OrderStrategyEntity {

    @Override
    public void processOrder() {
        System.out.println("Processing dine-in order.");
    }
}