package lk.ijse.dep.rcrmoto.business.custom.impl;

import lk.ijse.dep.rcrmoto.DB.HibernateUtil;
import lk.ijse.dep.rcrmoto.business.custom.CategoryBO;
import lk.ijse.dep.rcrmoto.dao.DAOFactory;
import lk.ijse.dep.rcrmoto.dao.DAOTypes;
import lk.ijse.dep.rcrmoto.dao.custom.CategoryDAO;
import lk.ijse.dep.rcrmoto.dto.CategoryDTO;
import lk.ijse.dep.rcrmoto.entity.Category;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryBOImpl implements CategoryBO {
    @Autowired
    CategoryDAO categoryDAO;
    @Override
    public List<CategoryDTO> getCategories() throws Exception {

        try (Session session = HibernateUtil.getSessionFactory().openSession()){
            categoryDAO.setSession(session);
            session.beginTransaction();
            List<CategoryDTO> categories = new ArrayList<>();
            List<Category> all = categoryDAO.findAll();
            for (Category category : all) {
                categories.add(new CategoryDTO(category.getCategoryId(),category.getDescription()));
            }
            session.getTransaction().commit();
            return categories;
        }
    }
}
