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

import java.sql.*;

public class Main {
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl();
        AnimalRoomServiceImpl animalRoomService = new AnimalRoomServiceImpl();
        ZooServiceImpl zooService = new ZooServiceImpl();

        Long zooId = zooService.create(new Zoo("California Zoo",250));
        animalRoomService.create(new AnimalRoom("Amphibians",50,zooId));
        animalRoomService.create(new AnimalRoom("Birds",95,zooId));
        departmentService.create(new Department("Business Department",zooId));
    }
}
