package DandA.kz.repository;

import DandA.kz.strategy.OrderStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StrategyRepository extends JpaRepository<OrderStrategy, Long> {
}
