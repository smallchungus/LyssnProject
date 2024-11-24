
package com.lyssn;

import java.util.List;

public class LyssnApp {
    public static void main(String[] args) {
        try {
            // Let's test out our database operations
            System.out.println("Getting all users from database...");
            List<User> allUsers = DatabaseHelper.getAllUsers();

            if (allUsers.isEmpty()) {
                System.out.println("No users found!");
                return;
            }

            System.out.println("Found " + allUsers.size() + " users:");
            allUsers.forEach(System.out::println);

            // Test getting a single user
            long testUserId = 1;  // assuming we have a user with ID 1
            System.out.println("\nLooking up user " + testUserId + "...");
            User user = DatabaseHelper.getUserById(testUserId);

            if (user != null) {
                System.out.println("Found: " + user);

                // Let's update their name
                System.out.println("Updating user's name...");
                boolean updated = DatabaseHelper.updateUser(testUserId, "Johnny", "Updated");

                if (updated) {
                    User updatedUser = DatabaseHelper.getUserById(testUserId);
                    System.out.println("User after update: " + updatedUser);
                } else {
                    System.out.println("Hmm, update didn't work for some reason");
                }
            } else {
                System.out.println("Couldn't find user " + testUserId);
            }

        } catch (Exception e) {
            System.out.println("Oops, something went wrong: " + e.getMessage());
            e.printStackTrace();
        }
    }
}