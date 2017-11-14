/*
 * Travis Ferguson - Apache 2.0 Licensed
 */
package com.overwrittenstack.raceday.service;

import com.overwrittenstack.raceday.dao.BracketDao;
import com.overwrittenstack.raceday.dao.ParticipantDao;
import com.overwrittenstack.raceday.dao.RaceDao;
import com.overwrittenstack.raceday.dao.RoundDao;
import com.overwrittenstack.raceday.dao.VehicleDao;
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
@Controller("participantManager")
public class ParticipantManager {
    @Autowired
    ParticipantDao pDao;
    
    @Autowired
    RoundDao roundDao;
    
    @Autowired
    RaceDao rDao;
    
    @Autowired
    VehicleDao vDao;
    
    @Autowired
    BracketDao bDao;
    
    public Participant getParticipant(int id, boolean loadRace) {
        Participant p = pDao.get(id);
        if(loadRace) {
            int raceId = p.getRaceId();
            Race r = rDao.get(raceId);
            p.setRace(r);
        }
        return p;
    }
    
    public Vehicle getVehicle(int id, boolean loadAll) {
        Vehicle v = vDao.get(id);
        if(v == null) return null;
        if(loadAll) v.setParticipant(getParticipant(v.getParticipantId(), loadAll));
        return v;
    }
    
    public List<Vehicle> getAllVehicles(boolean loadAll) {
        List<Vehicle> v = vDao.getAll();
        if(v == null) return null;
        if(loadAll) {
             List<Vehicle> v2 = new ArrayList<>();
            for(Vehicle vTemp:v) {
                vTemp.setParticipant(getParticipant(vTemp.getParticipantId(), loadAll));
                v2.add(vTemp);
            }
            return v2;
        }
        return v;
    }
    
    
    public List<Vehicle> getAllVehiclesForRace(int raceId, boolean loadAll) {
        List<Vehicle> v = vDao.getByRace(raceId);
        if(v == null) return null;
        if(loadAll) {
             List<Vehicle> v2 = new ArrayList<>();
            for(Vehicle vTemp:v) {
                vTemp.setParticipant(getParticipant(vTemp.getParticipantId(), loadAll));
                v2.add(vTemp);
            }
            return v2;
        }
        return v;
    }
    
    
    public List<Vehicle> getWinnersForRound(int roundId, boolean loadAll) {
        List<Vehicle> v = bDao.getWinnersByRound(roundId);
        if(v == null) return null;
        if(loadAll) {
             List<Vehicle> v2 = new ArrayList<>();
            for(Vehicle vTemp:v) {
                vTemp.setParticipant(getParticipant(vTemp.getParticipantId(), loadAll));
                v2.add(vTemp);
            }
            return v2;
        }
        return v;
    }
    
    public List<Round> getLoadedRounds() {
        List<Round> rounds = roundDao.getAll();
        List<Round> temp = new ArrayList<>();
        
        for(Round r: rounds) {
            int raceId = r.getRaceId();
            Race race = rDao.get(raceId);
            r.setRace(race);
            temp.add(r);
        }
        return temp;
    }
    
    
    public List<Participant> getAllParticipants(boolean loadRace) {
        List<Participant> pList = pDao.getAll();
        if(loadRace) {
            List<Participant> pList2 = new ArrayList<>();
            for(Participant p:pList) {
                int raceId = p.getRaceId();
                Race r = rDao.get(raceId);
                p.setRace(r);
                pList2.add(p);
            }
            return pList2;
        }
        return pList;
    }
    
    
    public List<Participant> getAllParticipantsForRace(int raceId, boolean loadRace) {
        List<Participant> pList = pDao.getByRace(raceId);
        if(loadRace) {
            List<Participant> pList2 = new ArrayList<>();
            for(Participant p:pList) {
                Race r = rDao.get(raceId);
                p.setRace(r);
                pList2.add(p);
            }
            return pList2;
        }
        return pList;
    }
    
    

    /**
     * Create a sample race, participant set, and boat set
     */
    public void loadSample() {
        Race r = new Race();
        r.setName("Sample Race");
        r = rDao.create(r);
        for(int i = 1; i < 25; i++) {
            String name = "SampleP-"+i;
            int raceId = r.getRaceId();
            String tag = "SP"+i;
            Participant p = new Participant();
            p.setName(name);
            p.setRaceId(raceId);
            p = pDao.create(p);
            Vehicle v = new Vehicle();
            v.setParticipantId(p.getParticipantId());
            v.setTag(tag);
            v = vDao.create(v);
        }
    }
}
