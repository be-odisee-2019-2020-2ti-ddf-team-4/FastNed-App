package service;

import be.fastned.FastnedLocatieAanmeldingenSecureApplication;

import be.fastned.domain.LocatieAanmelding;
import be.fastned.service.LocatieAanmeldingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocatieAanmeldingServiceImplTest extends FastnedLocatieAanmeldingenSecureApplication {

    LocatieAanmeldingService locatieAanmeldingService;
    LocatieAanmelding locatieAanmelding;


    @Test
    void locatieAanmelding() {

        locatieAanmelding = locatieAanmeldingService.locatieAanmelding(4);
        assertEquals("",locatieAanmelding);

    }
}