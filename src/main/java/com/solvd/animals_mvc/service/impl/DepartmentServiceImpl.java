package com.solvd.animals_mvc.service.impl;

import com.solvd.animals_mvc.dao.ConnectionPool;
import com.solvd.animals_mvc.dao.ConnectionTool;
import com.solvd.animals_mvc.dao.DBFactory;
import com.solvd.animals_mvc.dao.IDepartmentDAO;
import com.solvd.animals_mvc.model.Department;
import com.solvd.animals_mvc.service.IDepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements IDepartmentService {
    private final IDepartmentDAO departmentDAO = DBFactory
            .getDepartmentDAO(ConnectionTool.valueOf(ConnectionPool.getFramework()));

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
        departmentDAO.updateEntity(entity, id);
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
