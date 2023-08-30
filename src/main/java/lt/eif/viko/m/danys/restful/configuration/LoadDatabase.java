package lt.eif.viko.m.danys.restful.configuration;

import lt.eif.viko.m.danys.restful.repos.OrderRepository;
import lt.eif.viko.m.danys.restful.model.Order;
import lt.eif.viko.m.danys.restful.model.Traveler;
import lt.eif.viko.m.danys.restful.model.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(OrderRepository repository){

        Trip trip1 = new Trip("TravelTuras", "Kelione aplink Azija", "Kinija", "2 menesiai", 4000);
        Traveler traveler1 = new Traveler("Tomas", "Kiguolis");
        Order order1 = new Order("2023-10-23", List.of(traveler1), List.of(trip1));

        Trip trip2 = new Trip("TopTour", "Kelione i Amerika", "Jungtines Amerikos Valstijos", "2 savaites", 1500);
        Traveler trvMan = new Traveler("Martynas", "Danys");
        Traveler trvWom = new Traveler("Samanta", "Daniene");
        Order order2 = new Order("2024-04-10", List.of(trvMan,trvWom), List.of(trip2));


        return args -> {
            log.info("Loading" + repository.save(order1));
            log.info("Loading" + repository.save(order2));
        };

    }
}
