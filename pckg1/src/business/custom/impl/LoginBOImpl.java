package business.custom.impl;

import DB.HibernateUtil;
import business.custom.LoginBO;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.AdminDAO;
import dto.LoginDTO;
import entity.Admin;
import org.hibernate.Session;

public class LoginBOImpl implements LoginBO {
    AdminDAO adminDAO = DAOFactory.getInstance().getDAO(DAOTypes.ADMIN);

    @Override
    public boolean authentication(LoginDTO loginDTO) throws Exception {
        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            adminDAO.setSession(session);
            session.beginTransaction();
            boolean authentication = adminDAO.authentication(new Admin(loginDTO.getUsename(), loginDTO.getPassword()));
            session.getTransaction().commit();
            return authentication;
        }
    }

}
