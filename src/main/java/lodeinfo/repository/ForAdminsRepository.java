package lodeinfo.repository;

import lodeinfo.model.ForAdminsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForAdminsRepository extends CrudRepository<ForAdminsEntity, Long> {

    Page<ForAdminsEntity> findAllByOrderByLastEditDesc(Pageable pageable);

    ForAdminsEntity save(ForAdminsEntity news);

    void delete(Long id);

    ForAdminsEntity findById(Long id);

}
