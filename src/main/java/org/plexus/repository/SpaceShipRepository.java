package org.plexus.repository;

import org.plexus.model.enums.SpaceShip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SpaceShipRepository extends CrudRepository<SpaceShip, Integer>, JpaRepository<SpaceShip, Integer> {

    @Query("SELECT s FROM SpaceShip s WHERE s.name LIKE %:name%")
    List<SpaceShip> findByNameContaining(String name);


}
