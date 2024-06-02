package org.plexus.util;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.plexus.dto.SpaceShipDTO;
import org.plexus.model.SpaceShip;
import org.plexus.model.enums.SEEN;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpaceShipMapperTest {

    private final ModelMapper modelMapper = new ModelMapper();
    @Test
    void testEntityToDTOMapping() {
        SpaceShip spaceShip = new SpaceShip();
        spaceShip.setId(1);
        spaceShip.setName("test space");
        spaceShip.setSeen(SEEN.FILM);
        SpaceShipDTO spaceShipDTO = modelMapper.map(spaceShip, SpaceShipDTO.class);
        assertNotNull(spaceShipDTO);
        assertEquals(spaceShip.getId(), spaceShipDTO.getId());
        assertEquals(spaceShip.getName(), spaceShipDTO.getName());
        assertEquals(spaceShip.getSeen(), spaceShipDTO.getSeen());
    }



}
