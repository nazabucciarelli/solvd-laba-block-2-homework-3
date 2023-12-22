package com.solvd.animals_mvc;

import com.solvd.animals_mvc.dao.ConnectionPool;
import com.solvd.animals_mvc.model.AnimalRoom;
import com.solvd.animals_mvc.model.Department;
import com.solvd.animals_mvc.model.Zoo;
import com.solvd.animals_mvc.service.AnimalRoomService;
import com.solvd.animals_mvc.service.DepartmentService;
import com.solvd.animals_mvc.service.ZooService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        DepartmentService departmentService = new DepartmentService();
        AnimalRoomService animalRoomService = new AnimalRoomService();
        ZooService zooService = new ZooService();

        Long zooId = zooService.create(new Zoo("California Zoo", 1000),
                "Business Department", "Amphibians", 170);
        animalRoomService.create(new AnimalRoom("Birds", 200, zooId));
        animalRoomService.getAnimalRoomsCapacityOver100();
        Long departmentId = departmentService
                .create(new Department("Vet Department", zooId));
        departmentService.getAnimalRoomsInSameZoo(departmentId);
        zooService.logDepartmentsAndAnimalRooms(zooId);
        zooService.delete(zooId);

        LOGGER.info(ConnectionPool.getPoolSize());
    }
}
