package lt.eif.viko.m.danys.restful.repos;

import lt.eif.viko.m.danys.restful.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
