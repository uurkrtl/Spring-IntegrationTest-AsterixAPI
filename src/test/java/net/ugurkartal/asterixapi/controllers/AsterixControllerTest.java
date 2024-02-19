package net.ugurkartal.asterixapi.controllers;

import net.ugurkartal.asterixapi.repositories.AsterixRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    void getCharacterById() {
    }

    @Test
    void addCharacter() {
    }

    @Test
    void updateCharacter() {
    }

    @Test
    void deleteById() {
    }
}