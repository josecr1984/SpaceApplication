package org.plexus.controller;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.plexus.Main;
import org.plexus.dto.InputSpaceShipDTO;
import org.plexus.dto.SpaceShipDTO;
import org.plexus.model.enums.SEEN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



//@WebMvcTest(controllers = SpaceShipController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,classes = Main.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ControllerApplicationTest {


    @Autowired
    MockMvc mockMvc;
    private final static String SPACESHIP_URL = "/space-ship";
    private final static String SEARCH_URL = SPACESHIP_URL+"/searchByNameContaining";



    /*public String getStringFromSpaceShip(SpaceShipDTO spaceShip) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(spaceShip);
    }*/

    public String getStringFromSpaceShip(InputSpaceShipDTO spaceShip) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(spaceShip);
    }

    private SpaceShipDTO getSpaceShipFromString(String SpaceShipDTO) throws JsonProcessingException {
        return new ObjectMapper().readValue(SpaceShipDTO, SpaceShipDTO.class);
    }

    public List<SpaceShipDTO> getSpaceShipListFromString(String spaceShipListJson) throws JsonProcessingException {
        return new ObjectMapper().readValue(spaceShipListJson, new TypeReference<List<SpaceShipDTO>>() {});
    }



    @Test
    void testInsert() throws Exception {
        InputSpaceShipDTO space = new InputSpaceShipDTO();
        space.setName("Test Integration");
        space.setReleased(Timestamp.valueOf("2022-01-01 00:00:00"));
        space.setSeen(SEEN.FILM);
        String spaceShiptResponse = mockMvc.perform(
                post(SPACESHIP_URL).
                        contentType(MediaType.APPLICATION_JSON).content(getStringFromSpaceShip(space))
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        SpaceShipDTO spaceResult = getSpaceShipFromString(spaceShiptResponse);
        Assertions.assertNotNull(spaceResult);
    }




    @Test
    void testSearchContainsNotFound() throws Exception {
        String name = "Mil-";
        String spaceShiptResponse = mockMvc.perform(
                get(SEARCH_URL).param("name",name)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        List<SpaceShipDTO> spaceResult = getSpaceShipListFromString(spaceShiptResponse);
        Assertions.assertEquals(spaceResult.size(),0);
    }

    @Test
    void testSearchContainsPartOfName() throws Exception {
        String name = "Mill";
        String spaceShiptResponse = mockMvc.perform(
                get(SEARCH_URL).param("name",name)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        List<SpaceShipDTO> spaceResult = getSpaceShipListFromString(spaceShiptResponse);
        Assertions.assertEquals(spaceResult.size(),1);
    }


    @Test
    void testDelete() throws Exception {
        int spaceShipId = 1;
        String spaceShiptResponse = mockMvc.perform(
                delete(SPACESHIP_URL + "/"+spaceShipId).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        spaceShiptResponse  = mockMvc.perform(
                get(SPACESHIP_URL + "/"+spaceShipId).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNotFound()).andReturn().getResponse().getContentAsString();

    }

}
