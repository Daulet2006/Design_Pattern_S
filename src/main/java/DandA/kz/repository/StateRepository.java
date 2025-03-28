package DandA.kz.repository;

import DandA.kz.state.OrderState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<OrderState, Long> {
}