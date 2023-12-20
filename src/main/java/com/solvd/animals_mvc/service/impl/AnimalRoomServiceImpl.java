package com.solvd.animals_mvc.service.impl;

import com.solvd.animals_mvc.dao.ConnectionPool;
import com.solvd.animals_mvc.dao.ConnectionTool;
import com.solvd.animals_mvc.dao.DBFactory;
import com.solvd.animals_mvc.dao.IAnimalRoomDAO;
import com.solvd.animals_mvc.model.AnimalRoom;
import com.solvd.animals_mvc.service.IAnimalRoomService;

import java.util.List;

public class AnimalRoomServiceImpl implements IAnimalRoomService {
    private final IAnimalRoomDAO animalRoomDAO = DBFactory
            .getAnimalRoomDAO(ConnectionTool.valueOf(ConnectionPool.getFramework()));

    @Override
    public Long create(AnimalRoom entity) {
        return animalRoomDAO.insertEntity(entity);
    }

    @Override
    public AnimalRoom retrieveById(Long id) {
        return animalRoomDAO.getEntityById(id);
    }

    @Override
    public void updateById(AnimalRoom entity, Long id) {
        animalRoomDAO.updateEntity(entity, id);
    }

    @Override
    public void delete(AnimalRoom entity) {
        animalRoomDAO.removeEntity(entity);
    }

    @Override
    public List<AnimalRoom> retrieveAll() {
        return animalRoomDAO.getEntities();
    }
}
