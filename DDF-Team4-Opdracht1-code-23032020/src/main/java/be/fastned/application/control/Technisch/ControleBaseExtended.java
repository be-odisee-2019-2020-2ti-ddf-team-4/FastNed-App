package be.fastned.application.control.Technisch;

import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;

public class ControleBaseExtended extends ControleBase {
    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    protected Persoon m_ActieveGebruiker = null;

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
