package be.fastned.application.formdata;

import lombok.Data;
import javax.validation.constraints.NotBlank;

@Data
public class AfspraakData {

    private long id = 0;

    // TODO: capitalise first letter
    @NotBlank(message= "contract must be specified!")
    private String contractId;

    @NotBlank(message= "laadpaal must be specified!")
    private String laadpaalId;

    @NotBlank(message= "installateur must be specified!")
    private String installateurId;

    @NotBlank(message= "bezoek must be specified!")
    private String bezoekId;

    @NotBlank(message= "status must be specified!")
    private String status;
}
