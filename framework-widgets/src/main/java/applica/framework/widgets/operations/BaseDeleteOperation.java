package applica.framework.widgets.operations;

import applica.framework.Entity;
import applica.framework.RepositoriesFactory;
import applica.framework.Repository;
import applica.framework.library.utils.ProgramException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseDeleteOperation implements DeleteOperation {

    @Autowired
    private RepositoriesFactory repositoriesFactory;

    private Repository repository;
    private Class<? extends Entity> entityType;

    protected void init() {
        repository = repositoriesFactory.createForEntity(getEntityType());
    }

    @Override
    public void delete(String id) throws OperationException {
        if (getEntityType() == null) throw new ProgramException("Entity entityType is null");

        if (repository == null) {
            init();
        }

        if (repository == null) throw new ProgramException("Missing repository");

        try {
            repository.delete(id);
        } catch (Exception e) {
            throw new OperationException(e);
        }
    }

    @Override
    public void delete(List<String> ids) throws OperationException {
        if (entityType == null) throw new ProgramException("Entity entityType is null");

        if (repository == null) {
            init();
        }

        if (repository == null) throw new ProgramException("Missing repository");

        for (String id : ids) {
            try {
                repository.delete(id);
            } catch (Exception e) {
                throw new OperationException(e);
            }
        }
    }

    public Class<? extends Entity> getEntityType() {
        return entityType;
    }

    public void setEntityType(Class<? extends Entity> entityType) {
        this.entityType = entityType;
    }
}
