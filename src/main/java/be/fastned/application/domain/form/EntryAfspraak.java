package be.fastned.application.domain.form;

import be.fastned.application.domain.OtherImpl.Contract;
import be.fastned.application.domain.OtherImpl.Laadpaal;
import be.fastned.application.domain.Personen.Installateur;
import be.fastned.application.domain.Technisch.Bezoek;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ENTRIES")
@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)
public class EntryAfspraak {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    private Contract contract;

    @ManyToOne
    private Laadpaal laadpaal;

    @ManyToOne
    private Installateur installateur;

    // TODO: verbind/normaliseer tabellen installatie/reparatie -> attr "Type bezoek", titel -> "Bezoek", ...
    @ManyToOne
    private Bezoek bezoek;

    private String status;


    public Contract getContract(){ return this.contract;}
    public void setContract(Contract value){ this.contract = value;}

    public Laadpaal getLaadpaal(){ return this.laadpaal;}
    public void setLaadpaal(Laadpaal value){ this.laadpaal = value;}

    public Installateur getInstallateur(){ return this.installateur;}
    public void setInstallateur(Installateur value){ this.installateur = value;}

    public Bezoek getBezoek(){ return this.bezoek;}
    public void setBezoek(Bezoek value){ this.bezoek = value;}

    public String getStatus(){ return this.status;}
    public void setStatus(String value){ this.status = value;}
}
