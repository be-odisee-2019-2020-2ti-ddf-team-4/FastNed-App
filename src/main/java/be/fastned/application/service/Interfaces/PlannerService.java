package be.fastned.application.service.Interfaces;

import be.fastned.application.domain.form.EntryAfspraak;
import be.fastned.application.formdata.EntryDataAfspraak;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public interface PlannerService {
    // public Map<String, List<Project>> getCategoriesWithProjects();

    public Object getObjectives();

    public EntryDataAfspraak prepareNewEntryData();

    public String processEntry(@Valid EntryDataAfspraak entryData);

    public List<EntryAfspraak> getEntriesFromDate(LocalDate theDate);

    public Duration getTotalDuration(List<EntryAfspraak> entries);

    public EntryDataAfspraak prepareEntryDataToEdit(String id);

    public void deleteEntry(String id);
}
