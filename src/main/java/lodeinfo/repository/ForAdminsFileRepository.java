package lodeinfo.repository;

import lodeinfo.model.ForAdminsFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForAdminsFileRepository extends JpaRepository<ForAdminsFileEntity, Long>{

    ForAdminsFileEntity findById(Long id);

    ForAdminsFileEntity saveAndFlush(ForAdminsFileEntity fileEntity);

    void delete(Long id);
}
