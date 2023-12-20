package com.solvd.animals_mvc.dao.jdbc.impl;

import com.solvd.animals_mvc.dao.ConnectionPool;
import com.solvd.animals_mvc.dao.jdbc.IZooDAO;
import com.solvd.animals_mvc.model.Zoo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZooDAOImpl implements IZooDAO {
    @Override
    public Zoo getEntityById(Long id) {
        Zoo zoo = null;
        String statement = "SELECT * FROM zoos WHERE id = ?;";
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
                    int customers_capacity = rs.getInt("customers_capacity");
                    zoo = new Zoo(name, customers_capacity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
        return zoo;
    }

    @Override
    public Long insertEntity(Zoo entity) {
        String statement = "INSERT INTO zoos (name,customers_capacity) " +
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
            ps.setInt(2, entity.getCustomersCapacity());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
        return 0L;
    }

    @Override
    public void updateEntity(Zoo updatedEntity, Long id) {
        String statement = "UPDATE zoos SET name = ?, customers_capacity = ? " +
                "WHERE id = ?;";
        Connection conn;
        try {
            conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement ps = conn.prepareStatement(statement);) {
            ps.setString(1, updatedEntity.getName());
            ps.setInt(2, updatedEntity.getCustomersCapacity());
            ps.setLong(3, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public void removeEntity(Zoo entity) {
        String statement = "DELETE FROM zoos WHERE name = ? AND " +
                "customers_capacity = ?;";
        Connection conn;
        try {
            conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement ps = conn.prepareStatement(statement)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getCustomersCapacity());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public List<Zoo> getEntities() {
        String statement = "SELECT * FROM zoos";
        List<Zoo> zooList = new ArrayList<>();
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
                int customers_capacity = rs.getInt("customers_capacity");
                Zoo zoo = new Zoo(name, customers_capacity);
                zooList.add(zoo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
        return zooList;
    }
}
