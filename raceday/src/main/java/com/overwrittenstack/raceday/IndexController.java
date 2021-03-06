package com.overwrittenstack.raceday;

import com.overwrittenstack.raceday.dao.BracketDao;
import com.overwrittenstack.raceday.dao.ParticipantDao;
import com.overwrittenstack.raceday.dao.RaceDao;
import com.overwrittenstack.raceday.dao.RoundDao;
import com.overwrittenstack.raceday.dao.VehicleDao;
import com.overwrittenstack.raceday.helper.RaceRandomizer;
import com.overwrittenstack.raceday.model.Bracket;
import com.overwrittenstack.raceday.model.Participant;
import com.overwrittenstack.raceday.model.Race;
import com.overwrittenstack.raceday.model.Registration;
import com.overwrittenstack.raceday.model.Round;
import com.overwrittenstack.raceday.model.Vehicle;
import com.overwrittenstack.raceday.model.WinnerRequest;
import com.overwrittenstack.raceday.service.BracketManager;
import com.overwrittenstack.raceday.service.ParticipantManager;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
    @Autowired
    private SettingsBean settings;
    
    @Autowired
    private BracketManager bracketManager;
    
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
    
    @RequestMapping("/")
    public ModelAndView getHome(ModelMap model, Principal principal) {
        List<Race> races = raceDao.getAll();
        model.addAttribute("races", races);
        Race race = raceDao.getFirst();
        if(race == null) {
            race = new Race();
        }
        System.out.println("Race: " + race + " - " + race.getRaceId());
        String raceName = race.getName();
        model.addAttribute("raceName", raceName);
        List<List<Bracket>> brackets = bracketManager.getLoadedBracket(race.getRaceId());
        System.out.println("Brackets: " + brackets);
        if(brackets.size() > 0) model.addAttribute("valid", true);
        else model.addAttribute("valid", false);
        model.addAttribute("brackets", brackets);
        return new ModelAndView("index", model);
    }
    
    
    @RequestMapping("/bracket")
    public ModelAndView getHomeById(@RequestParam("id") int id, ModelMap model) {
        List<Race> races = raceDao.getAll();
        model.addAttribute("races", races);
        
        Race race = raceDao.get(id);
        if(race == null) {
            race = new Race();
        }
        List<List<Bracket>> brackets = bracketManager.getLoadedBracket(race.getRaceId());
        String raceName = race.getName();
        
        if(brackets.size() > 0) model.addAttribute("valid", true);
        else model.addAttribute("valid", false);
        
        model.addAttribute("raceName", raceName);
        model.addAttribute("brackets", brackets);
        return new ModelAndView("index", model);
    }
    
    
    
    @RequestMapping("/races")
    public ModelAndView getRaces(ModelMap model, Principal principal) {
        model.addAttribute("page", "races");
        List<Race> races = raceDao.getAll();
        
        model.addAttribute("races", races);
        return new ModelAndView("admin", model);
    }
    
    @PostMapping("/race")
    public ModelAndView postCreateOverlay(@ModelAttribute Race race, BindingResult bindingResult, ModelMap model) {
        if(race != null) {
            if(race.getRaceId() == -1) {
                raceDao.create(race);
            } else {
                raceDao.update(race);
            }
        }
        model.addAttribute("page", "admin_home");
        return new ModelAndView("admin", model);
    }
     
    
    @RequestMapping("/race")
    public ModelAndView getRace(@RequestParam("id") int id, ModelMap model) {
        model.addAttribute("page", "race");
        Race race = raceDao.get(id);
        model.addAttribute("raceId", id);
        if(race == null) race = new Race();
        model.addAttribute("race", race);
        
        return new ModelAndView("admin", model);
    }
    
    
    
    @RequestMapping("/boats")
    public ModelAndView getBoats(ModelMap model, Principal princ) {
        List<Vehicle> vehicles = participantManager.getAllVehicles(true);
        model.addAttribute("page", "boats");
        model.addAttribute("vehicles", vehicles);
        return new ModelAndView("admin", model);
    }
    
    @RequestMapping("/boat")
    public ModelAndView getBoat(@RequestParam("id") int id, ModelMap model) {
        model.addAttribute("page", "boat");
        Vehicle v = participantManager.getVehicle(id, true);
        if(id == -1) {
            v = new Vehicle();
            v.setVehicleId(-1);
        }
        List<Participant> participants = participantManager.getAllParticipants(true);
        model.addAttribute("participants", participants);
        model.addAttribute("vehicle", v);
        model.addAttribute("vehicleId", id);
        return new ModelAndView("admin", model);
    }
    
    @PostMapping("/boat")
    public ModelAndView postPart(@ModelAttribute Vehicle v, BindingResult bindingResult, ModelMap model) {
        if(v != null) {
            if(v.getVehicleId() == -1) {
                vDao.create(v);
            } else {
                vDao.update(v);
            }
        }
        List<Vehicle> vehicles = participantManager.getAllVehicles(true);
        model.addAttribute("page", "boats");
        model.addAttribute("vehicles", vehicles);
        return new ModelAndView("admin", model);
    }
    
    
    @RequestMapping("/sampledata")
    public ModelAndView sampleData(ModelMap model, Principal princ) {
        participantManager.loadSample();
        
        model.addAttribute("page", "admin_home");
        return new ModelAndView("admin", model);
    }
    
    
    @RequestMapping("/cleardata1234")
    public ModelAndView clearData(ModelMap model, Principal princ) {
        bDao.deleteAll();
        roundDao.deleteAll();
        vDao.deleteAll();
        pDao.deleteAll();
        raceDao.deleteAll();
        model.addAttribute("page", "admin_home");
        return new ModelAndView("admin", model);
    }
    
    
    @RequestMapping("/participants")
    public ModelAndView getPartis(ModelMap model, Principal princ) {
        List<Participant> participants = participantManager.getAllParticipants(true);
        model.addAttribute("page", "participants");
        model.addAttribute("participants", participants);
        return new ModelAndView("admin", model);
    }
    
    
    @RequestMapping("/participant")
    public ModelAndView getPart(@RequestParam("id") int id, ModelMap model) {
        Participant participant = new Participant();
        List<Race> races = raceDao.getAll();
        if(id != -1) {
            participant = participantManager.getParticipant(id, true);
        } else {
            participant.setParticipantId(-1);
        }
        model.addAttribute("page", "participant");
        model.addAttribute("participant", participant);
        model.addAttribute("races", races);
        model.addAttribute("participantId", id);
        
        return new ModelAndView("admin", model);
    }
    
    @PostMapping("/participant")
    public ModelAndView postPart(@ModelAttribute Participant p, BindingResult bindingResult, ModelMap model) {
        System.out.println("Participant passed: "+ p);
        if(p != null) {
            System.out.println("P:" + p.getParticipantId());
            System.out.println("P:" + p.getName());
            System.out.println("P:" + p.getRaceId());
            
            if(p.getParticipantId() == -1) {
                pDao.create(p);
            } else {
                pDao.update(p);
            }
        }
        
        List<Participant> participants = participantManager.getAllParticipants(true);
        model.addAttribute("page", "participants");
        model.addAttribute("participants", participants);
        return new ModelAndView("admin", model);
    }
    
    @RequestMapping("/racemgmt")
    public ModelAndView getRaceMgmtSimple(ModelMap model, Principal princ) {
        model.addAttribute("page", "racemgmt");
        List<Race> races = raceDao.getAll();
        
        model.addAttribute("races", races);
        return new ModelAndView("admin", model);
    }
    
    @RequestMapping("/racemgmt2")
    public ModelAndView getRaceMgmtRace(@RequestParam("id") int id, ModelMap model) {
        //id is raceId
        //get our max round and if it is completed yet
        //if no round, or existing round is completed, we need to create a new round
        //assuming the participants from last round > 2
        boolean giveBracket = true;
        Round r = roundDao.getLastByRace(id);
        if(r == null) {
            //Create a new round 1
            r = new Round();
            r.setCompleted(false);
            r.setRaceId(id);
            r = roundDao.create(r);
            
            //If new round, we need to pull the participants list from the race participants vs last round
            List<Vehicle> vechs = participantManager.getAllVehiclesForRace(id, true);
            List<Bracket> brackets = RaceRandomizer.makeBracket(vechs, r.getRoundId(), 0, true);
            for(Bracket b:brackets) {
                bDao.create(b);
            }
            r = roundDao.getLastByRace(id);
        } else if(r.isCompleted()) {
            //Next round, but we had a previous one, so we are good
            int prevRoundId = r.getRoundId();
            //Select our winners from the previous round
            List<Vehicle> vechs = participantManager.getWinnersForRound(prevRoundId, true);
            
            if(vechs != null && vechs.size() > 1) {
                Round r2 = new Round();
                r2.setCompleted(false);
                r2.setRaceId(id);
                r2.setRound(r.getRound()+1);
                r = roundDao.create(r2);

                List<Bracket> brackets = RaceRandomizer.makeBracket(vechs, r.getRoundId(), 0, false);
                brackets.forEach((b) -> {
                    bDao.create(b);
                });
            } else {
                System.out.println("Set message");
                model.addAttribute("message", "This race is completed");
                giveBracket = false;
            }
        }
        if(giveBracket) {
            Bracket bracket = bracketManager.getNext(r.getRoundId());
            if(bracket.getVech1() != null  && bracket.getVech1().getParticipant() != null) {
                model.addAttribute("vech1name", bracket.getVech1().getParticipant().getName());
                model.addAttribute("vech1tag", bracket.getVech1().getTag());
            }

            if(bracket.getVech2() != null && bracket.getVech2().getParticipant() != null) {
                model.addAttribute("vech2name", bracket.getVech2().getParticipant().getName());
                model.addAttribute("vech2tag", bracket.getVech2().getTag());
            } else {
                model.addAttribute("vech2name", "Bye");
                model.addAttribute("vech2tag", "Bye");
            }
            
            model.addAttribute("bracket", bracket);
            model.addAttribute("roundNum", bracket.getRound().getRound());
        } else {
            model.addAttribute("bracket", new Bracket());
        }
        
        model.addAttribute("page", "racemgmt2");
        return new ModelAndView("admin", model);
    }
    
    
    @RequestMapping("/registration")
    public ModelAndView getReg(ModelMap model, Principal principal) {
        model.addAttribute("page", "registration");
        List<Race> races = this.raceDao.getAll();
        model.addAttribute("races", races);
        return new ModelAndView("admin", model);
    }
    
    @PostMapping("/registration")
    public ModelAndView postRegistration(@ModelAttribute Registration reg, BindingResult bindingResult, ModelMap model) {
        if(reg != null) {
            try {
                String name = reg.getName();
                int raceId = reg.getRaceId();
                String tag = reg.getVehicleTag();
                Participant p = new Participant();
                p.setName(name);
                p.setRaceId(raceId);
                p = pDao.create(p);
                Vehicle v = new Vehicle();
                v.setParticipantId(p.getParticipantId());
                v.setTag(tag);
                v = vDao.create(v);
                model.addAttribute("message", "Created new vehicle " + v.getVehicleId()+" and  participant " + p.getParticipantId());
            } catch(Exception e) {
                model.addAttribute("message", "Failed to create: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            model.addAttribute("message", "Cannot create a new blank registration");
        }
        List<Race> races = this.raceDao.getAll();
        model.addAttribute("races", races);
        model.addAttribute("page", "registration");
        return new ModelAndView("admin", model);
    }
    
    
    @RequestMapping("/admin")
    public ModelAndView getAdmin(ModelMap model, Principal principal) {
        model.addAttribute("page", "admin_home");
        return new ModelAndView("admin", model);
    }
    
    
    @RequestMapping("/manualedit")
    public ModelAndView getManualEdit(ModelMap model, Principal principal) {
        List<Round> rounds = participantManager.getLoadedRounds();
        List<Vehicle> vehicles = participantManager.getAllVehicles(true);
        List<Bracket> brackets = bDao.getAll();
        
        model.addAttribute("rounds", rounds);
        model.addAttribute("vehicles", vehicles);
        model.addAttribute("page", "manualedit");
        model.addAttribute("brackets", brackets);
        
        return new ModelAndView("admin", model);
    }
    
    
    @PostMapping("/savebracket")
    public @ResponseBody Map<String, String> getSave(@RequestBody WinnerRequest wr) {
        int bracketId = wr.getBracketId();
        System.out.println("Winner: " + bracketId + " - " + wr.getWinner());
        Bracket b = bDao.get(bracketId);
        b.setWinnerId(wr.getWinner());
        bDao.update(b);
        
        int roundId = b.getRoundId();
        Bracket next = bDao.getNext(roundId);
        if(next == null) {
            //Complete our round out
            Round r = roundDao.get(roundId);
            r.setCompleted(true);
            roundDao.update(r);
        }
        
        Map<String, String> map = new HashMap<>();
        map.put("status", "valid");
        map.put("message", "Saved");
        
        return map;
    }
    
    @RequestMapping("/race/delete")
    public ModelAndView deleteRace(@RequestParam("id") int id, ModelMap model) {
        raceDao.delete(id);
        model.addAttribute("page", "admin_home");
        return new ModelAndView("admin", model);
    }
    @RequestMapping("/participant/delete")
    public ModelAndView deletePart(@RequestParam("id") int id, ModelMap model) {
        pDao.delete(id);
        model.addAttribute("page", "admin_home");
        return new ModelAndView("admin", model);
    }
    @RequestMapping("/boat/delete")
    public ModelAndView deleteBoat(@RequestParam("id") int id, ModelMap model) {
        vDao.delete(id);
        model.addAttribute("page", "admin_home");
        return new ModelAndView("admin", model);
    }
    
    @PostMapping("/manual/bracket")
    public  @ResponseBody Map<String, String> getManualSaveBracket(@RequestBody Bracket bracket) {
        bDao.update(bracket);
        
        Map<String, String> map = new HashMap<>();
        map.put("status", "valid");
        map.put("message", "Saved");
        
        return map;
    }
    
}