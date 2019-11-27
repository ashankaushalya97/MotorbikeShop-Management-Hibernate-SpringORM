package lk.ijse.dep.rcrmoto.business.custom.impl;

import lk.ijse.dep.rcrmoto.business.custom.CategoryBO;
import lk.ijse.dep.rcrmoto.dao.custom.CategoryDAO;
import lk.ijse.dep.rcrmoto.dto.CategoryDTO;
import lk.ijse.dep.rcrmoto.entity.Category;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Component
public class CategoryBOImpl implements CategoryBO {
    @Autowired
    CategoryDAO categoryDAO;

    @Transactional(readOnly = true)
    @Override
    public List<CategoryDTO> getCategories() throws Exception {
            List<CategoryDTO> categories = new ArrayList<>();
            List<Category> all = categoryDAO.findAll();
            for (Category category : all) {
                categories.add(new CategoryDTO(category.getCategoryId(),category.getDescription()));
            }
            return categories;
    }
}
