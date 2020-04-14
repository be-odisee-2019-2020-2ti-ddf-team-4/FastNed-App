package be.fastned.application.control.Base;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.cfg.NotYetImplementedException;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class ControleBase {

    /* //----------------// -#####----------------#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####----------------#####- //----------------// */

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /**
     * Deze technische functie ondersteunt deze klasse door foutmelder en foutmelding te verwerken in een foutmelding op het scherm.
     */
    protected void tf_ToonFoutmelding(String foutmelder, String foutmelding, boolean canTryAgain){
        // Encapsuleren van deze operatie als bug-management.
        try{
            if (canTryAgain){
                // Informeer de gebruiker over de fout(en) en stuur hem terug naar een nieuwe input-kans. (= een scherm)
                System.out.println(String.format("try-again foutboodschap vanuit \"%s\" getoond met inhoud: \"%s\"", foutmelder, foutmelding));
                throw new NotYetImplementedException();
            }
            else{
                // Informeer de gebruiker over de fout(en) en stuur hem permanent/tijdelijk terug naar een scherm.
                System.out.println(String.format("not-try-again foutboodschap vanuit \"%s\" getoond met inhoud: \"%s\"", foutmelder, foutmelding));
                throw new NotYetImplementedException();
            }
        }
        catch(Exception ex){
            System.out.println(String.format("tonen van foutboodschap misgelopen met error \"%s\"", ex.getMessage()));
            throw new NotYetImplementedException();
        }
    }

    /**
     * Deze technische functie ondersteunt deze klasse door een set argumenten te checken op leeg/null zijn.
     * @return Een set met uitgefilterde problemen hun index die null is waneer er geen problemen zijn.
     */
    protected ArrayList<Integer> tf_checkAgainstNullOrEmpty(Object[] arguments, boolean allStrings){
        // Setup technische helper-variabelen.
        ArrayList<Integer> indexes = null;

        // Afhandeling: er zijn geen problemen --> Early exit. (enkel mogelijk bij alleen strings of alleen objecten..)
        if (allStrings && Arrays.stream(arguments).filter(arg -> arg == null || ((String)arg).isEmpty() == true).count() == 0) return null;
        else if (!allStrings && Arrays.stream(arguments).filter(arg -> arg == null).count() == 0) return null;

        // Afhandeling: er zijn problemen opgetreden --> Problemen uitfilteren & retourneren
        for (int i = 0; i < arguments.length; i++) {
            if (arguments[i] instanceof String && arguments[i] == null || ((String)arguments[i]).isEmpty() == true){
                indexes.add((Integer) ArrayUtils.indexOf(arguments, arguments[i]));
            }
            else if (arguments[i] instanceof String && arguments[i] == null){
                indexes.add((Integer) ArrayUtils.indexOf(arguments, arguments[i]));
            }
        }
        return indexes;
    }
}
