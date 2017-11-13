/*
 * Travis Ferguson - Apache 2.0 Licensed
 */
package com.overwrittenstack.raceday.dao;

import com.overwrittenstack.raceday.model.Participant;
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
public class ParticipantDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private static final String CREATE = "INSERT INTO public.participant(name, race_id) VALUES (?, ?);";
    private static final String UPDATE = "UPDATE public.participant SET name=?, race_id=? WHERE participant_id=?;";
    private static final String DELETE = "DELETE FROM public.participant WHERE participant_id=?;";
    private static final String DELETE_ALL = "DELETE FROM public.participant ";
    
    private static final String SELECT_ONE = "select participant_id, name, race_id from participant where participant_id=?";
    private static final String SELECT_ALL = "select participant_id, name, race_id from participant";
    private static final String SELECT_BY = "select participant_id, name, race_id from participant where race_id=?";

    public Participant create(Participant p) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getRaceId());
            return ps;
        }, holder);
        long id = (Long)holder.getKeys().get("participant_id");
        p.setParticipantId((int)id);
        return p;
    }
    
    
    public boolean delete(int pId) {
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(DELETE);
            ps.setInt(1, pId);
            return ps;
        });
        return true;
    }
    
    public Participant update(Participant p) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getRaceId());
            ps.setInt(3, p.getParticipantId());
            return ps;
        }, holder);
        return p;
    }
    
    
    public Participant get(int pId) {
        List<Participant> ps = jdbcTemplate.query(SELECT_ONE, new Integer[] {pId}, new ParticipantMapper());
        if(ps != null && ps.size() > 0) {
            return ps.get(0);
        } else {
            return null;
        }
    }
    
    public List<Participant> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new ParticipantMapper());
    }
    
    public List<Participant> getByRace(int raceId) {
       return jdbcTemplate.query(SELECT_BY, new Integer[] {raceId}, new ParticipantMapper());
    }

    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL);
    }
}


class ParticipantMapper implements RowMapper {
    @Override
    public Participant mapRow(ResultSet rs, int i) throws SQLException {
        Participant p = new Participant();
        p.setName(rs.getString("name"));
        p.setParticipantId(rs.getInt("participant_id"));
        p.setRaceId(rs.getInt("race_id"));
        return p;
    }
}