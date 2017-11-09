package com.overwrittenstack.raceday;

import com.overwrittenstack.raceday.model.Bracket;
import com.overwrittenstack.raceday.model.Vehicle;
import java.security.Principal;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class IndexController {
    @Autowired
    private SettingsBean settings;
    
    @RequestMapping("/")
    public ModelAndView getHome(ModelMap model, Principal principal) {
        ArrayList<ArrayList<Bracket>> brackets = new ArrayList<>();
        ArrayList<Bracket> bracket = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            Bracket b = new Bracket();
            b.setRoundId(0);
            Vehicle v1 = new Vehicle();
            v1.setParticipant(p);
            b.setVech1(v1);
            b.setVech2(v2);
            
            bracket.add(b);
        }
        model.addAttribute("brackets", brackets);
        return new ModelAndView("index", model);
    }
//    
//    @PostMapping("/save")
//    public @ResponseBody Map<String, String> getSave(@RequestBody Note n) {
//        n.setEndpoint(settings.getCloud());
//        dao.create(n);
//        
//        Map<String, String> map = new HashMap<>();
//        map.put("status", "valid");
//        map.put("message", "Saved");
//        
//        return map;
//    }
}