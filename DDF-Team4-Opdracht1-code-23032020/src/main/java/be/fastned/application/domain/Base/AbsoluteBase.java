package be.fastned.application.domain.Base;

import be.fastned.application.dao.Base.BaseDao;
import java.io.Serializable;

public abstract class AbsoluteBase implements Serializable {

    static protected final String AFSPRAAK_ID_PREFIX = "AFSPRK_";
    static protected final String CONTRACT_ID_PREFIX = "CNTRCT_";
    static protected final String INSTALLATIE_ID_PREFIX = "INSTLTIE_";
    static protected final String LAADPAAL_ID_PREFIX = "LDPL_";
    static protected final String LAADSESSIE_ID_PREFIX = "LDSSIE_";
    static protected final String LOCATIETOESTEMMING_ID_PREFIX = "LCTSTMNG_";
    static protected final String OPLOSSING_ID_PREFIX = "OPLSNG_";
    static protected final String PROBLEEM_ID_PREFIX = "PBLM_";
    static protected final String REPARATIE_ID_PREFIX = "RPRTIE_";
    static protected final String DOCUMENTATIEDOC_ID_PREFIX = "DCMNTTIEDC_";

    static protected final String PERSONEN_ID_PREFIX = "PRSN_";
    static protected final String INSTALLATEUR_ID_PREFIX = "INSTLR_";
    static protected final String LAADKLANT_ID_PREFIX = "LDKLNT_";
    static protected final String LOCATIEHOUDER_ID_PREFIX = "LCTHDR_";
    static protected final String PLANNER_ID_PREFIX = "PLNNR_";

    static protected BaseDao klasseDao;

    /**
     * Deze technische functie leidt het id voor deze instantie af v.h. laatste record in de DB.
     */
    protected String baseExtrapolateId(String PREFIX, BaseDao objectDao){

        if (!objectDao.isTableEmpty()){
            System.out.println("TEST");
            String lastDBItemId = objectDao.getLastItemId();
            Integer idCount = Integer.parseInt(lastDBItemId.substring(PREFIX.length()));
            String nieuwId = PREFIX+(idCount+1);
            return nieuwId;
        }
        else {
            System.out.println("TEST2");
            return String.format("%s0", PREFIX);
        }
    }
}
