package be.fastned.application.domain.PersoonAbstracties;



import be.fastned.application.dao.Interfaces.InstallateurDao;
import be.fastned.application.domain.AbsoluteBase;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.Persoon;
import be.fastned.application.domain.PersoonAbstracties.Interfaces.PersoonExtension;
import be.fastned.application.service.AppRunner;

import javax.persistence.*;
import java.util.ArrayList;

//@Entity(name = "Persoon")
//@Table(name="tbl_personen")
public abstract class PersoonImpl extends AbsoluteBase implements Persoon {
    /* //----------------// -#####-----------------------------#####- //----------------// */
    /* //----------------// -#####- | ! VERDUIDELIJKINGEN ! | -#####- //----------------// */
    /* //----------------// -#####-----------------------------#####- //----------------// */

	/*
		De collecties van type "ArrayList"? -> (HELP01)
		Wat is een "hf_..."-functie? -> (HELP03)
	*/

    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Properties //----------------// */
    @Transient
    protected String m_Id;
    @Transient
    protected String m_Naam = null;
    @Transient
    protected String m_Voornaam = null;
    @Transient
    protected String m_Geslacht = null;
    @Transient
    protected String m_Gsm = null;
    @Transient
    protected String m_Emailadres = null;
    @Transient
    protected String m_Gebruikersnaam = null;
    @Transient
    protected String m_Wachtwoord = null;

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */
    @Transient
    private boolean isActive = true;

    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    private static final String PREFIX = "PN_";


    /**
     * Collectie van alle (als-instantie-van-deze-klasse) aangemaakte extenties van PERSONEN. (Installateurs etc)
     * NOTE: bevat enkel referenctie naar child-versie van dit, zo handelt .archiveer() in de super-klasse via collecties uit de child-klasse.
     */
    @Transient
    protected static ArrayList<PersoonExtension> actieveChildInstanties = null;
    /**
     * Collectie van alle (als-instantie-van-deze-klasse) gearchiveerde extenties van PERSONEN. (Installateurs etc)
     * NOTE: bevat enkel referenctie naar child-versie van dit, zo handelt .archiveer() in de super-klasse via collecties uit de child-klasse.
     */
    @Transient
    protected static ArrayList<PersoonExtension> gearchiveerdeChildInstanties = null;

    /**
     * Collectie van alle (als-instantie-van-deze-klasse) aangemaakte PERSONEN.
     */
    @Transient
    protected static ArrayList<Persoon> actieveSuperInstanties = new ArrayList<Persoon>();
    /**
     * Collectie van alle (als-instantie-van-deze-klasse) aangemaakte PERSONEN.
     */
    @Transient
    protected static ArrayList<Persoon> gearchiveerdeSuperInstanties = new ArrayList<Persoon>();

    /* //----------------// -#####- |--------------| -#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####- |--------------| -#####- //----------------// */
    public PersoonImpl(){}
    /**
     * Default Constructor voor deze klasse. */
    public PersoonImpl (boolean addToActieveColl){
        if (addToActieveColl) {
            actieveSuperInstanties.add(this);
            extrapolateAndSetId();
        }
    }

    public PersoonImpl(String emailadres, String gebruikersnaam, String wachtwoord ){
        m_Emailadres = emailadres;
        m_Gebruikersnaam = gebruikersnaam;
        m_Wachtwoord = wachtwoord;
        actieveSuperInstanties.add(this);
        extrapolateAndSetId();
    }

    public PersoonImpl(String emailadres, String gebruikersnaam, String wachtwoord, String naam, String voornaam, String geslacht, String gsm ){
        m_Emailadres = emailadres;
        m_Gebruikersnaam = gebruikersnaam;
        m_Wachtwoord = wachtwoord;
        m_Naam = naam;
        m_Voornaam = voornaam;
        m_Geslacht = geslacht;
        m_Gsm = gsm;
        actieveSuperInstanties.add(this);
        extrapolateAndSetId();
    }

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */

    /* //----------------// SECTIE: Technische-Functies //----------------// */
    /**
     * Deze functie archiveert bv. een Installateur door zowel child- als super-klasse (Persoon) apart
     * uit de respectievelijke actieve collecties te verwijderen en toe te voegen aan de gearchiveerde collecties.
     */
    public void archiveer(){
        gearchiveerdeSuperInstanties.add(this);
        gearchiveerdeChildInstanties.add((PersoonExtension) this);
        actieveSuperInstanties.remove(this);
        actieveChildInstanties.remove(this);
    }

