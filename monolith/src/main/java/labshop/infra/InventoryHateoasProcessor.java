package labshop.infra;

import labshop.domain.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

@Component
public class InventoryHateoasProcessor
    implements RepresentationModelProcessor<EntityModel<Inventory>> {

    @Override
    public EntityModel<Inventory> process(EntityModel<Inventory> model) {
        model.add(
            Link
                .of(model.getRequiredLink("self").getHref() + "/updatestock")
                .withRel("updatestock")
        );

        return model;
    }
}
