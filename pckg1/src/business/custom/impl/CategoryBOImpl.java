package business.custom.impl;

import DB.HibernateUtil;
import business.custom.CategoryBO;
import dao.DAOFactory;
import dao.DAOTypes;
import dao.custom.CategoryDAO;
import dto.CategoryDTO;
import entity.Category;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CategoryBOImpl implements CategoryBO {
    CategoryDAO categoryDAO = DAOFactory.getInstance().getDAO(DAOTypes.CATEGORY);
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
