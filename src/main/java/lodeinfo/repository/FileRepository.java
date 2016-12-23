package lodeinfo.repository;

import lodeinfo.model.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long>{

    FileEntity findById(Long id);

    FileEntity saveAndFlush(FileEntity fileEntity);

    void delete(Long id);
}
