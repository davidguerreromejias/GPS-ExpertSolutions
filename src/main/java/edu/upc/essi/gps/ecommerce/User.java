package edu.upc.essi.gps.ecommerce;

import javax.jws.soap.SOAPBinding;

/**
 * Created by GerardDuch on 30/05/15.
 */
public class User {
    private String rol;
    private String name;
    private String password;

    public User(){};

    public User(String r, String n, String p) {
        this.rol=r;
        this.name=n;
        this.password=p;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getRol() {
        return rol;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
