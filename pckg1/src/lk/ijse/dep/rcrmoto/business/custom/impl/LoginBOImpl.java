package lk.ijse.dep.rcrmoto.business.custom.impl;

import lk.ijse.dep.rcrmoto.business.custom.LoginBO;
import lk.ijse.dep.rcrmoto.dao.custom.AdminDAO;
import lk.ijse.dep.rcrmoto.dto.LoginDTO;
import lk.ijse.dep.rcrmoto.entity.Admin;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class LoginBOImpl implements LoginBO {
    @Autowired
    AdminDAO adminDAO;

    @Transactional(readOnly = true)
    @Override
    public boolean authentication(LoginDTO loginDTO) throws Exception {
            return adminDAO.authentication(new Admin(loginDTO.getUsename(), loginDTO.getPassword()));
    }

}
