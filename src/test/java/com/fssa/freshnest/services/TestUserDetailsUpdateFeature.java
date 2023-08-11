package com.fssa.freshnest.services;

import com.fssa.freshnest.model.User;
import com.fssa.freshnest.services.exceptions.ServiceException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestUserDetailsUpdateFeature {




    @Test
    void testUpdateSuccess() {

        User user1 = new User("susi@gmail.com", "Kanipapa", "raji@SM123", "Susikumar", "Pitchaimuthu", 20, 8870737612L,
                "2003-08-01", "Indian", "Male");

        UserService userService = new UserService();

        try {
            assertTrue(userService.updateUser(user1));
        } catch (ServiceException e) {
            e.printStackTrace();
            fail();
        }
    }

    // test invalid email address update
    @Test
    void testEmailNotExistInDatabaseUpdate() {
        UserService userService = new UserService();

        User user1 = new User("naan@gmail.com", "Kanipapa", "raji@SM123", "Susikumar", "Pitchaimuthu", 20, 8870737612L,
                "2003-08-01", "Indian", "Male");
        try {
            assertFalse(userService.updateUser(user1));
        } catch (ServiceException e) {
            e.printStackTrace();

        }
    }

    // test invalid user name update
    @Test
    void testUpdateInvalidUserName() {
        UserService userService = new UserService();

        User user1 = new User("mani@gmail.com", "", "raji@SM123", "Susikumar", "Pitchaimuthu", 20, 8870737612L,
                "2003-08-01", "Indian", "Male");
        try {
            assertFalse(userService.updateUser(user1));
        } catch (ServiceException e) {
            e.printStackTrace();

        }
    }

    // test invalid user gender details
    @Test
    void testUpdateInvalidGenderDetails() {
        UserService userService = new UserService();

        User user1 = new User("mani@gmail.com", "kanipapa", "raji@SM123", "Susikumar", "Pitchaimuthu", 20, 8870737612L,
                "2003-08-01", "Indian", "Male234");
        try {
            assertFalse(userService.updateUser(user1));
        } catch (ServiceException e) {
            e.printStackTrace();

        }
    }

    // test invalid nationality details
    @Test
    void testInvalidNationalityDetails() {
        UserService userService = new UserService();

        User user1 = new User("mani@gmail.com", "kanipapa", "raji@SM123", "Susikumar", "Pitchaimuthu", 20, 8870737612L,
                "2003-08-12", "Indian", "Male");
        try {
            assertFalse(userService.updateUser(user1));
        } catch (ServiceException e) {
            e.printStackTrace();

        }
    }

    // test invalid first name details
    @Test
    void testInvalidUserFirstNameDetails() {
        UserService userService = new UserService();

        User user1 = new User("mani@gmail.com", "kanipapa", "raji@SM123", "", "Pitchaimuthu", 20, 8870737612L,
                "2003-08-12", "Indian", "Male");
        try {
            assertFalse(userService.updateUser(user1));
        } catch (ServiceException e) {
            e.printStackTrace();

        }
    }

    // test invalid last name details
    @Test
    void testInvalidUserLastNameDetails() {
        UserService userService = new UserService();

        User user1 = new User("mani@gmail.com", "kanipapa", "raji@SM123", "Susikumar", "", 20, 8870737612L,
                "2003-08-12", "Indian", "Male");
        try {
            assertFalse(userService.updateUser(user1));
        } catch (ServiceException e) {
            e.printStackTrace();

        }
    }

}
