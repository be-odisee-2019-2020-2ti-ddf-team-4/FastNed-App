package be.fastned.application.service;

import be.fastned.application.domain.Persoon;

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:52
 */
public class FastNedAppImpl implements FastNedAppService {
    public Persoon m_HuidigePersoon;
    public Object m_GetoondObject;
    public Object[] m_RelevanteInMemoryObjecten;

    private static FastNedAppImpl uniqueInstance;

    /**
     * Default private Constructor voor deze klasse. */
    private FastNedAppImpl() {}


    public static FastNedAppImpl getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new FastNedAppImpl();
        }
        return uniqueInstance;
    }

    /**
     * Deze domein-attribuut setter vertegenwoordigt de HuidigePersoon. */
    public void setHuidigePersoon(Persoon value){
        this.m_HuidigePersoon = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de HuidigePersoon. */
    public Persoon getHuidigePersoon(){
        return this.m_HuidigePersoon;
    }

    /* //----------------// PROPERTY: GetoondObject //----------------// */
    /**
     * Deze domein-attribuut setter vertegenwoordigt de GetoondObject. */
    public void setGetoondObject(Object value){
        this.m_GetoondObject = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de GetoondObject. */
    public Object getGetoondObject(){
        return this.m_GetoondObject;
    }

    /* //----------------// PROPERTY: RelevanteObjecten //----------------// */
    /**
     * Deze domein-attribuut setter vertegenwoordigt de RelevanteObjecten. */
    public void setRelevanteObjecten(Object[] value){
        this.m_RelevanteInMemoryObjecten = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de RelevanteObjecten. */
    public Object[] getRelevanteObjecten(){
        return this.m_RelevanteInMemoryObjecten;
    }
}