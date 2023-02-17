package de.cloudogu;

import io.smallrye.common.annotation.Blocking;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import java.util.List;


@Blocking
@Path("/my-entity")
public class MyEntityResource {

    private final MyEntityRepository repo;
    public MyEntityResource(MyEntityRepository repo) {
        this.repo = repo;
    }

    @GET
    public List<MyEntity> getAll() {
        return repo.getAll();
    }

    @GET
    @Path("/{id}")
    public MyEntity get(long id) {
        var entity = repo.getOrNull(id);
        if (entity == null) throw new NotFoundException();
        return entity;
    }

    @POST
    public long create(MyEntity newEntity) {
        newEntity.id = null;
        return repo.create(newEntity);
    }

    @PUT
    @Path("/{id}")
    public void replace(long id, MyEntity updatedEntity) {
        updatedEntity.id = id;
        repo.update(updatedEntity);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void delete(long id) {
        repo.delete(id);
    }
}