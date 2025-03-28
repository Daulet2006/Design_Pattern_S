package DandA.kz.model;

import DandA.kz.state.OrderState;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @NonNull
    private String customerName;
    private String contactInfo;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String orderType;
    private LocalDateTime orderTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_id")
    private OrderState state; // This maps to the state_id column in the database

    @Transient // This field is not persisted in the database
    private Long stateId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;


    public Long getCustomerId() {
        return customer != null ? customer.getId() : null;
    }
}