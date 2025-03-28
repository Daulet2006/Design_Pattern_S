package DandA.kz.state;

import DandA.kz.model.Order;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PREPARING")
public class PreparingState extends OrderState {

    @Override
    public void next(Order order) {
        order.setState(new ReadyState());
    }

    @Override
    public void previous(Order order) {
        order.setState(new ReceivedState());
    }

    @Override
    public String getStatus() {
        return "PREPARING";
    }
}