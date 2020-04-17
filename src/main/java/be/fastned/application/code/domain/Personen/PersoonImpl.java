package be.fastned.application.code.domain.Personen;

import be.fastned.application.code.dao.PersoonHibernateDao;
import be.fastned.application.code.dao.Base.BaseDao;
import be.fastned.application.code.domain.Base.Entiteit;
import be.fastned.application.code.domain.Base.EntiteitBaseImpl;
import be.fastned.application.code.service.AppRunner;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.util.ArrayList;

import static be.fastned.application.code.domain.Personen.PersoonImpl.ENTITY_NAME;
import static be.fastned.application.code.domain.Personen.PersoonImpl.TABLE_NAME;

/**
 * @author TiboVG
 * @version 6.0
 */

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name = ENTITY_NAME)
@Table(name = TABLE_NAME)

// public abstract class PersoonImpl extends EntiteitBaseImpl implements Persoon {
public class PersoonImpl extends EntiteitBaseImpl implements Persoon, Entiteit {
    /* //----------------// -##########-----------------------------##########- //----------------// */
    /* //----------------// -##########- | ! VERDUIDELIJKINGEN ! | -##########- //----------------// */
    /* //----------------// -##########-----------------------------##########- //----------------// */
	/*
		Verwijzing: Vragen omtrent actieve en gearchiveerde collecties -> (HELP02)
		Verwijzing: Vragen omtrent Hoofdletter-genaamde variabelen -> (HELP03)
	*/

    /* //----------------// -##########--------------------------------##########- //----------------// */
    /* //----------------// -##########- &|& INSTANTIE VARIABELEN &|& -##########- //----------------// */
    /* //----------------// -##########--------------------------------##########- //----------------// */

    /* //----------------\\ # ------------------------------- # //----------------\\ */
    /* //----------------\\ # Instantie Domein Variabelen # //----------------\\ */
    /* //----------------\\ # ------------------------------- # //----------------\\ */
    protected String persoonId;
    protected String naam = null;
    protected String voornaam = null;
    protected String geslacht = null;
    protected String gsm = null;
    protected String emailadres = null;
    protected String gebruikersnaam = null;
    protected String wachtwoord = null;

    /* //----------------\\ # ------------------------------- # //----------------\\ */
    /* //----------------\\ # Instantie Technische Variabelen # //----------------\\ */
    /* //----------------\\ # ------------------------------- # //----------------\\ */
    private boolean isActive = true;

    /* //----------------// -##########-----------------------------##########- //----------------// */
    /* //----------------// -##########- &|& KLASSE VARIABELEN &|& -##########- //----------------// */
    /* //----------------// -##########-----------------------------##########- //----------------// */

    static private BaseDao klasseDao;

    /* //----------------// SECTIE: Constanten //----------------// */
    // Configureren @Table en @Entity
    public static final String ENTITY_NAME = "Persoon";
    public static final String TABLE_NAME = "tbl_personen";

    // Lokale constante (id prefix) overkopieëren naar super-variabel
    public static final String ID_PREFIX_PERSOON = PERSOON_ID_PREFIX;

    // Constanten met kolom-namen
    public static final String ID_COL_NAME_PERSOON = ID_PREFIX_PERSOON + "ID";
    public static final String NAAM_COL_NAME = "Naam";
    public static final String GSM_COL_NAME = "GSM";
    public static final String VOORNAAM_COL_NAME = "Voornaam";
    public static final String GESLACHT_COL_NAME = "Geslacht";
    public static final String EMAILADRES_COL_NAME = "Emailadres";
    public static final String WACHTWOORD_COL_NAME = "Wachtwoord";
    public static final String GEBRUIKERSNAAM_COL_NAME = "Gebruikersnaam";

    /* //----------------// SECTIE: Child-Instanties //----------------// */
    /**
     * (ACT-AFSPRAKEN) Collectie van actieve & nieuwe instanties via deze klasse.
     */
    public static ArrayList<Persoon> actieveChildInstanties = new ArrayList<Persoon>();
    /**
     * (ARCH-AFSPRAKEN) Collectie van verlopen & afgehandelde instanties via deze klasse.
     */
    public static ArrayList<Persoon> gearchiveerdeChildInstanties = new ArrayList<Persoon>();

