package lodeinfo.repository;

import lodeinfo.model.NewsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<NewsEntity, Long> {

    // Query - because need to get only text without files. new Map for enable column names, without column is antonymous
//    @Query("select new Map(n.id as id, n.title as title, n.text as text, n.createdOn as createdOn, n.lastEdit as lastEdit, n.forWhom as forWhom) " +
//            "from NewsEntity as n where n.forWhom = 'all' order by n.lastEdit desc")
//    Page<NewsEntity> findNewsForAll(Pageable pageable);

    NewsEntity save(NewsEntity news);

    void delete(Long id);

    NewsEntity findById(Long id);

    @Query("select new Map(n.id as id, n.title as title, n.text as text, n.createdOn as createdOn, n.lastEdit as lastEdit, n.forWhom as forWhom) " +
            "from NewsEntity as n where n.forWhom = ?1 order by n.lastEdit desc")
    Page<NewsEntity> findNewsForSomeone(String someone, Pageable pageable);

}
