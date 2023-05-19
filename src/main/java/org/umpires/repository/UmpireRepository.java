package org.umpires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.umpires.entity.Umpire;

import java.util.List;

@Repository
public interface UmpireRepository extends JpaRepository<Umpire, Long> {
    List<Umpire> findBySeasonsSeason(int season);
    Umpire findByFirstNameAndLastName(String firstName, String lastName);
    @Query("SELECT MAX(id) as id FROM Umpire")
    long getMaxId();
}