    /* //----------------// SECTIE: Super-Instanties //----------------// */
    /**
     * (ACT-AFSPRAKEN) Collectie van actieve & nieuwe instanties via deze klasse.
     */
    public static ArrayList<Persoon> actieveSuperInstanties = new ArrayList<Persoon>();
    /**
     * (ARCH-AFSPRAKEN) Collectie van verlopen & afgehandelde instanties via deze klasse.
     */
    public static ArrayList<Persoon> gearchiveerdeSuperInstanties = new ArrayList<Persoon>();

    /* //----------------// -#########------------------------#########- //----------------// */
    /* //----------------// -#########- &|& CONSTRUCTORS &|& -#########- //----------------// */
    /* //----------------// -#########------------------------#########- //----------------// */
    /**
     * Default constructor voor deze klasse. (Wel configuratie)
     */
    public PersoonImpl(){
        //setupInitConfig();
    }
    /**
     * Default constructor voor deze klasse. (Optionele Configuratie)
     */
    public PersoonImpl (boolean noConfig){
        if (!noConfig) { setupInitConfig(); }
    }

    public PersoonImpl(String gebruikersnaam, String emailadres, String wachtwoord){
        this.gebruikersnaam = gebruikersnaam;
        this.emailadres = emailadres;
        this.wachtwoord = wachtwoord;
        setupInitConfig();
    }

    public PersoonImpl(String gebruikersnaam, String emailadres, String wachtwoord, String naam, String voornaam, String geslacht, String gsm ){
        setupInitConfig();
        this.gebruikersnaam = gebruikersnaam;
        this.emailadres = emailadres;
        this.wachtwoord = wachtwoord;
        this.naam = naam;
        this.voornaam = voornaam;
        this.geslacht = geslacht;
        this.gsm = gsm;
    }

    /* //----------------// -#########--------------------#########- //----------------// */
    /* //----------------// -#########- &|& FUNCTIES &|& -#########- //----------------// */
    /* //----------------// -#########--------------------#########- //----------------// */

    /* //----------------\\ # ------------------------- # //----------------\\ */
    /* //----------------\\ # Functie Domein # //----------------\\ */
    /* //----------------\\ # ------------------------- # //----------------\\ */

    /* //----------------\\ # ---------------------------- # //----------------\\ */
    /* //----------------\\ # Functie Base # //----------------\\ */
    /* //----------------\\ # ---------------------------- # //----------------\\ */

    /**
     * Deze technische functie zet deze instantie over van de actieve- naar de gearchiveerde arraylist.
     */
    public void archiveer(){
        gearchiveerdeChildInstanties.add(this);
        gearchiveerdeChildInstanties.add(this);
        actieveSuperInstanties.remove(this);
        actieveChildInstanties.remove(this);
    }

    /**
     * Deze technische functie abstraheert alle overige configuraties i.v.m. instantie-constructie.
     */
    private void setupInitConfig(){
        actieveSuperInstanties.add(this);
        actieveChildInstanties.add(this);
        klasseDao = (BaseDao) AppRunner.getAppContext().getBean(PersoonHibernateDao.BEAN_DAO_NAME);
        this.persoonId = (klasseDao.isTableEmpty()) ? (ID_PREFIX_PERSOON + "0") : extrapolateId();
    }

    /**
     * Deze technische functie leidt het id af via het laatste record in de tabel.
     */
    private String extrapolateId(){
        Integer prefixLength = ID_PREFIX_PERSOON.length();
        Integer highest = 0;

        for (Entiteit item : klasseDao.getAllItems() ) {
            item.getId();
            Integer idCount = Integer.parseInt(item.getId().substring(prefixLength));
            if (idCount > highest)
                highest = idCount;
        }
        return ID_PREFIX_PERSOON + (highest+1);
    }

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

    /* //----------------// -#########- |------------| -#########- //----------------// */
    /* //----------------// -#########- | PROPERTIES | -#########- //----------------// */
    /* //----------------// -#########- |------------| -#########- //----------------// */

    /* //----------------\\ # ------------------------- # //----------------\\ */
    /* //----------------\\ # Property Domein # //----------------\\ */
    /* //----------------\\ # ------------------------- # //----------------\\ */

