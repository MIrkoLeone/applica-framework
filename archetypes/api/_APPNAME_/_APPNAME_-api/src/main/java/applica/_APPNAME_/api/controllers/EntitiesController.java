package applica._APPNAME_.api.controllers;

import applica._APPNAME_.api.responses.ResponseCode;
import applica.framework.*;
import applica.framework.widgets.entities.EntitiesRegistry;
import applica.framework.library.responses.Response;
import applica.framework.library.responses.ValueResponse;
import applica.framework.widgets.entities.EntityDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Created by bimbobruno on 06/12/2016.
 */
@RestController
@RequestMapping("/entities/{entity}")
public class EntitiesController {

    @Autowired
    private RepositoriesFactory repositoriesFactory;

    @Autowired
    private ApplicationContext context;

    @GetMapping("")
    public Response getEntities(@PathVariable("entity") String id, String queryJson) {
        try {
            Optional<EntityDefinition> definition = EntitiesRegistry.instance().get(id);
            if (definition.isPresent()) {
                Repository repository = repositoriesFactory.createForEntity(definition.get().getType());
                Query query = Query.fromJSON(queryJson);
                Result result = repository.find(query);
                return new ValueResponse(result);
            } else {
                return new Response(ResponseCode.NOT_FOUND);
            }
        } catch (Exception e) {
            return new Response(Response.ERROR);
        }
    }
}
