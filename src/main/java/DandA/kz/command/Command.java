package DandA.kz.command;


import DandA.kz.model.Order;

public interface Command {
    void execute(Order order);
}
