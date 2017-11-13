/*
 * Travis Ferguson - Apache 2.0 Licensed
 */
package com.overwrittenstack.raceday.dao;

import com.overwrittenstack.raceday.model.Race;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Travis
 */
@Repository
public class RaceDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private static final String UPDATE = "update race set race_name=? where race_id=?";
    private static final String INSERT = "insert into race (race_name) values(?);";
    private static final String DELETE = "delete from race where race_id=?";
    private static final String DELETE_ALL = "delete from race";
    
    private static final String GET_ONE = "SELECT race_id, race_name from race where race_id=?";
    private static final String GET = "select race_id, race_name from race";
    
    public Race create(Race r) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, r.getName());
            return ps;
        }, holder);
        int id = (Integer)holder.getKeys().get("race_id");
        r.setRaceId(id);
        return r;
    }
    
    public Race update(Race r) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setString(1, r.getName());
            ps.setInt(2, r.getRaceId());
            return ps;
        }, holder);
        return r;
    }
    
    
    public boolean delete(int rId) {
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(DELETE);
            ps.setInt(1, rId);
            return ps;
        });
        return true;
    }
    
    public Race get(int raceId) {
        List<Race> races = jdbcTemplate.query(GET_ONE, new Integer[] {raceId}, new RaceMapper());
        if(races != null && races.size() > 0) {
            return races.get(0);
        } else {
            return null;
        }
    }
    
    public List<Race> getAll() {
        return jdbcTemplate.query(GET, new RaceMapper());
    }

    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL);
    }
}

class RaceMapper implements RowMapper {
    @Override
    public Race mapRow(ResultSet rs, int i) throws SQLException {
        Race r = new Race();
        r.setRaceId(rs.getInt("race_id"));
        r.setName(rs.getString("race_name"));
        return r;
    }
}