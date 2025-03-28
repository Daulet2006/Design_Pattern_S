package DandA.kz.command;

import DandA.kz.model.Order;


import org.springframework.stereotype.Component; // Add this annotation

@Component // This makes it a Spring bean
public class OrderCommandExecutor {

    public void executeCommand(Command command, Order order) {
        command.execute(order);
    }
}
