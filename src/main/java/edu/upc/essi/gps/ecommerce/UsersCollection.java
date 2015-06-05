package edu.upc.essi.gps.ecommerce;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by GerardDuch on 30/05/15.
 */
public class UsersCollection {
    private ArrayList<User> usersList;
    private String activeUser_name;
    private String activeUser_type;

    public UsersCollection () {
        usersList = new ArrayList<User>();
        activeUser_name=null;
        activeUser_type=null;
    }

    public void addLogin(String tipusLogin, String name, String password) {
        if (activeUser_name == null && activeUser_type == null)
            throw new IllegalArgumentException("Actualment no existeix cap usuari que hagi iniciat sessio");
        if (!activeUser_type.equals("gestor"))
            throw new IllegalArgumentException("Nomes un gestor pot crear un usuari nou al sistema");

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
        if (!activeUser_type.equals("gestor"))
            throw new IllegalArgumentException("Nomes un gestor pot llistar els usuaris del sistema");

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
        if (!activeUser_type.equals("gestor"))
            throw new IllegalArgumentException("Nomes un gestor pot llistar els usuaris del sistema");

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
        for (User u: usersList) {
            if (u.getName().equals(nom) && u.getPassword().equals(password)) return u.getRol();
        }
        return "";
    }

    public void addUserActive(String nom, String rol) {
        activeUser_name = nom;
        activeUser_type = rol;
    }



    public boolean checkUserActive(String tipusLogin, String name) {
        if ( activeUser_name.equals(name) && activeUser_type.equals(tipusLogin) ) return true;
        return false;
    }

    public String getActiveUsers() {
        if (activeUser_name == null && activeUser_type == null)
            throw new IllegalArgumentException("Actualment no existeix cap usuari que hagi iniciat sessio");

        StringBuilder sb = new StringBuilder();
        sb.append("------ USUARIS ACTIUS AL SISTEMA -----\n");
        sb.append("--Tipus Login--  --Nom--  --Password--\n");
        String espai = " , ";

        sb.append(activeUser_type).append(espai).append(activeUser_name).append(espai).append(getPassword()).append("\n");

        return sb.toString();
    }

    public void logout(String nom) {
        activeUser_name = null;
        activeUser_type = null;
    }

    public String getPassword() {
        String aux = "";
        for (User u: usersList) {
            if (u.getName().equals(activeUser_name) && u.getRol().equals(activeUser_type)) {
                aux = u.getPassword();
                return aux;
            }
        }
        return aux;
    }

    public boolean checkUserNotActive(String tipusLogin, String name) {
        if (activeUser_name != null && activeUser_type != null) return false;
        return true;
    }

    public boolean checkUserCanLogin() {
        if (activeUser_name == null && activeUser_type == null) return true;
        else return false;
    }

    public void adminAddLogin(String tipusLogin, String name, String password) {
        User u = new User(tipusLogin, name, password);
        usersList.add(u);
    }


    public String getTypeUsuariActiu(){return activeUser_type;}
}