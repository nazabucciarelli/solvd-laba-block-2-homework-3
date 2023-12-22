package com.solvd.animals_mvc.service;

import com.solvd.animals_mvc.dao.ConnectionPool;
import com.solvd.animals_mvc.dao.ConnectionTool;
import com.solvd.animals_mvc.dao.DBFactory;
import com.solvd.animals_mvc.dao.IZooDAO;
import com.solvd.animals_mvc.model.AnimalRoom;
import com.solvd.animals_mvc.model.Department;
import com.solvd.animals_mvc.model.Zoo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ZooService {
    private final IZooDAO zooDAO = DBFactory
            .getZooDAO(ConnectionTool.valueOf(ConnectionPool.getFramework()));
    private final DepartmentService departmentService = new DepartmentService();
    private final AnimalRoomService animalRoomService = new AnimalRoomService();
    private static final Logger LOGGER = LogManager.getLogger(ZooService.class);

    public Long create(Zoo zoo, String departmentName, String animalRoomName,
                       int capacity) { // Let's imagine that we must create a zoo with a department and an animal room because of a business rule
        Long zooId = zooDAO.insertEntity(zoo);
        animalRoomService.create(new AnimalRoom(animalRoomName, capacity, zooId));
        departmentService.create(new Department(departmentName, zooId));
        return zooId;
    }

    public void delete(Long zooId) { // If we delete a Zoo, also we delete its departments and animal rooms.
        Zoo zoo = zooDAO.getEntityById(zooId);
        departmentService.retrieveAll().forEach((d) -> {
            if (d.getZooId().equals(zooId)) {
                departmentService.delete(d);
            }
        });
        animalRoomService.retrieveAll().forEach((ar) -> {
            if (ar.getZooId().equals(zooId)) {
                animalRoomService.delete(ar);
            }
        });
        zooDAO.removeEntity(zoo);
    }

    public void logDepartmentsAndAnimalRooms(Long zooId) {
        LOGGER.info("Departments from the zoo with id " + zooId);
        departmentService.retrieveAll().stream()
                .filter((d) -> d.getZooId().equals(zooId))
                .forEach(LOGGER::info);
        LOGGER.info("Animal rooms from the zoo with id " + zooId);
        animalRoomService.retrieveAll().stream()
                .filter((ar) -> ar.getZooId().equals(zooId))
                .forEach(LOGGER::info);
    }

}
