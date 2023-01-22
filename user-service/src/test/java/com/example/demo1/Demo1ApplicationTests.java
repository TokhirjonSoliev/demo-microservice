package com.example.demo1;

import com.example.demo1.dto.UserCreateDto;
import com.example.demo1.dto.UserUpdateDto;
import com.example.demo1.entity.User;
import com.example.demo1.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class Demo1ApplicationTests {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository userRepository;
    @Container
    static PostgreSQLContainer<?> database = new PostgreSQLContainer<>("postgres:12.3")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", database::getJdbcUrl);
        registry.add("spring.datasource.password", database::getPassword);
        registry.add("spring.datasource.username", database::getUsername);
    }

    @Test
    void shouldCreateUser() throws Exception {
        UserCreateDto userCreateDto = getUserCreateDto();
        String userCreateDtoSting = objectMapper.writeValueAsString(userCreateDto);
        MvcResult mvcResult = checkingUserCreation(userCreateDtoSting);
        User user = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);
        Assertions.assertEquals("John", userRepository.findById(user.getId()).get().getFirstName());
        userRepository.delete(user);
    }

    @Test
    void shouldEditUser() throws Exception {
        UserCreateDto userCreateDto = getUserCreateDto();
        UserUpdateDto userUpdateDto = getUserUpdateDto();
        String userCreateDtoSting = objectMapper.writeValueAsString(userCreateDto);
        String userUpdateDtoString = objectMapper.writeValueAsString(userUpdateDto);
        MvcResult mvcResult = checkingUserCreation(userCreateDtoSting);
        User user = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);
        MvcResult updatedMvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/api/user/edit/" + user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userUpdateDtoString))
                .andExpect(status().isOk())
                .andReturn();
        User updatedUser = objectMapper.readValue(updatedMvcResult.getResponse().getContentAsString(), User.class);
        Assertions.assertEquals("Tom", userRepository.findById(updatedUser.getId()).get().getFirstName());
        userRepository.delete(user);
    }

    @Test
    void shouldDeleteUser() throws Exception {
        UserCreateDto userCreateDto = getUserCreateDto();
        String userCreateDtoSting = objectMapper.writeValueAsString(userCreateDto);
        MvcResult mvcResult = checkingUserCreation(userCreateDtoSting);
        User user = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), User.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/user/delete/" + user.getId()))
                .andExpect(status().isOk());
        Assertions.assertEquals(0, userRepository.findAll().size());
    }

    private MvcResult checkingUserCreation(String userCreateDtoSting) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post("/api/user/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCreateDtoSting))
                .andExpect(status().isCreated())
                .andReturn();
    }


    private UserCreateDto getUserCreateDto() {
        return UserCreateDto.builder()
                .firstName("John")
                .lastName("Terry")
                .username("john_st")
                .email("john@gmail.com")
                .password("johnterry")
                .build();
    }

    private UserUpdateDto getUserUpdateDto() {
        return UserUpdateDto.builder()
                .firstName("Tom")
                .build();
    }

}