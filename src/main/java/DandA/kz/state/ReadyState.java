package DandA.kz.state;

import DandA.kz.model.Order;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("READY")
public class ReadyState extends OrderState {

    @Override
    public void next(Order order) {
        order.setState(new DeliveredState()); // Transition to DeliveredState
    }

    @Override
    public void previous(Order order) {
        order.setState(new PreparingState()); // Transition back to PreparingState
    }

    @Override
    public String getStatus() {
        return "READY";
    }
}