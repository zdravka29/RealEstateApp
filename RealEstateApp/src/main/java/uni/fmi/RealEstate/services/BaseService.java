package uni.fmi.RealEstate.services;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.fmi.RealEstate.models.MainModel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<U extends MainModel> {
    protected abstract JpaRepository<U, Long> getRepo();
    public List<U> findAll(){ return getRepo().findAll(); }
    public Optional<U> getEntity(Long id){ return getRepo().findById(id); }
    public U create(U entity) {
        entity.setCreatedAt(LocalDateTime.now());
        return getRepo().save(entity);
    }

    public U update(U entity) {
        long id = entity.getId();
        Optional<U> optionalEntity = getRepo().findById(id);
        if (optionalEntity.isPresent()){
           entity.setUpdatedAt(LocalDateTime.now());
           return getRepo().save(entity);
        }
        return null;
    }

    public boolean remove(long id) {
        Optional<U> optionalCategory = getRepo().findById(id);
        if (optionalCategory.isPresent()){
            getRepo().delete(optionalCategory.get());
            return true;
        }
        return false;
    }
}
