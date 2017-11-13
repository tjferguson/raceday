/*
 * Travis Ferguson - Apache 2.0 Licensed
 */
package com.overwrittenstack.raceday.service;

import com.overwrittenstack.raceday.SettingsBean;
import com.overwrittenstack.raceday.dao.BracketDao;
import com.overwrittenstack.raceday.dao.ParticipantDao;
import com.overwrittenstack.raceday.dao.RaceDao;
import com.overwrittenstack.raceday.dao.RoundDao;
import com.overwrittenstack.raceday.dao.VehicleDao;
import com.overwrittenstack.raceday.model.Bracket;
import com.overwrittenstack.raceday.model.Participant;
import com.overwrittenstack.raceday.model.Race;
import com.overwrittenstack.raceday.model.Round;
import com.overwrittenstack.raceday.model.Vehicle;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Travis
 */
@Controller("bracketManager")
public class BracketManager {
    @Autowired
    SettingsBean settings;
    
    @Autowired
    private ParticipantManager participantManager;
    
    @Autowired
    private RaceDao raceDao;
    
    @Autowired
    private ParticipantDao pDao;
    
    @Autowired
    private VehicleDao vDao;
    
    @Autowired
    private BracketDao bDao;
    
    @Autowired
    private RoundDao roundDao;
    
    /**
     * Load a bracket structure from the data source based on the race id
     * 
     * @param raceId
     * @return 
     */
    public List<List<Bracket>> getLoadedBracket(int raceId) {
        List<List<Bracket>> brackets = new ArrayList<>();
        //get rounds first
        List<Round> rounds = roundDao.getByRace(raceId);
        for(Round r:rounds) {
            List<Bracket> bracket = bDao.getByRound(r.getRoundId());
            List<Bracket> bracketTemp = new ArrayList<>();
            for(Bracket b: bracket) {
                Vehicle v1 = participantManager.getVehicle(b.getVech1Id(), true);
                Vehicle v2 = new Vehicle();
                
                if(b.getVech2Id() > 0) {
                    v2 = participantManager.getVehicle(b.getVech2Id(), true);
                } else {
                    v2.setTag("Bye");
                    Participant p = new Participant();
                    p.setName("Bye");
                    p.setParticipantId(-1);
                    v2.setParticipant(p);
                    v2.setParticipantId(-1);
                }
                if(b.getWinnerId() > 0) {
                    b.setWinner(participantManager.getVehicle(b.getWinnerId(), true));
                }
                b.setVech1(v1);
                b.setVech2(v2);
                bracketTemp.add(b);
            }
            brackets.add(bracketTemp);
        }
        return brackets;
    }

    public Bracket getNext(int roundId) {
        System.out.println("Round id passed was:" + roundId);
        Bracket b = bDao.getNext(roundId);
        Round r = roundDao.get(roundId);
        Race race = raceDao.get(r.getRaceId());
        r.setRace(race);
        b.setRound(r);
        b.setVech1(participantManager.getVehicle(b.getVech1Id(), true));
        b.setVech2(participantManager.getVehicle(b.getVech2Id(), true));
        b.setWinner(participantManager.getVehicle(b.getWinnerId(), true));
        return b;
    }
    
}
