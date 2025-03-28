package DandA.kz.strategy;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("TAKEAWAY")
public class TakeawayStrategy extends OrderStrategyEntity {

    @Override
    public void processOrder() {
        System.out.println("Processing takeaway order.");
    }
}