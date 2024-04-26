package entities;

import statics.TypeRole;

import java.io.Serializable;

public class Admin extends User implements Serializable {
    public Admin(String name, String mail, String password, TypeRole typeRole) {
        super(name, mail, password, typeRole);
    }
}
