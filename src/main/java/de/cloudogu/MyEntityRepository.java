package de.cloudogu;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional
public class MyEntityRepository {

    private final EntityManager em;

    public MyEntityRepository(EntityManager em) {
        this.em = em;
    }

    public MyEntity getOrNull(long id) {
        return em.find(MyEntity.class, id);
    }

    public List<MyEntity> getAll() {
        return em.createQuery("SELECT e FROM MyEntity e", MyEntity.class).getResultList();
    }

    public long create(MyEntity newEntity) {
        var mergedEntity = em.merge(newEntity);
        em.flush();
        return mergedEntity.id;
    }

    public void update(MyEntity newEntity) {
        em.merge(newEntity);
        em.flush();
    }

    public void delete(long id) {
        var entity = em.find(MyEntity.class, id);
        if (entity != null) {
            em.remove(entity);
        }
    }

}
