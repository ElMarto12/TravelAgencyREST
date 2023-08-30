package lt.eif.viko.m.danys.restful.configuration;

import lt.eif.viko.m.danys.restful.repos.TravelerRepository;
import lt.eif.viko.m.danys.restful.assembler.TravelerModelAssembler;
import lt.eif.viko.m.danys.restful.exception.TravelerNotFoundException;
import lt.eif.viko.m.danys.restful.model.Traveler;
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
public class TravelerController {

    private final TravelerRepository repository;
    private final TravelerModelAssembler assembler;

    TravelerController(TravelerRepository repository, TravelerModelAssembler assembler){

        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/travelers")
    public CollectionModel<EntityModel<Traveler>> all(){

        List<EntityModel<Traveler>> travelers = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(travelers, linkTo(methodOn(TravelerController.class).all()).withSelfRel());
    }

    @GetMapping("/travelers/{id}")
    public EntityModel<Traveler> one(@PathVariable Long id){

        Traveler traveler = repository.findById(id)
                .orElseThrow(() -> new TravelerNotFoundException(id));

        return assembler.toModel(traveler);
    }

    @PutMapping("/travelers/{id}")
    ResponseEntity<?> updateTraveler(@RequestBody Traveler newTraveler, @PathVariable Long id){

        Traveler updatedTraveler = repository.findById(id)
                .map(traveler ->{
                    traveler.setFirstName(newTraveler.getFirstName());
                    traveler.setLastName(newTraveler.getLastName());
                    return repository.save(traveler);
                })
                .orElseGet(()->{
                    newTraveler.setId(id);
                    return repository.save(newTraveler);
                });

        EntityModel<Traveler> entityModel = assembler.toModel(updatedTraveler);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PostMapping("/travelers")
    ResponseEntity<?> newTraveler(@RequestBody Traveler newTraveler){

        EntityModel<Traveler> entityModel = assembler.toModel(repository.save(newTraveler));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/travelers/{id}")
    ResponseEntity<?> deleteTraveler(@PathVariable Long id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
