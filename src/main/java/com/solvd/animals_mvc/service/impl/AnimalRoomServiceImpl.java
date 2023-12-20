package com.solvd.animals_mvc.service.impl;

import com.solvd.animals_mvc.dao.jdbc.impl.AnimalRoomDAOImpl;
import com.solvd.animals_mvc.model.AnimalRoom;
import com.solvd.animals_mvc.service.IAnimalRoomService;

import java.util.List;

public class AnimalRoomServiceImpl implements IAnimalRoomService {
    AnimalRoomDAOImpl animalRoomDAO = new AnimalRoomDAOImpl();

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
        animalRoomDAO.updateEntity(entity,id);
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