    /**
     * Deze technische functie leidt het id van deze installateur af van het laatste record in de DB.
     */
    //TODO What if no records present
    private void extrapolateAndSetId(){
        /*System.out.println(String.format("Test -> %s", AppRunner.getAppContext().getBean("installateurDao")));
        String lastDBItemId = ((InstallateurDao) AppRunner.getAppContext().getBean("installateurDao")).getLastItemId();
        Integer idCount = Integer.parseInt(lastDBItemId.substring(PREFIX.length()));
        m_Id = PREFIX+idCount++;*/

    }

    /* //----------------// SECTIE: Technische-Helper-Functies //----------------// */
    /**
     * Deze technische functie leidt zogoed als elke operatie in met een check tegen boolean "isActive".
     * Zo voorkomt deze applicatie het gebruik van triviale operaties van gearchiveerde objecten.
     */
    public Object hf_CheckIsActive(Object arg){
        try{
            if (isActive){
                return arg;
            }
            else throw new Exception("Je kan geen object gebruiken dat gearchiveerd is!");
        }
        catch(Exception ex){
            System.out.println(String.format("\n Binnen methode \"archiveer\" van Persoon ging iets fout:\n%s", ex.getMessage()));
            return null;
        }
    }

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// PROPERTY: Persoon-Id //----------------// */
    @Id
    public String getSuperId() { return m_Id; }
    @Transient
    public void setSuperId(String id) { m_Id = id; }

    /* //----------------// PROPERTY: Persoon-Naam //----------------// */
    @Transient
    public void setNaam(String naam) { m_Naam = naam; }

    @Column(name = "naam")
    public String getNaam() { return m_Naam; }

    /* //----------------// PROPERTY: Persoon-Voornaam //----------------// */
    @Transient
    public void setVoorNaam(String voorNaam) { m_Voornaam = voorNaam; }

    @Column(columnDefinition = "Voornaam")
    public String getVoorNaam() { return m_Voornaam; }

    /* //----------------// PROPERTY: Persoon-Geslacht //----------------// */
    @Transient
    public void setGeslacht(String geslacht) { m_Geslacht = geslacht; }

    //@Column(name = "geslacht", nullable = true, length = 50)
    @Transient
    public String getGeslacht() { return m_Geslacht; }

    /* //----------------// PROPERTY: Persoon-Gsm //----------------// */
    @Transient
    public void setGsm(String gsm) { m_Gsm = gsm; }

    //@Column(columnDefinition = "Gsm")
    @Transient
    public String getGsm() { return m_Gsm; }

    /* //----------------// PROPERTY: Persoon-Emailadres //----------------// */
    @Transient
    public void setEmailadres(String emailadres) { m_Emailadres = emailadres; }

    //@Column(columnDefinition = "emailadres")
    @Transient
    public String getEmailadres() { return m_Emailadres; }

    /* //----------------// PROPERTY: Persoon-Gebruikersnaam //----------------// */
    @Transient
    public void setGebruikersnaam(String gebruikersnaam) { m_Gebruikersnaam = gebruikersnaam; }

    //@Column(columnDefinition = "Gebruikersnaam")
    @Transient
    public String getGebruikersnaam() { return m_Gebruikersnaam; }

    /* //----------------// PROPERTY: Persoon-Wachtwoord //----------------// */
    @Transient
    public void setWachtwoord(String wachtwoord) { m_Wachtwoord = wachtwoord; }

    @Column(columnDefinition = "Wachtwoord")
    public String getWachtwoord() { return m_Wachtwoord; }

    /* //----------------\\ <||> --------------------- <||> //----------------\\ */
    /* //----------------\\ <||> TECHNISCHE Properties <||> //----------------\\ */
    /* //----------------\\ <||> --------------------- <||> //----------------\\ */

    /* //----------------// PROPERTY: Actieve PERSONEN //----------------// */
    /**
     * (ACTIEF) Deze domein-attribuut-setter vertegenwoordigt de coll. v.d. actieve PERSONEN.
     */
    @Transient
    public static ArrayList<Persoon> getActieveSuperInstanties() { return actieveSuperInstanties; }

    /* //----------------// PROPERTY: Archief PERSONEN //----------------// */
    /**
     * (ARCHIVED) Deze domein-attribuut-setter vertegenwoordigt de coll. v.d. gearchiveerde PERSONEN.
     */
    @Transient
    public static ArrayList<Persoon> getGearchiveerdeSuperInstanties() { return gearchiveerdeSuperInstanties; }
}
