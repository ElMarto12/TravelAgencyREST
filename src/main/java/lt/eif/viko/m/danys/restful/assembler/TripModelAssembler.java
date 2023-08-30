package lt.eif.viko.m.danys.restful.assembler;

import lt.eif.viko.m.danys.restful.configuration.TripController;
import lt.eif.viko.m.danys.restful.model.Trip;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TripModelAssembler implements RepresentationModelAssembler<Trip, EntityModel<Trip>> {

    @Override
    public EntityModel<Trip> toModel(Trip trip){
        return EntityModel.of(trip,
                WebMvcLinkBuilder.linkTo(methodOn(TripController.class).one(trip.getId())).withSelfRel(),
                linkTo(methodOn(TripController.class).all()).withRel("trips"));
    }

}
