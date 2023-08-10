package com.fssa.freshnest.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import com.fssa.freshnest.model.User;
import com.fssa.freshnest.services.exceptions.ServiceException;

public class TestUserProfileImageUpdate {

    public static void main(String[] args) {

        User user1 = new User("https://example.com/image.jpg", 1);
        UserService userService = new UserService();

        try {
            userService.profileImageUpdate(user1);

        } catch (ServiceException e) {
            e.printStackTrace();

        }
    }

    @Test
     void testProfileUpdateSuccess() {

        User user1 = new User("https://example.com/image.jpg", 1);
        UserService userService = new UserService();

        try {
            assertTrue(userService.profileImageUpdate(user1));

        } catch (ServiceException e) {
            e.printStackTrace();
            fail();

        }
    }


     void testProfileInvaliedDetailsUpdateSuccess() {

        User user1 = new User("https://example", 24);
        UserService userService = new UserService();

        try {
            assertFalse(userService.profileImageUpdate(user1));

        } catch (ServiceException e) {
            e.printStackTrace();
            
        }
    }


     void testProfileDetailsNull() {

        User user1 = null;
        UserService userService = new UserService();

        try {
            assertFalse(userService.profileImageUpdate(user1));

        } catch (ServiceException e) {
            e.printStackTrace();


        }
    }
}
