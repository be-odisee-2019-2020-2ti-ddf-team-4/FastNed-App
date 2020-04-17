package be.fastned.application.code.boundary.Base;

import be.fastned.application.code.domain.Personen.Persoon;

public abstract class SchermBase {
    /* //----------------// -#####----------------------------#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####----------------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    protected Persoon m_SchermViewer = null;

    /* //----------------// -#####------------------#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####------------------#####- //----------------// */

    /* //----------------// SECTIE: Domein-Properties //----------------// */
    /**
     * Deze domein-attribuut setter vertegenwoordigt de persoon-instantie (schermgebruiker) in deze boundary-instantie.
     */
    public void setSchermViewer(Persoon value){
        this.m_SchermViewer = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de persoon-instantie (schermgebruiker) in deze boundary-instantie.
     */
    public Persoon getSchermViewer(){
        return this.m_SchermViewer;
    }
}
