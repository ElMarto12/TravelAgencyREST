package lt.eif.viko.m.danys.restful.repos;

import lt.eif.viko.m.danys.restful.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Long> {
}
