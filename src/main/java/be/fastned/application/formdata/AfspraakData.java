package be.fastned.application.formdata;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
@NoArgsConstructor(force=true)
@RequiredArgsConstructor
@Data
public class AfspraakData {

    private long id = 0;

    // TODO: capitalise first letter
    @NotBlank(message= "contract must be specified!")
    private long contractId;

    @NotBlank(message= "laadpaal must be specified!")
    private long laadpaalId;

    @NotBlank(message= "installateur must be specified!")
    private long installateurId;

    @NotBlank(message= "bezoek must be specified!")
    private long bezoekId;

    @NotBlank(message= "status must be specified!")
    private  String status;
}
