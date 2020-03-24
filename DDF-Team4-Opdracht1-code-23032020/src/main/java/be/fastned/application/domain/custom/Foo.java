package be.fastned.application.domain.custom;

import java.util.ArrayList;

/**
 * @TODO Add dependency for .IsEmpty en .IsBlank use in Controle-class
 * m_AfspraakStatus = (StringUtils.isBlank(afspraakStatus) || StringUtils.isEmpty(afspraakStatus)) ? "undefined" : afspraakStatus;
 * @TODO Do Bar in Foo.
 */

/**
 * @author TiboVG
 * @version 1.0
 * @created 15-Mar-2020 14:24:54
 */
public class Foo {
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// -#####- | INSTANTIE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |----------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */
    private String m_FooBar = null;

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */

    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// -#####- | KLASSE VARIABELEN | -#####- //----------------// */
    /* //----------------// -#####- |-------------------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Variabelen //----------------// */

    /* //----------------// SECTIE: Technische-Variabelen //----------------// */
    /**
     * Collectie van actieve & nieuwe Foo. (data-bron voor schermen) */
    public static ArrayListExtended<Foo, Foo> s_ActiveObjecten = new ArrayListExtended<Foo, Foo>();
    /**
     * Collectie van verlopen & afgehandelde Foo. (repository voor rollback) */
    public static ArrayList<Foo> s_ArchivedFooFoo = new ArrayList<Foo>();

    /* //----------------// -#####- |--------------| -#####- //----------------// */
    /* //----------------// -#####- | CONSTRUCTORS | -#####- //----------------// */
    /* //----------------// -#####- |--------------| -#####- //----------------// */
    /**
     * Default Constructor voor deze klasse. */
    public Foo(){
        s_ActiveObjecten.add(this);
    }
    /**
     * Volledige Constructor voor deze klasse. */
    public Foo(String fooBar){
        // super(id, naam, voornaam, geslacht, emailadres, gsm);
        m_FooBar = fooBar;
        this.s_ActiveObjecten.add(this);
    }

    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// -#####- | FUNCTIES | -#####- //----------------// */
    /* //----------------// -#####- |----------| -#####- //----------------// */
    /* //----------------// SECTIE: Domein-Functies //----------------// */
    /* //----------------// SECTIE: Technische-Functies //----------------// */
    /**
     * Deze Domein-functie schrijft een deze instantie over van de Active-ArrayList naar de Archived-ArrayList.
     * Dit via klasse "ArrayListExtended" via naamgeving "s_ArchivedKlasseItemKlasse" of dit zonder "s_". */
    public void archiveer(){
        //this.s_FooObjecten.removeWrapped(Foo.class, Foo.class, true);
    }

    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// -#####- | PROPERTIES | -#####- //----------------// */
    /* //----------------// -#####- |------------| -#####- //----------------// */
    /* //----------------// PROPERTY: Afspraak-Status //----------------// */
    /**
     * Deze domein-attribuut setter vertegenwoordigt de afspraak-status. */
    public void setFooBar(String value){
        this.m_FooBar = value;
    }
    /**
     * Deze domein-attribuut getter vertegenwoordigt de afspraak-status. */
    public String getFooBar(){
        return this.m_FooBar;
    }
}
