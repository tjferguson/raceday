/*
 * Travis Ferguson - Apache 2.0 Licensed
 */
package com.overwrittenstack.raceday.dao;

import com.overwrittenstack.raceday.model.Round;
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
public class RoundDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private static final String ID = "round_id";
    private static final String CREATE = "INSERT INTO public.round(race_id, round_num, completed) VALUES (?, ?, ?);";
    private static final String UPDATE = "update public.round set race_id=?, round_num=?, completed=? where round_id=?";
    private static final String DELETE = "delete from public.round where round_id=?";
    private static final String DELETE_ALL = "delete from public.round";
    
    private static final String SELECT_ONE = "SELECT round_id,race_id, round_num, completed FROM public.round where round_id=?";
    private static final String SELECT_ALL = "SELECT round_id,race_id, round_num, completed FROM public.round;";
    private static final String SELECT_BY = "SELECT round_id,race_id, round_num, completed FROM public.round where race_id=? order by round_num ";
    private static final String SELECT_LAST_BY_RACE = "SELECT round_id,race_id, round_num, completed from public.round where race_id=? order by round_num desc limit 1";
    
    public Round create(Round r) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            //race_id, round_num, completed
            ps.setInt(1, r.getRaceId());
            ps.setInt(2, r.getRound());
            ps.setBoolean(3, r.isCompleted());
            
            return ps;
        }, holder);
        int id = (Integer)holder.getKeys().get(ID);
        r.setRoundId(id);
        return r;
    }
    
    public Round update(Round r) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setInt(1, r.getRaceId());
            ps.setInt(2, r.getRound());
            ps.setBoolean(3, r.isCompleted());
            ps.setInt(4, r.getRoundId());
            return ps;
        }, holder);
        return r;
    }
    
    public boolean delete(int id) {
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(DELETE);
            ps.setInt(1, id);
            return ps;
        });
        return true;
    }
    
    public Round get(int id) {
        List<Round> ps = jdbcTemplate.query(SELECT_ONE, new Integer[] {id}, new RoundMapper());
        if(ps != null && ps.size() > 0) {
            return ps.get(0);
        } else {
            return null;
        }
    }
    
    public List<Round> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new RoundMapper());
    }
    
    public List<Round> getByRace(int raceId) {
        return jdbcTemplate.query(SELECT_BY, new Integer[] {raceId}, new RoundMapper());
    }
    
    public Round getLastByRace(int raceId) {
        List<Round> ps = jdbcTemplate.query(SELECT_LAST_BY_RACE, new Integer[] {raceId}, new RoundMapper());
        if(ps != null && ps.size() > 0) {
            return ps.get(0);
        } else {
            return null;
        }
    }

    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL);
    }
    
    
}


class RoundMapper implements RowMapper {
    @Override
    public Round mapRow(ResultSet rs, int i) throws SQLException {
        Round r = new Round();
        r.setCompleted(rs.getBoolean("completed"));
        r.setRaceId(rs.getInt("race_id"));
        r.setRoundId(rs.getInt("round_id"));
        r.setRound(rs.getInt("round_num"));
        
        return r;
    }
}