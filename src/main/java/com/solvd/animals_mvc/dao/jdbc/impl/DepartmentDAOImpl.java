package com.solvd.animals_mvc.dao.jdbc.impl;

import com.solvd.animals_mvc.dao.ConnectionPool;
import com.solvd.animals_mvc.dao.jdbc.IDepartmentDAO;
import com.solvd.animals_mvc.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements IDepartmentDAO {
    @Override
    public Department getEntityById(Long id) {
        Department department = null;
        String statement = "SELECT * FROM departments WHERE id = ?;";
        Connection conn;
        try {
            conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement ps = conn.prepareStatement(statement);) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    Long zooId = rs.getLong("zoos_id");
                    department = new Department(name, zooId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
        return department;
    }

    @Override
    public Long insertEntity(Department entity) {
        String statement = "INSERT INTO departments (name,zoos_id) " +
                "VALUES (?,?);";
        Connection conn;
        try {
            conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement ps = conn.prepareStatement(statement,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getName());
            ps.setLong(2, entity.getZooId());
            ps.executeUpdate();
            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
        return null;
    }

    @Override
    public void updateEntity(Department updatedEntity, Long id) {
        String statement = "UPDATE departments SET name = ?, zoos_id = ? WHERE" +
                " id = ?;";
        Connection conn;
        try {
            conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement ps = conn.prepareStatement(statement);) {
            ps.setString(1, updatedEntity.getName());
            ps.setLong(2, updatedEntity.getZooId());
            ps.setLong(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public void removeEntity(Department entity) {
        String statement = "DELETE FROM departments WHERE name = ? AND " +
                "zoos_id = ?;";
        Connection conn;
        try {
            conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement ps = conn.prepareStatement(statement)) {
            ps.setString(1, entity.getName());
            ps.setLong(2, entity.getZooId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public List<Department> getEntities() {
        String statement = "SELECT * FROM departments;";
        List<Department> departmentList = new ArrayList<>();
        Connection conn;
        try {
            conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement ps = conn.prepareStatement(statement);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("name");
                Long zooId = rs.getLong("zoos_id");
                Department department = new Department(name, zooId);
                departmentList.add(department);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
        return departmentList;
    }
}
