package service;

import entities.Admin;
import statics.TypeRole;

import java.util.ArrayList;
import java.util.List;

public class AdminService {
    final List<Admin> admins = new ArrayList<>();

    private void createAdmin(){
        admins.add(new Admin("hieu", "hieu@gmail.com", "Hieu.1234", TypeRole.ADMIN));
    }

    public Admin findByName(String userName) {
        createAdmin();
        for (Admin e: admins) {
            if (e.getName().equals(userName))
                return e;
        }
        return null;
    }
}
