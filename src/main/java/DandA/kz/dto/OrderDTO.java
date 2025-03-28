package DandA.kz.dto;

import DandA.kz.model.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDTO {
    private Long id;
    private String customerName;
    private String contactInfo;
    private OrderStatus status;
    private String orderType;
    private LocalDateTime orderTime;
    private Long customerId; // Add this field
    private Long stateId;    // Add this field
}