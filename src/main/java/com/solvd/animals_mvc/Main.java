package com.solvd.animals_mvc;

import com.solvd.animals_mvc.dao.ConnectionPool;
import com.solvd.animals_mvc.model.AnimalRoom;
import com.solvd.animals_mvc.model.Department;
import com.solvd.animals_mvc.model.Zoo;
import com.solvd.animals_mvc.service.impl.AnimalRoomServiceImpl;
import com.solvd.animals_mvc.service.impl.DepartmentServiceImpl;
import com.solvd.animals_mvc.service.impl.ZooServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        AnimalRoomServiceImpl animalRoomService = new AnimalRoomServiceImpl();
        ZooServiceImpl zooService = new ZooServiceImpl();

        Long californiaZooId = zooService.create(new Zoo("California Zoo", 250));
        zooService.updateById(new Zoo("Texas Zoo", 300), californiaZooId);
        Long americanZooId = zooService.create(new Zoo("American Zoo", 560));
        zooService.retrieveById(americanZooId);
        zooService.retrieveAll();
        zooService.delete(new Zoo("American Zoo", 560));

        animalRoomService.create(new AnimalRoom("Amphibians", 50, californiaZooId));
        Long animalRoomIdBirds = animalRoomService.create(new AnimalRoom("Birds",
                95, californiaZooId));
        animalRoomService.updateById(new AnimalRoom("Birds", 110, californiaZooId),
                animalRoomIdBirds);
        animalRoomService.delete(new AnimalRoom("Amphibians", 50, californiaZooId));
        animalRoomService.retrieveById(animalRoomIdBirds);
        animalRoomService.create(new AnimalRoom("Aquarium", 430, californiaZooId));
        animalRoomService.retrieveAll();

        departmentService.create(new Department("Business Department", californiaZooId));
        Long departmentId = departmentService
                .create(new Department("Vet Department", californiaZooId));
        departmentService.updateById(new Department("HR Department", californiaZooId),
                departmentId);
        departmentService.retrieveAll();
        departmentService.delete(new Department("Vet Department", californiaZooId));
        departmentService.retrieveById(departmentId);

        LOGGER.info(ConnectionPool.getPoolSize());
    }
}
