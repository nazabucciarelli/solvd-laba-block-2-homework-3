package com.solvd.animals_mvc.service.impl;

import com.solvd.animals_mvc.dao.jdbc.impl.ZooDAOImpl;
import com.solvd.animals_mvc.model.Zoo;
import com.solvd.animals_mvc.service.IZooService;

import java.util.List;

public class ZooServiceImpl implements IZooService {
    private ZooDAOImpl zooDAO = new ZooDAOImpl();

    @Override
    public Long create(Zoo entity) {
        return zooDAO.insertEntity(entity);
    }

    @Override
    public Zoo retrieveById(Long id) {
        return zooDAO.getEntityById(id);
    }

    @Override
    public void updateById(Zoo entity, Long id) {
        zooDAO.updateEntity(entity,id);
    }

    @Override
    public void delete(Zoo entity) {
        zooDAO.removeEntity(entity);
    }

    @Override
    public List<Zoo> retrieveAll() {
        return zooDAO.getEntities();
    }
}
