package com.solvd.animals_mvc.service.impl;

import com.solvd.animals_mvc.dao.jdbc.impl.DepartmentDAOImpl;
import com.solvd.animals_mvc.model.Department;
import com.solvd.animals_mvc.service.IDepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();

    @Override
    public Long create(Department entity) {
        return departmentDAO.insertEntity(entity);
    }

    @Override
    public Department retrieveById(Long id) {
        return departmentDAO.getEntityById(id);
    }

    @Override
    public void updateById(Department entity, Long id) {
        departmentDAO.updateEntity(entity,id);
    }

    @Override
    public void delete(Department entity) {
        departmentDAO.removeEntity(entity);
    }

    @Override
    public List<Department> retrieveAll() {
        return departmentDAO.getEntities();
    }
}
