package com.solvd.animals_mvc.dao.jdbc.impl;

import com.solvd.animals_mvc.dao.ConnectionPool;
import com.solvd.animals_mvc.dao.IAnimalRoomDAO;
import com.solvd.animals_mvc.model.AnimalRoom;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalRoomDAOImpl implements IAnimalRoomDAO {
    private static final Logger LOGGER = LogManager
            .getLogger(AnimalRoomDAOImpl.class);

    @Override
    public AnimalRoom getEntityById(Long id) {
        AnimalRoom animalRoom = null;
        String statement = "SELECT * FROM animal_rooms WHERE id = ?;";
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
                    int capacity = rs.getInt("capacity");
                    Long zooId = rs.getLong("zoos_id");
                    animalRoom = new AnimalRoom(name, capacity, zooId);
                    LOGGER.info("Get Animal Room by ID " + id + ": " + animalRoom);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
        return animalRoom;
    }

    @Override
    public Long insertEntity(AnimalRoom entity) {
        String statement = "INSERT INTO animal_rooms (name,capacity,zoos_id) " +
                "VALUES (?,?,?);";
        Connection conn;
        try {
            conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement ps = conn.prepareStatement(statement,
                PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getCapacity());
            ps.setLong(3, entity.getZooId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    Long id = rs.getLong(1);
                    LOGGER.info("Animal Room entity was inserted with the ID:" + id);
                    return id;
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
    public void updateEntity(AnimalRoom updatedEntity, Long id) {
        String statement = "UPDATE animal_rooms SET name = ?, capacity = ?," +
                " zoos_id = ? WHERE id = ?;";
        Connection conn;
        try {
            conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement ps = conn.prepareStatement(statement);) {
            ps.setString(1, updatedEntity.getName());
            ps.setInt(2, updatedEntity.getCapacity());
            ps.setLong(3, updatedEntity.getZooId());
            ps.setLong(4, id);
            ps.executeUpdate();
            LOGGER.info("Animal Room entity with the ID " + id + " was updated");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public void removeEntity(AnimalRoom entity) {
        String statement = "DELETE FROM animal_rooms WHERE name = ? AND " +
                "capacity = ? AND zoos_id = ?;";
        Connection conn;
        try {
            conn = ConnectionPool.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try (PreparedStatement ps = conn.prepareStatement(statement)) {
            ps.setString(1, entity.getName());
            ps.setInt(2, entity.getCapacity());
            ps.setLong(3, entity.getZooId());
            ps.executeUpdate();
            LOGGER.info("Animal Room entity with the data " + entity
                    + " was removed");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
    }

    @Override
    public List<AnimalRoom> getEntities() {
        String statement = "SELECT * FROM animal_rooms";
        List<AnimalRoom> animalRoomList = new ArrayList<>();
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
                int capacity = rs.getInt("capacity");
                Long zooId = rs.getLong("zoos_id");
                AnimalRoom animalRoom = new AnimalRoom(name, capacity, zooId);
                animalRoomList.add(animalRoom);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.releaseConnection(conn);
        }
        return animalRoomList;
    }
}
