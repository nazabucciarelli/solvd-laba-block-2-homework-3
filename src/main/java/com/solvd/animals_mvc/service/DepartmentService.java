package com.solvd.animals_mvc.service;

import com.solvd.animals_mvc.dao.ConnectionPool;
import com.solvd.animals_mvc.dao.ConnectionTool;
import com.solvd.animals_mvc.dao.DBFactory;
import com.solvd.animals_mvc.dao.IDepartmentDAO;
import com.solvd.animals_mvc.model.AnimalRoom;
import com.solvd.animals_mvc.model.Department;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentService {
    private final IDepartmentDAO departmentDAO = DBFactory
            .getDepartmentDAO(ConnectionTool.valueOf(ConnectionPool.getFramework()));
    private final AnimalRoomService animalRoomService = new AnimalRoomService();
    private static final Logger LOGGER = LogManager.getLogger(DepartmentService.class);

    public Long create(Department department) {
        return departmentDAO.insertEntity(department);
    }

    public List<AnimalRoom> getAnimalRoomsInSameZoo(Long departmentId) { // To get the animal rooms that are in the same zoo as the department.
        Department department = departmentDAO.getEntityById(departmentId);
        LOGGER.info("The animal rooms within the same zoo as the department are:");
        return animalRoomService.retrieveAll().stream().filter((ar) -> ar
                        .getZooId()
                        .equals(department.getZooId()))
                .peek(LOGGER::info)
                .collect(Collectors.toList());
    }

    public void delete(Department department) {
        departmentDAO.removeEntity(department);
    }

    public List<Department> retrieveAll() {
        return departmentDAO.getEntities();
    }
}
