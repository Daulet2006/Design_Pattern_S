package DandA.kz.command;

import DandA.kz.model.Order;

public class OrderCommandExecutor {

    public void executeCommand(Command command, Order order) {
        command.execute(order);
    }
}
