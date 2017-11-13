/*
 * Travis Ferguson - Apache 2.0 Licensed
 */
package com.overwrittenstack.raceday.dao;

import com.overwrittenstack.raceday.model.Vehicle;
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
public class VehicleDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private static final String CREATE = "INSERT INTO public.vechicle(participant_id, tag) VALUES (?, ?);";
    private static final String UPDATE = "UPDATE public.vechicle SET participant_id=?, tag=? WHERE vechicle_id=?;";
    private static final String DELETE = "DELETE FROM public.vechicle WHERE vechicle_id=?;";
    private static final String DELETE_ALL = "DELETE FROM public.vechicle";
    
    private static final String SELECT_ONE = "SELECT vechicle_id, participant_id, tag FROM public.vechicle where vechicle_id=?;";
    private static final String SELECT_ALL = "SELECT vechicle_id, participant_id, tag FROM public.vechicle";
    private static final String SELECT_BY = "SELECT vechicle_id, vechicle.participant_id as participant_id, tag FROM public.vechicle join public.participant on vechicle.participant_id = participant.participant_id where race_id=?";
    
    public Vehicle create(Vehicle v) {
         KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, v.getParticipantId());
            ps.setString(2, v.getTag());
            return ps;
        }, holder);
        int id = (Integer)holder.getKeys().get("vechicle_id");
        v.setVehicleId(id);
        return v;
    }
    
    public boolean delete(int vId) {
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(DELETE);
            ps.setInt(1, vId);
            return ps;
        });
        return true;
    }
    
    public Vehicle update(Vehicle v) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setInt(1, v.getParticipantId());
            ps.setString(2, v.getTag());
            ps.setInt(3, v.getVehicleId());
            
            return ps;
        }, holder);
        return v;
    }
    
    public Vehicle get(int vId) {
        List<Vehicle> vech = jdbcTemplate.query(SELECT_ONE, new Integer[] {vId}, new VehicleMapper());
        if(vech != null && vech.size() > 0) {
            return vech.get(0);
        } else {
            return null;
        }
    }
    
    public List<Vehicle> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new VehicleMapper());
    }
    
    public List<Vehicle> getByRace(int raceId) {
       return jdbcTemplate.query(SELECT_BY, new Integer[] {raceId}, new VehicleMapper());
    }

     public void deleteAll() {
         jdbcTemplate.update(DELETE_ALL);
    }
}



class VehicleMapper implements RowMapper {
    @Override
    public Vehicle mapRow(ResultSet rs, int i) throws SQLException {
        Vehicle v = new Vehicle();
        v.setParticipantId(rs.getInt("participant_id"));
        v.setTag(rs.getString("tag"));
        v.setVehicleId(rs.getInt("vechicle_id"));
        return v;
    }
}