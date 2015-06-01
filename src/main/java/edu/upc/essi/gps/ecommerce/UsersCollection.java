package edu.upc.essi.gps.ecommerce;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by GerardDuch on 30/05/15.
 */
public class UsersCollection {
    private ArrayList<User> usersList;
    private ArrayList<User> activeUsers;

    public UsersCollection () {
        usersList = new ArrayList<User>();
        activeUsers = new ArrayList<User>();
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

    public String getListLogins(String tipoLogin) {
        if (usersList.isEmpty())
            throw new IllegalArgumentException("Actualment no existeix cap login del tipus " + tipoLogin);

        StringBuilder sb = new StringBuilder();
        sb.append("--Tipus Login--  --Nom--  --Password--\n");
        String espai = " , ";

        if (tipoLogin.equals("gestor")) {
            for (User u : usersList) {
                if (u.getRol().equals("gestor"))
                    sb.append(u.getRol()).append(espai).append(u.getName()).append(espai).append(u.getPassword()).append("\n");
            }
        }

        if (tipoLogin.equals("venedor")) {
            for (User u : usersList) {
                if (u.getRol().equals("venedor"))
                    sb.append(u.getRol()).append(espai).append(u.getName()).append(espai).append(u.getPassword()).append("\n");
            }
        }
        return sb.toString();
    }

    public String getAllListLogins() {
        if (usersList.isEmpty())
            throw new IllegalArgumentException("Actualment no existeix cap login");

        StringBuilder sb = new StringBuilder();
        sb.append("--Tipus Login--  --Nom--  --Password--\n");
        String espai = " , ";

        for (User u : usersList) {
            if (u.getRol().equals("gestor"))
                sb.append(u.getRol()).append(espai).append(u.getName()).append(espai).append(u.getPassword()).append("\n");
        }

        for (User u : usersList) {
            if (u.getRol().equals("venedor"))
                sb.append(u.getRol()).append(espai).append(u.getName()).append(espai).append(u.getPassword()).append("\n");
        }

        return sb.toString();
    }

    public boolean usuariCorrecte(String nom, String password) {
        for (User u: usersList) {
            if (u.getName().equals(nom) && u.getPassword().equals(password)) return true;
        }
        return false;
    }

    public String getRol(String nom, String password) {
        String rol="";
        for (User u: usersList) {
            if (u.getName().equals(nom) && u.getPassword().equals(password)) rol=u.getRol();
        }
        return rol;
    }

    public void addUserActive(String nom, String password) {
        User u = new User(getRol(nom,password), nom, password);
        activeUsers.add(u);
    }

    public boolean checkUserActive(String tipusLogin, String name, String password) {
        for (User u: activeUsers) {
            if (u.getRol().equals(tipusLogin) && u.getName().equals(name) && u.getPassword().equals(password)) return true;
        }
        return false;
    }

    public String getActiveUsers() {
        if (activeUsers.isEmpty())
            throw new IllegalArgumentException("Actualment no existeix cap usuari que hagi iniciat sessio");

        StringBuilder sb = new StringBuilder();
        sb.append("------ USUARIS ACTIUS AL SISTEMA -----\n");
        sb.append("--Tipus Login--  --Nom--  --Password--\n");
        String espai = " , ";

        for (User u : activeUsers) {
            if (u.getRol().equals("gestor"))
                sb.append(u.getRol()).append(espai).append(u.getName()).append(espai).append(u.getPassword()).append("\n");
        }

        for (User u : activeUsers) {
            if (u.getRol().equals("venedor"))
                sb.append(u.getRol()).append(espai).append(u.getName()).append(espai).append(u.getPassword()).append("\n");
        }

        return sb.toString();
    }

    public void logout(String nom) {
        Iterator<User> it = activeUsers.iterator();
        User aux;
        Boolean trobat = false;
        while( it.hasNext() && !trobat){
            aux = it.next();
            if ( aux.getName().equals(nom) && !trobat) {
                trobat=true;
                it.remove();
            }
        }
    }

    public boolean checkUserNotActive(String tipusLogin, String name) {
        for (User u: activeUsers) {
            if (u.getRol().equals(tipusLogin) && u.getName().equals(name)) return false;
        }
        return true;
    }
}
