package DandA.kz.service;

import DandA.kz.dto.OrderDTO;
import DandA.kz.model.Order;
import DandA.kz.repository.StateRepository;
import DandA.kz.state.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DtoMapper {

    @Autowired
    private StateRepository stateRepository;

    public OrderDTO toOrderDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomerName());
        dto.setContactInfo(order.getContactInfo());
        dto.setStatus(order.getStatus());
        dto.setOrderType(order.getOrderType());
        dto.setOrderTime(order.getOrderTime());

        // Handle null customer
        if (order.getCustomer() != null) {
            dto.setCustomerId(order.getCustomer().getId());
        }

        // Handle null state
        if (order.getState() != null) {
            dto.setStateId(order.getState().getId());
        }

        return dto;
    }

    public Order toOrderEntity(OrderDTO orderDTO) {
        Order order = new Order();
        order.setId(orderDTO.getId());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setContactInfo(orderDTO.getContactInfo());
        order.setStatus(orderDTO.getStatus());
        order.setOrderType(orderDTO.getOrderType());
        order.setOrderTime(orderDTO.getOrderTime());

        // Fetch and set OrderState
        if (orderDTO.getStateId() != null) {
            OrderState state = stateRepository.findById(orderDTO.getStateId())
                    .orElseThrow(() -> new RuntimeException("State not found with ID: " + orderDTO.getStateId()));
            order.setState(state);
        }

        return order;
    }
}