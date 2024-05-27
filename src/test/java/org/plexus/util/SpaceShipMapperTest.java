package org.plexus.util;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.plexus.dto.SpaceShipDTO;
import org.plexus.model.enums.SEEN;
import org.plexus.model.enums.SpaceShip;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SpaceShipMapperTest {

    private final ModelMapper modelMapper = new ModelMapper();


    @Test
    void testEntityToDTOMapping() {
        // Configuración del objeto de entidad
        SpaceShip spaceShip = new SpaceShip();
        spaceShip.setId(1);
        spaceShip.setName("test space");
        spaceShip.setSeen(SEEN.FILM);
        spaceShip.setCreated(Timestamp.valueOf("2022-01-01 00:00:00"));
        spaceShip.setCreatedDate(Timestamp.valueOf("2022-01-01 00:00:00"));

        // Mapeo de entidad a DTO
        SpaceShipDTO spaceShipDTO = modelMapper.map(spaceShip, SpaceShipDTO.class);

        // Verificación de resultados
        assertNotNull(spaceShipDTO);
        assertEquals(spaceShip.getId(), spaceShipDTO.getId());
        assertEquals(spaceShip.getName(), spaceShipDTO.getName());
        assertEquals(spaceShip.getCreated(), spaceShipDTO.getCreatedDate());
        assertEquals(spaceShip.getSeen(), spaceShipDTO.getSeen());

    }
}
