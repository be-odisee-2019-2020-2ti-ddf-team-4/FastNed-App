package be.fastned.application.formdata;

import be.fastned.application.domain.OtherImpl.Contract;
import be.fastned.application.domain.OtherImpl.Laadpaal;
import be.fastned.application.domain.Personen.Installateur;
import be.fastned.application.domain.Technisch.Bezoek;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class EntryDataAfspraak {

    private final String attrNameContract = "contract";
    private final String attrNameLaadpaal = "laadpaal";
    private final String attrNameInstallateur = "installateur";
    private final String attrNameBezoek = "bezoek";
    private final String attrNameStatus = "status";

    // id is needed for updating
    private String id=null;

    // TODO: capitalise first letter
    @NotBlank(message=attrNameContract + " must be specified!")
    private String entryContractId;

    @NotBlank(message=attrNameLaadpaal + " must be specified!")
    private String entryLaadpaalId;

    @NotBlank(message=attrNameInstallateur + " must be specified!")
    private String entryInstallateurId;

    @NotBlank(message=attrNameBezoek + " must be specified!")
    private String entryBezoekId;

    @NotBlank(message=attrNameStatus + " must be specified!")
    private String entryStatus;

    public String getContractId(){ return this.entryContractId;}
    public void setContractId(String value){ this.entryContractId = value;}

    public String getLaadpaalId(){ return this.entryLaadpaalId;}
    public void setLaadpaalId(String value){ this.entryLaadpaalId = value;}

    public String getInstallateurId(){ return this.entryInstallateurId;}
    public void setInstallateurId(String value){ this.entryInstallateurId = value;}

    public String getBezoekId(){ return this.entryBezoekId;}
    public void setBezoekId(String value){ this.entryBezoekId = value;}

    public String getStatus(){ return this.entryStatus;}
    public void setStatus(String value){ this.entryStatus = value;}
}
