package com.solvd.animals_mvc.service.impl;

import com.solvd.animals_mvc.dao.ConnectionPool;
import com.solvd.animals_mvc.dao.ConnectionTool;
import com.solvd.animals_mvc.dao.DBFactory;
import com.solvd.animals_mvc.dao.IZooDAO;
import com.solvd.animals_mvc.model.Zoo;
import com.solvd.animals_mvc.service.IZooService;

import java.util.List;

public class ZooServiceImpl implements IZooService {
    private final IZooDAO zooDAO = DBFactory
            .getZooDAO(ConnectionTool.valueOf(ConnectionPool.getFramework()));

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
        zooDAO.updateEntity(entity, id);
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
