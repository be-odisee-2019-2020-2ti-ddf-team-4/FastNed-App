package be.fastned.application.formdata;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)
public class AfspraakData {

    private final long id = 0;

    @NotBlank(message= "contract must be specified!")
    private long contractId;

    @NotBlank(message= "laadpaal must be specified!")
    private long laadpaalId;

    @NotBlank(message= "installateur must be specified!")
    private long installateurId;

    @NotBlank(message= "bezoek must be specified!")
    private long bezoekId;

    @NotBlank(message= "status must be specified!")
    private String status;

    private final long[] contractIds, laadpaalIds, installateurIds;
}
