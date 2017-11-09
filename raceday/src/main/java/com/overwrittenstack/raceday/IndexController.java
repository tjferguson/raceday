package com.overwrittenstack.raceday;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class IndexController {
    @Autowired
    private SettingsBean settings;
    
//    @RequestMapping("/")
//    public ModelAndView getHome(ModelMap model, Principal principal) {
//        
//    }
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