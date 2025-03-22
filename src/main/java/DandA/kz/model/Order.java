package DandA.kz.model;

import DandA.kz.state.OrderState;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String contactInfo;  // Добавлено поле для контактной информации

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String orderType;  // DineIn, Takeaway, Delivery

    private LocalDateTime orderTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    private OrderState state;


}

