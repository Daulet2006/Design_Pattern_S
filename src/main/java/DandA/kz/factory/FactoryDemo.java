package DandA.kz.factory;

import DandA.kz.model.Order;

public class FactoryDemo {

    public static void main(String[] args) {
        OrderFactory dineInFactory = new DineInOrderFactory();
        Order dineInOrder = dineInFactory.createOrder("Иван Иванов", "123-456-789");

        OrderFactory deliveryFactory = new DeliveryOrderFactory();
        Order deliveryOrder = deliveryFactory.createOrder("Мария Смирнова", "987-654-321");

        System.out.println("Dine-In Order: " + dineInOrder);
        System.out.println("Delivery Order: " + deliveryOrder);
    }
}
