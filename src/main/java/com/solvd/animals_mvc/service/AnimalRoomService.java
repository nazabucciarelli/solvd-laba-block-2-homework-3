package com.solvd.animals_mvc.service;

import com.solvd.animals_mvc.dao.ConnectionPool;
import com.solvd.animals_mvc.dao.ConnectionTool;
import com.solvd.animals_mvc.dao.DBFactory;
import com.solvd.animals_mvc.dao.IAnimalRoomDAO;
import com.solvd.animals_mvc.model.AnimalRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class AnimalRoomService {
    private final IAnimalRoomDAO animalRoomDAO = DBFactory
            .getAnimalRoomDAO(ConnectionTool.valueOf(ConnectionPool.getFramework()));
    private static final Logger LOGGER = LogManager.getLogger(AnimalRoomService.class);

    public Long create(AnimalRoom animalRoom) {
        return animalRoomDAO.insertEntity(animalRoom);
    }

    public List<AnimalRoom> getAnimalRoomsCapacityOver100() { // To get the animal rooms with a capacity over 100 animals.
        LOGGER.info("The animal rooms with capacity over 100 are:");
        return retrieveAll().stream()
                .filter((ar) -> ar.getCapacity() > 100).peek(LOGGER::info)
                .collect(Collectors.toList());
    }

    public void delete(AnimalRoom animalRoom) {
        animalRoomDAO.removeEntity(animalRoom);
    }

    public List<AnimalRoom> retrieveAll() {
        return animalRoomDAO.getEntities();
    }
}
