package org.plexus.repository;




import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.plexus.Main;
import org.plexus.model.SpaceShip;
import org.plexus.model.enums.SEEN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,classes = Main.class)
@ActiveProfiles("test")
public class SpaceShipRepositoryTest {

    @Autowired
    private  SpaceShipRepository spaceShipRepository;

    @BeforeEach
    void afterEach() {
        spaceShipRepository.deleteAll();
    }

     @Test
    public void testInsert() {

        SpaceShip spaceShip = new SpaceShip();
        spaceShip.setId(1);
        spaceShip.setName("test space");
        spaceShip.setSeen(SEEN.FILM);
        spaceShip.setCreatedDate(Timestamp.valueOf("2022-01-01 00:00:00"));
        spaceShipRepository.save(spaceShip);
        SpaceShip  spaceShipResult = spaceShipRepository.findById(1).get();
        assertNotNull(spaceShip.getId());
        assertEquals(spaceShip.getName(), spaceShipResult.getName());
        assertEquals(spaceShip.getId(), spaceShipResult.getId());
     }

    @Test
    public void testDelete() {
        SpaceShip spaceShip = new SpaceShip();
        spaceShip.setId(1);
        spaceShip.setName("test space");
        spaceShip.setSeen(SEEN.FILM);
        spaceShip.setCreatedDate(Timestamp.valueOf("2022-01-01 00:00:00"));
        // Verificación de los resultados
        assertNotNull(spaceShip.getId());
        spaceShipRepository.deleteById(spaceShip.getId());
        assertTrue(spaceShipRepository.findById(spaceShip.getId()).isEmpty());


    }


    @Test
    public void testUpdate() {

        SpaceShip spaceShip = new SpaceShip();
        spaceShip.setId(1);
        spaceShip.setName("test space");
        spaceShip.setSeen(SEEN.FILM);
        spaceShip.setCreatedDate(Timestamp.valueOf("2022-01-01 00:00:00"));
        SpaceShip spaceShipResult = spaceShipRepository.save(spaceShip);
        assertNotNull(spaceShip.getId());
        assertEquals(spaceShip.getName(), spaceShipResult.getName());
        assertEquals(spaceShip.getId(), spaceShipResult.getId());

        String nameUpdate = "Test Modified";
        spaceShipResult.setName(nameUpdate);
        spaceShipRepository.save(spaceShipResult);

        assertEquals(spaceShipRepository.findById(spaceShipResult.getId()).get().getName(),nameUpdate);


    }

}
