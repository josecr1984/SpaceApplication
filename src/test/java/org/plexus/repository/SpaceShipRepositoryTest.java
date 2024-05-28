package org.plexus.repository;




import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.plexus.Main;
import org.plexus.model.enums.SEEN;
import org.plexus.model.enums.SpaceShip;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
        Timestamp startDate = Timestamp.valueOf(LocalDate.of(2024, 1, 21).atStartOfDay());
        SpaceShip spaceShip = new SpaceShip();
        spaceShip.setId(1);
        spaceShip.setName("test space");
        spaceShip.setSeen(SEEN.FILM);
        spaceShip.setCreated(Timestamp.valueOf("2022-01-01 00:00:00"));
        spaceShip.setCreatedDate(Timestamp.valueOf("2022-01-01 00:00:00"));
        SpaceShip spaceShipResult = spaceShipRepository.save(spaceShip);
        assertNotNull(spaceShip.getId());
        assertEquals(spaceShip.getName(), spaceShipResult.getName());
        assertEquals(spaceShip.getId(), spaceShipResult.getId());
     }

    @Test
    public void testDelete() {

        Timestamp startDate = Timestamp.valueOf(LocalDate.of(2024, 1, 21).atStartOfDay());
        Timestamp endDate = Timestamp.valueOf(LocalDate.of(2024, 1, 22).atStartOfDay());

        SpaceShip spaceShip = new SpaceShip();
        spaceShip.setId(1);
        spaceShip.setName("test space");
        spaceShip.setSeen(SEEN.FILM);
        spaceShip.setCreated(Timestamp.valueOf("2022-01-01 00:00:00"));
        spaceShip.setCreatedDate(Timestamp.valueOf("2022-01-01 00:00:00"));
        // Verificación de los resultados
        assertNotNull(spaceShip.getId());
        spaceShipRepository.deleteById(spaceShip.getId());
        assertTrue(spaceShipRepository.findById(spaceShip.getId()).isEmpty());


    }


    @Test
    public void testUpdate() {
        Timestamp startDate = Timestamp.valueOf(LocalDate.of(2024, 1, 21).atStartOfDay());
        SpaceShip spaceShip = new SpaceShip();
        spaceShip.setId(1);
        spaceShip.setName("test space");
        spaceShip.setSeen(SEEN.FILM);
        spaceShip.setCreated(Timestamp.valueOf("2022-01-01 00:00:00"));
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
