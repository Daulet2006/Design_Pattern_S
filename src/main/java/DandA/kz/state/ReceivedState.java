package DandA.kz.state;

import DandA.kz.model.Order;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("RECEIVED")
public class ReceivedState extends OrderState {

    @Override
    public void next(Order order) {
        order.setState(new PreparingState());
    }

    @Override
    public void previous(Order order) {
        // Cannot move backward from received state
        System.out.println("Заказ уже на начальной стадии.");
    }

    @Override
    public String getStatus() {
        return "RECEIVED";
    }
}