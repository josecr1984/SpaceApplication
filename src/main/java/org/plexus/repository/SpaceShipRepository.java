package org.plexus.repository;

import org.plexus.model.SpaceShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SpaceShipRepository extends JpaRepository<SpaceShip, Integer> {
    List<SpaceShip> findByNameContainingIgnoreCase(String name);

    List<SpaceShip> findByNameContaining(String name);

}