    /* //----------------// PROPERTY: ID //----------------// */
    /**
     * Deze domein-attribuut-getter vertegenwoordigt het id-attribuut van deze instantie.
     */
    //@Transient
    @Id @Column(name = ID_COL_NAME_PERSOON)
    public String getId(){
        return this.persoonId;
    }
    /**
     * Deze domein-attribuut-setter vertegenwoordigt het id-attribuut van deze instantie.
     */
    @Transient
    public void setId(String value){
        this.persoonId = value;
    }

    /* //----------------// PROPERTY: Persoon-Naam //----------------// */
    /**
     * Deze domein-attribuut-getter vertegenwoordigt het Naam-attribuut van deze instantie.
     */
    //@Transient
    @Column(name = NAAM_COL_NAME)
    public String getNaam() { return naam; }
    /**
     * Deze domein-attribuut-setter vertegenwoordigt het Naam-attribuut van deze instantie.
     */
    @Transient
    public void setNaam(String value) { naam = value; }

    /* //----------------// PROPERTY: Persoon-Voornaam //----------------// */
    /**
     * Deze domein-attribuut-getter vertegenwoordigt het Voornaam-attribuut van deze instantie.
     */
    //@Transient
    @Column(name = VOORNAAM_COL_NAME)
    public String getVoorNaam() { return voornaam; }
    /**
     * Deze domein-attribuut-setter vertegenwoordigt het Voornaam-attribuut van deze instantie.
     */
    @Transient
    public void setVoorNaam(String value) { voornaam = value; }

    /* //----------------// PROPERTY: Persoon-Geslacht //----------------// */
    /**
     * Deze domein-attribuut-getter vertegenwoordigt het Geslacht-attribuut van deze instantie.
     */
    //@Transient
    @Column(name = GESLACHT_COL_NAME)
    public String getGeslacht() { return geslacht; }
    /**
     * Deze domein-attribuut-setter vertegenwoordigt het Geslacht-attribuut van deze instantie.
     */
    @Transient
    public void setGeslacht(String value) { geslacht = value; }

    /* //----------------// PROPERTY: Persoon-Gsm //----------------// */
    /**
     * Deze domein-attribuut-getter vertegenwoordigt het Gsm-attribuut van deze instantie.
     */
    //@Transient
    @Column(name = GSM_COL_NAME)
    public String getGsm() { return gsm; }
    /**
     * Deze domein-attribuut-setter vertegenwoordigt het Gsm-attribuut van deze instantie.
     */
    @Transient
    public void setGsm(String value) { gsm = value; }

    /* //----------------// PROPERTY: Persoon-Gebruikersnaam //----------------// */
    /**
     * Deze domein-attribuut-getter vertegenwoordigt het Gebruikersnaam-attribuut van deze instantie.
     */
    //@Transient
    @Column(name = GEBRUIKERSNAAM_COL_NAME)
    public String getGebruikersnaam() { return gebruikersnaam; }
    /**
     * Deze domein-attribuut-setter vertegenwoordigt het Gebruikersnaam-attribuut van deze instantie.
     */
    @Transient
    public void setGebruikersnaam(String value) { gebruikersnaam = value; }

    /* //----------------// PROPERTY: Persoon-Emailadres //----------------// */
    /**
     * Deze domein-attribuut-getter vertegenwoordigt het Emailadres-attribuut van deze instantie.
     */
    //@Transient
    @Column(name = EMAILADRES_COL_NAME)
    public String getEmailadres() { return emailadres; }
    /**
     * Deze domein-attribuut-setter vertegenwoordigt het Emailadres-attribuut van deze instantie.
     */
    @Transient
    public void setEmailadres(String value) { emailadres = value; }

    /* //----------------// PROPERTY: Persoon-Wachtwoord //----------------// */
    /**
     * Deze domein-attribuut-getter vertegenwoordigt het Wachtwoord-attribuut van deze instantie.
     */
    //@Transient
    @Column(name = WACHTWOORD_COL_NAME)
    public String getWachtwoord() { return wachtwoord; }
    /**
     * Deze domein-attribuut-setter vertegenwoordigt het Wachtwoord-attribuut van deze instantie.
     */
    @Transient
    public void setWachtwoord(String value) { wachtwoord = value; }

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
