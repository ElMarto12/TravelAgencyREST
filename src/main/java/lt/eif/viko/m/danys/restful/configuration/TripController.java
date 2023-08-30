package lt.eif.viko.m.danys.restful.configuration;

import lt.eif.viko.m.danys.restful.assembler.TripModelAssembler;
import lt.eif.viko.m.danys.restful.exception.TripNotFoundException;
import lt.eif.viko.m.danys.restful.model.Trip;
import lt.eif.viko.m.danys.restful.repos.TripRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class TripController {

    private final TripRepository repository;
    private final TripModelAssembler assembler;

    TripController(TripRepository repository, TripModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/trips")
    public CollectionModel<EntityModel<Trip>> all(){
        List<EntityModel<Trip>> trips = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(trips, linkTo(methodOn(TripController.class).all()).withSelfRel());
    }

    @GetMapping("/trips/{id}")
    public EntityModel<Trip> one(@PathVariable Long id){
        Trip trip = repository.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));

        return assembler.toModel(trip);
    }

    @PostMapping("/trips")
    ResponseEntity<?> newTrip(@RequestBody Trip newTrip){

        EntityModel<Trip> entityModel = assembler.toModel(repository.save(newTrip));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping("/trips/{id}")
    ResponseEntity<?> updateTrip(@RequestBody Trip newTrip, @PathVariable Long id){

        Trip updatedTrip = repository.findById(id)
                .map(trip ->{
                    trip.setOrganizationName(newTrip.getOrganizationName());
                    trip.setName(newTrip.getName());
                    trip.setLocation(newTrip.getLocation());
                    trip.setTripDuration(newTrip.getTripDuration());
                    trip.setCost(newTrip.getCost());

                    return repository.save(trip);
                })
                .orElseGet(()->{
                    newTrip.setId(id);
                    return repository.save(newTrip);
                });

        EntityModel<Trip> entityModel = assembler.toModel(updatedTrip);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/trips/{id}")
    ResponseEntity<?> deleteTrip(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
