package be.fastned.application.formdata;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@RequiredArgsConstructor
@NoArgsConstructor(force=true)
public class AfspraakData {

    private long id = 0;

    private long installateurId;

    private long laadpaalId;

    private long contractId;

    private long bezoekId;

    @NotBlank(message= "status must be specified!")
    @Size(min=2, max=240)
    private String status;

    private final long[] contractIds, laadpaalIds, installateurIds;
}
