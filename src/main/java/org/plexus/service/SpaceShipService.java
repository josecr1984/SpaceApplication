package org.plexus.service;

import org.plexus.dto.SpaceShipDTO;

import java.util.List;

public interface SpaceShipService {

    public List<SpaceShipDTO>  getAll();

    public SpaceShipDTO  get(Integer id);


    public SpaceShipDTO  update(Integer id, SpaceShipDTO updateShapeShipDTO);

    public Integer  delete(Integer id);

    public SpaceShipDTO  create(SpaceShipDTO spaceShipDTO);


    List<SpaceShipDTO> searchByNameContaining(String name);
}
