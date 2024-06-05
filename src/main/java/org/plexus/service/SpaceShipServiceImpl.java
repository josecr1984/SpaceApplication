package org.plexus.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.plexus.dto.InputSpaceShipDTO;
import org.plexus.dto.SpaceShipDTO;
import org.plexus.error.ResourceNotFoundException;
import org.plexus.model.SpaceShip;
import org.plexus.repository.SpaceShipRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class SpaceShipServiceImpl implements SpaceShipService {


    private final SpaceShipRepository spaceShipRepository;
    private final ModelMapper modelMapper;

    @Value("${spaceApplication.cte.message.notfound.value}")
    private String messageNotFound;


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
                .orElseThrow(() -> new ResourceNotFoundException(messageNotFound + id));
        return modelMapper.map(spaceShip,SpaceShipDTO.class);
    }

    @Override
    @CachePut(value = "spaceships", key = "#id")
    @CacheEvict(value = "spaceships", key = "'all'")
    public SpaceShipDTO update(Integer id, InputSpaceShipDTO inputSpaceShipDTO) {
        SpaceShip spaceShip = spaceShipRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException(messageNotFound + id));
        spaceShip.setName(inputSpaceShipDTO.getName());
        spaceShip.setSeen(inputSpaceShipDTO.getSeen());
        spaceShip.setReleased(inputSpaceShipDTO.getReleased());
        return modelMapper.map(spaceShipRepository.save(spaceShip),SpaceShipDTO.class);
    }

    @Override
    @CacheEvict(value = "spaceships", allEntries = true)
    public Integer delete(Integer id) {
        SpaceShip spaceShip = spaceShipRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(messageNotFound + id));
        spaceShipRepository.delete(spaceShip);
        return id;
    }

    @Override
    @CachePut(value = "spaceships", key = "#result.id")
    @CacheEvict(value = "spaceships", key = "'all'")
    public SpaceShipDTO create(InputSpaceShipDTO inputSpaceShipDTO) {
        SpaceShip spaceShip = modelMapper.map(inputSpaceShipDTO, SpaceShip.class);
        return modelMapper.map(spaceShipRepository.save(spaceShip),SpaceShipDTO.class);
    }

    @Override
    public List<SpaceShipDTO> searchByNameContaining(String name) {
        return  spaceShipRepository.findByNameContaining(name).stream().map(shapeShip -> modelMapper.map(shapeShip,SpaceShipDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<SpaceShipDTO> getAllSpaceShips(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<SpaceShip> spaceShipPage = spaceShipRepository.findAll(pageable);
        List<SpaceShipDTO> spaceShipDTOs = spaceShipRepository.findAll(PageRequest.of(page, size)).getContent().stream()
                .map(shapeShip -> modelMapper.map(shapeShip,SpaceShipDTO.class))
                .collect(Collectors.toList());
        return new PageImpl<>(spaceShipDTOs, pageable, spaceShipPage.getTotalElements());

    }
}
