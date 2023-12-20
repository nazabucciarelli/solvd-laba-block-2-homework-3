package com.solvd.animals_mvc.dao;

import com.solvd.animals_mvc.dao.IAnimalRoomDAO;
import com.solvd.animals_mvc.dao.IDepartmentDAO;
import com.solvd.animals_mvc.dao.IZooDAO;

public class DBFactory {
    public static IZooDAO getZooDAO(ConnectionTool tool) {
        switch (tool) {
            case JDBC:
                return new com.solvd.animals_mvc.dao.jdbc.impl.ZooDAOImpl();
            case MYBATIS:
                return null;
            default:
                return null;
        }
    }

    public static IDepartmentDAO getDepartmentDAO(ConnectionTool tool) {
        switch (tool) {
            case JDBC:
                return new com.solvd.animals_mvc.dao.jdbc.impl.DepartmentDAOImpl();
            case MYBATIS:
                return null;
            default:
                return null;
        }
    }

    public static IAnimalRoomDAO getAnimalRoomDAO(ConnectionTool tool) {
        switch (tool) {
            case JDBC:
                return new com.solvd.animals_mvc.dao.jdbc.impl.AnimalRoomDAOImpl();
            case MYBATIS:
                return null;
            default:
                return null;
        }
    }
}
