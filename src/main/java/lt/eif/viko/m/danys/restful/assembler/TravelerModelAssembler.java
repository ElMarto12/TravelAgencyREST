package lt.eif.viko.m.danys.restful.assembler;

import lt.eif.viko.m.danys.restful.configuration.TravelerController;
import lt.eif.viko.m.danys.restful.model.Traveler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TravelerModelAssembler implements RepresentationModelAssembler<Traveler, EntityModel<Traveler>> {

    @Override
    public EntityModel<Traveler> toModel(Traveler traveler){
        return EntityModel.of(traveler,
                WebMvcLinkBuilder.linkTo(methodOn(TravelerController.class).one(traveler.getId())).withSelfRel(),
                linkTo(methodOn(TravelerController.class).all()).withRel("travelers"));
    }
}
