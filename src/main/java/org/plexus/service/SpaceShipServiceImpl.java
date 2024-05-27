package org.plexus.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.plexus.dto.SpaceShipDTO;
import org.plexus.error.ResourceNotFoundException;
import org.plexus.model.enums.SpaceShip;
import org.plexus.repository.SpaceShipRepository;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SpaceShipServiceImpl implements SpaceShipService {


    private final SpaceShipRepository spaceShipRepository;
    private final ModelMapper modelMapper;

    @Override
    @Cacheable(value="spaceships")
    public List<SpaceShipDTO> getAll() {
        return spaceShipRepository.findAll().stream().map(shapeShip -> modelMapper.map(shapeShip,SpaceShipDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Cacheable(value = "spaceships", key = "#id")
    public SpaceShipDTO get(Integer id) {
        SpaceShip spaceShip =  spaceShipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can not be found SpaceShip with id :" + id));
        return modelMapper.map(spaceShip,SpaceShipDTO.class);
    }

    @Override
    @CachePut(value = "spaceships", key = "#id")
    @CacheEvict(value = "spaceships", key = "'all'")
    public SpaceShipDTO update(Integer id, SpaceShipDTO updateShapeShipDTO) {
        spaceShipRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Can not be found SpaceShip with id :" + id));
        updateShapeShipDTO.setId(id);
        SpaceShip spaceShip = modelMapper.map(updateShapeShipDTO,SpaceShip.class);
        return modelMapper.map(spaceShipRepository.save(spaceShip),SpaceShipDTO.class);
    }

    @Override
    @CacheEvict(value = "spaceships", allEntries = true)
    public Integer delete(Integer id) {
        SpaceShip spaceShip = spaceShipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Can not be found SpaceShip with id :" + id));
        spaceShipRepository.delete(spaceShip);
        return id;
    }

    @Override

    @CachePut(value = "spaceships", key = "#result.id")
    @CacheEvict(value = "spaceships", key = "'all'")
    public SpaceShipDTO create(SpaceShipDTO spaceShipDTO) {
        SpaceShip spaceShip = modelMapper.map(spaceShipDTO, SpaceShip.class);
        return modelMapper.map(spaceShipRepository.save(spaceShip),SpaceShipDTO.class);
    }

    @Override
    public List<SpaceShipDTO> searchByNameContaining(String name) {
        return  spaceShipRepository.findByNameContaining(name).stream().map(shapeShip -> modelMapper.map(shapeShip,SpaceShipDTO.class))
                .collect(Collectors.toList());
    }
}
