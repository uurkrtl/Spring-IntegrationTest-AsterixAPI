package net.ugurkartal.asterixapi.controllers;

import net.ugurkartal.asterixapi.models.Character;
import net.ugurkartal.asterixapi.repositories.AsterixRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class AsterixControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private AsterixRepository asterixRepository;

    @Test
    void getAllCharacters_shouldReturnEmptyList_WhenCalledInitially() throws Exception {
        //GIVEN
        List<Character> characters = List.of();

        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/asterix/characters"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(characters.toString()));

    }

    @Test
    @DirtiesContext
    void getCharacterById_shouldReturnAstrix_WhenCalledWithValidId() throws Exception {
        //GIVEN
        Character character = new Character("1", "Astrix", 4, "King");
        asterixRepository.save(character);

        //WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.get("/asterix/characters/getById/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                    "id": "1",
                                    "name": "Astrix",
                                    "age": 4,
                                    "profession": "King"
                                }
                                """
                ));
    }

    @Test
    void shouldReturnNewCharacter_WhenCalledWithValidJSON() throws Exception {
        // GIVEN
        // WHEN & THEN
        mvc.perform(MockMvcRequestBuilders.post("/asterix/characters/add")
                        .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                {
                                    "name": "Astrix",
                                    "age": 4,
                                    "profession": "King"
                                }
                                """
                ))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(
                        """
                                {
                                    "name": "Astrix",
                                    "age": 4,
                                    "profession": "King"
                                }
                                """
                ))
                .andExpect(jsonPath("$.id").exists());
    }

    @Test
    void updateCharacter() {
    }

    @Test
    void deleteById() {
    }
}