package entities;

import statics.TypeRole;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String mail;
    private String password;
    private TypeRole typeRole;
    public User() {
    }

    public User(String name, String mail, String password, TypeRole typeRole) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.typeRole = typeRole;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TypeRole getTypeRole() {
        return typeRole;
    }

    public void setTypeRole(TypeRole typeRole) {
        this.typeRole = typeRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", role='" + typeRole + '\'' +
                '}' + "\n";
    }
}
