package edu.upc.essi.gps.ecommerce;

import java.util.ArrayList;

/**
 * Created by GerardDuch on 30/05/15.
 */
public class UsersCollection {
    private ArrayList<User> usersList;

    public UsersCollection () {
        usersList = new ArrayList<User>();
    }

    public void addLogin(String tipusLogin, String name, String password) {
        User u = new User(tipusLogin, name, password);
        usersList.add(u);
    }

    public boolean checkLogin(String tipusLogin, String name) {
        for (User u: usersList) {
            if (u.getRol().equals(tipusLogin) && u.getName().equals(name)) return true;
        }
        return false;
    }
}
