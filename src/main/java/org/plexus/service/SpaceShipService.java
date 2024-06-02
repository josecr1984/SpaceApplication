package org.plexus.service;

import org.plexus.dto.InputSpaceShipDTO;
import org.plexus.dto.SpaceShipDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SpaceShipService {

    public SpaceShipDTO  create(InputSpaceShipDTO inputSpaceShipDTO);
    public SpaceShipDTO  update(Integer id, InputSpaceShipDTO inputSpaceShipDTO);
    public Integer  delete(Integer id);
    Page<SpaceShipDTO> getAllSpaceShips(int page, int size);
    public List<SpaceShipDTO> searchByNameContaining(String name);
    public List<SpaceShipDTO>  getAll();
    public SpaceShipDTO  get(Integer id);

}