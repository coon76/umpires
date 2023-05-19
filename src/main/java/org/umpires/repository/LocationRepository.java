package org.umpires.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.umpires.entity.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
