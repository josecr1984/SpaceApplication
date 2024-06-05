package org.plexus.service;

import org.plexus.dto.InputSpaceShipDTO;
import org.plexus.dto.SpaceShipDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SpaceShipService {

    SpaceShipDTO  create(InputSpaceShipDTO inputSpaceShipDTO);
    SpaceShipDTO  update(Integer id, InputSpaceShipDTO inputSpaceShipDTO);
    Integer  delete(Integer id);
    Page<SpaceShipDTO> getAllSpaceShips(int page, int size);
    List<SpaceShipDTO> searchByNameContaining(String name);
    List<SpaceShipDTO>  getAll();
    SpaceShipDTO  get(Integer id);

}