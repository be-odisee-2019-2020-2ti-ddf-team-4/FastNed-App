package be.odisee.travelbasepakketten.controller;

import be.odisee.travelbasepakketten.formdata.PakketFormulier;
import be.odisee.travelbasepakketten.service.PakketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8082", maxAge = 3600, allowCredentials = "true")
public class PakketController {

    @Autowired
    PakketService pakketService;

    @RequestMapping(path = "getemptypakketformulier", method = RequestMethod.GET)
    PakketFormulier getEmptyPakketFormulier()
    {
        return pakketService.getEmptyPakketFormulier();
    }

    @RequestMapping(path = "getallpakketformuliers", method = RequestMethod.GET)
    List<PakketFormulier> getAllPakketFormuliers()
    {
        return pakketService.getAllPakketFormuliers();
    }

    @RequestMapping(path = "getpakketformulier/{id}", method = RequestMethod.GET)
    PakketFormulier getPakketFormulier(@PathVariable Long id)
    //PakketFormulier getPakketFormulier()
    {
        return pakketService.getPakketFormulier(id);
        //return pakketService.getPakketFormulier((long)1);
    }

    @RequestMapping(path = "createpakketformulier", method = RequestMethod.POST)
    PakketFormulier createPakketFormulier(@RequestBody PakketFormulier pakketFormulier)
    {
        return pakketService.createPakketFormulier(pakketFormulier);
    }

    @RequestMapping(path = "updatepakketformulier", method = RequestMethod.PUT)
    PakketFormulier updatePakketFormulier(@RequestBody PakketFormulier pakketFormulier)
    {
        return pakketService.updatePakketFormulier(pakketFormulier);
    }

    @RequestMapping(path = "deletepakketformulier/{id}", method = RequestMethod.DELETE)
    Boolean deletePakketFormulier(@PathVariable Long id)
    {
        return pakketService.deletePakketFormulier(id);
    }
}
