package DandA.kz.state;

import DandA.kz.model.Order;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DELIVERED")
public class DeliveredState extends OrderState {

    @Override
    public void next(Order order) {
        // Cannot move forward from delivered state
        System.out.println("Заказ уже доставлен. Невозможно изменить состояние.");
    }

    @Override
    public void previous(Order order) {
        // Move back to READY state
        order.setState(new ReadyState());
    }

    @Override
    public String getStatus() {
        return "DELIVERED";
    }
}