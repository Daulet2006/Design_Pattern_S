package DandA.kz.state;

import DandA.kz.model.Order;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CANCELED")
public class CanceledState extends OrderState {

    @Override
    public void next(Order order) {
        // Cannot move forward from canceled state
        System.out.println("Заказ уже отменен. Невозможно изменить состояние.");
    }

    @Override
    public void previous(Order order) {
        // Cannot move backward from canceled state
        System.out.println("Заказ уже отменен. Невозможно изменить состояние.");
    }

    @Override
    public String getStatus() {
        return "CANCELED";
    }
}