package org.umpires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.umpires.entity.UmpireSeason;

@Repository
public interface UmpireSeasonRepository extends JpaRepository<UmpireSeason, Integer> {
    @Query("SELECT MAX(id) as id FROM UmpireSeason")
    long getMaxId();
}
