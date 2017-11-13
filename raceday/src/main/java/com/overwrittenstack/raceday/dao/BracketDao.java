/*
 * Travis Ferguson - Apache 2.0 Licensed
 */
package com.overwrittenstack.raceday.dao;

import com.overwrittenstack.raceday.model.Bracket;
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
public class BracketDao {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    private static final String ID = "bracket_id";
    private static final String CREATE = "insert into bracket (round_id, vech1_id, vech2_id, winner, prev_bracket) values(?, ?, ?, ?, ?);";
    private static final String UPDATE = "update bracket set round_id=?, vech1_id=?, vech2_id=?, winner=?, prev_bracket=? where bracket_id=?";
    private static final String DELETE = "delete from public.bracket where bracket_id=?";
    private static final String DELETE_ALL = "delete from public.bracket";

    private static final String SELECT_ONE = "SELECT bracket_id, round_id, vech1_id, vech2_id, winner, prev_bracket FROM public.bracket where bracket_id=?";
    private static final String SELECT_ALL = "SELECT bracket_id, round_id, vech1_id, vech2_id, winner, prev_bracket FROM public.bracket;";
    private static final String SELECT_BY = "SELECT bracket_id, round_id, vech1_id, vech2_id, winner, prev_bracket FROM public.bracket where round_id=?";
    private static final String SELECT_NEXT = "SELECT bracket_id, round_id, vech1_id, vech2_id, winner, prev_bracket FROM public.bracket where round_id=? and (winner = null OR winner=0) limit 1";
    private static final String SELECT_WINNERS = "SELECT vechicle_id, participant_id, tag FROM public.vechicle where vechicle_id in (select winner from public.bracket where round_id=?)";
    
    public Bracket create(Bracket b) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, b.getRoundId());
            ps.setInt(2, b.getVech1Id());
            ps.setInt(3, b.getVech2Id());
            ps.setInt(4, b.getWinnerId());
            ps.setInt(5, b.getLastBracketId());
            return ps;
        }, holder);
        int id = (Integer)holder.getKeys().get(ID);
        b.setBracketId(id);
        return b;
    }
    
    public Bracket update(Bracket b) {
        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setInt(1, b.getRoundId());
            ps.setInt(2, b.getVech1Id());
            ps.setInt(3, b.getVech2Id());
            ps.setInt(4, b.getWinnerId());
            ps.setInt(5, b.getLastBracketId());
            ps.setInt(6, b.getBracketId());
            return ps;
        }, holder);
        return b;
    }
    
    public boolean delete(int id) {
        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(DELETE);
            ps.setInt(1, id);
            return ps;
        });
        return true;
    }
    
    public Bracket get(int id) {
        List<Bracket> ps = jdbcTemplate.query(SELECT_ONE, new Integer[] {id}, new BracketMapper());
        if(ps != null && ps.size() > 0) {
            return ps.get(0);
        } else {
            return null;
        }
    }
    
    public Bracket getNext(int id) {
        List<Bracket> ps = jdbcTemplate.query(SELECT_NEXT, new Integer[] {id}, new BracketMapper());
        if(ps != null && ps.size() > 0) {
            return ps.get(0);
        } else {
            return null;
        }
    }
    
    public List<Bracket> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new BracketMapper());
    }
    
    public List<Bracket> getByRound(int roundId) {
        return jdbcTemplate.query(SELECT_BY, new Integer[] {roundId}, new BracketMapper());
    }
    
    public List<Vehicle> getWinnersByRound(int roundId) {
        return jdbcTemplate.query(SELECT_WINNERS, new Integer[] {roundId}, new VehicleMapper());
    }

    
    public void deleteAll() {
        jdbcTemplate.update(DELETE_ALL);
    }
}



class BracketMapper implements RowMapper {
    @Override
    public Bracket mapRow(ResultSet rs, int i) throws SQLException {
        Bracket b = new Bracket();
        //bracket_id, round_id, vech1_id, vech2_id, winner, prev_bracket
        b.setBracketId(rs.getInt("bracket_id"));
        b.setRoundId(rs.getInt("round_id"));
        b.setVech1Id(rs.getInt("vech1_id"));
        b.setVech2Id(rs.getInt("vech2_id"));
        b.setWinnerId(rs.getInt("winner"));
        b.setLastBracketId(rs.getInt("prev_bracket"));
        return b;
    }
}