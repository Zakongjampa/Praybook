package com.prayer.demo.mapper;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prayer.demo.dto.UserResponseDTO;
import com.prayer.demo.utility.User;
import org.junit.jupiter.api.Test;

class UserMapperTest {

    @Test
    void toDtoIncludesIsAdminInJson() throws JsonProcessingException {
        User user = new User();
        user.setId(1L);
        user.setUsername("admin");
        user.setEmail("admin@example.com");
        user.setFirstName("Admin");
        user.setLastName("User");
        user.setAdmin(true);

        UserResponseDTO dto = UserMapper.toDTO(user);
        String json = new ObjectMapper().writeValueAsString(dto);

        assertTrue(json.contains("\"isAdmin\":true"));
    }
}
