package com.team2project.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {
    
    @Test
    public void test_get_authority()
    {
        User user = new User();
        user.setRole(User.UserRole.USER);
        assertEquals("ROLE_" + user.getRole(), user.getAuthority());
    }

}
