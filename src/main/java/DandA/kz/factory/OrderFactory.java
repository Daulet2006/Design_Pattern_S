package DandA.kz.factory;

import DandA.kz.model.Order;

public abstract class OrderFactory {
    public abstract Order createOrder(String customerName, String contactInfo);
}
