package lodeinfo.repository;

import lodeinfo.model.NewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<NewsEntity, Long> {

    @Query("select new Map(n.id as id, n.title as title, n.text as text, n.createdOn as createdOn, n.lastEdit as lastEdit) from NewsEntity as n")
    Page<NewsEntity> findAllByOrderByLastEditDesc(Pageable pageable);

    NewsEntity save(NewsEntity news);

    void delete(Long id);

    NewsEntity findById(Long id);

}
