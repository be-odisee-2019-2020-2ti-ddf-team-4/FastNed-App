package be.fastned.application.control.Base;

import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;
import be.fastned.application.service.AppRunner;

public class ControleBaseExtended extends ControleBase {
    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    protected Persoon m_ActieveGebruiker = null;

    protected final Persoon BEAN_ACTIEVE_GEBRUIKER = AppRunner.getActieveGebruiker();
    public static final String BEAN_CONTROLEPLANNER = "ControlePlanner";
    public static final String BEAN_CONTROLEINSTALLATEUR = "ControleInstallateur";
    public static final String BEAN_CONTROLELAADKLANT = "ControleLaadklant";
    public static final String BEAN_CONTROLELOCATIEHOUDER = "ControleLocatiehouder";

    /* //----------------// -#####----------------#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####----------------#####- //----------------// */

    /* //----------------// SECTIE: Technische-Functies //----------------// */

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */

    /* //----------------// SECTIE: Domein-Properties //----------------// */
    /**
     * Deze domein-attribuut setter vertegenwoordigt de actieveGebruiker-instantie in deze controle-instantie.
     */
    public void setActieveGebruiker(Persoon value){
        this.m_ActieveGebruiker = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de actieveGebruiker-instantie in deze controle-instantie.
     */
    public Persoon getActieveGebruiker(){
        return this.m_ActieveGebruiker;
    }
}