package org.plexus.controller;



import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.plexus.Main;
import org.plexus.dto.SpaceShipDTO;
import org.plexus.model.enums.SEEN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



//@WebMvcTest(controllers = SpaceShipController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,classes = Main.class)
@AutoConfigureMockMvc
public class ControllerApplicationTest {

    @Autowired
    MockMvc mockMvc;
    private final static String SPACESHIP_URL = "/spaceShips";
    private final static String SEARCH_URL = SPACESHIP_URL+"/searchByNameContaining/";



    public String getStringFromSpaceShip(SpaceShipDTO spaceShip) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(spaceShip);
    }

    private SpaceShipDTO getSpaceShipFromString(String SpaceShipDTO) throws JsonProcessingException {
        return new ObjectMapper().readValue(SpaceShipDTO, SpaceShipDTO.class);
    }

    public List<SpaceShipDTO> getSpaceShipListFromString(String spaceShipListJson) throws JsonProcessingException {
        return new ObjectMapper().readValue(spaceShipListJson, new TypeReference<List<SpaceShipDTO>>() {});
    }



    @Test
    public void test_insert_should_be_OK() throws Exception {
        SpaceShipDTO space = new SpaceShipDTO();
        space.setName("Test Integration");
        space.setId(1);
        space.setSeen(SEEN.FILM);
        space.setCreatedDate(Timestamp.from(Instant.now()));
        String spaceShiptResponse = mockMvc.perform(
                post(SPACESHIP_URL).
                        contentType(MediaType.APPLICATION_JSON).content(getStringFromSpaceShip(space))
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        SpaceShipDTO spaceResult = getSpaceShipFromString(spaceShiptResponse);
        Assertions.assertNotNull(spaceResult);
    }




    @Test
    public void testSearchContainsNotFound() throws Exception {
        String name = "Millennial";
        String spaceShiptResponse = mockMvc.perform(
                get(SEARCH_URL + name).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        List<SpaceShipDTO> spaceResult = getSpaceShipListFromString(spaceShiptResponse);
        Assertions.assertEquals(spaceResult.size(),0);
    }

    @Test
    public void testSearchContainsPartOfName() throws Exception {
        String name = "Mill";
        String spaceShiptResponse = mockMvc.perform(
                get(SEARCH_URL + name).
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        List<SpaceShipDTO> spaceResult = getSpaceShipListFromString(spaceShiptResponse);
        Assertions.assertEquals(spaceResult.size(),1);
    }


    @Test
    public void testDelete() throws Exception {
        String spaceShiptResponse = mockMvc.perform(
                delete(SPACESHIP_URL + "/3").
                        contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
    }

}
