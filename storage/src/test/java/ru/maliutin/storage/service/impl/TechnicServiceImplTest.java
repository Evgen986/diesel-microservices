package ru.maliutin.storage.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.maliutin.storage.domain.Technic;
import ru.maliutin.storage.repository.TechnicRepository;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TechnicServiceImplTest {

    @Mock
    private TechnicRepository technicRepository;
    @InjectMocks
    private TechnicServiceImpl technicService;

    @Test
    public void checkNewTechnicExpectSaveNewTechnic(){
        Technic technicInDatabase = new Technic();
        String availableTitle = "inBase";
        Long technicInBaseId = 1L;
        technicInDatabase.setTitle(availableTitle);
        technicInDatabase.setTechnicId(technicInBaseId);

        Technic technicNotInDatabase = new Technic();
        String notAvailableTitle = "notinBase";
        Long newTechnicId = 2L;
        technicNotInDatabase.setTitle(notAvailableTitle);
        technicNotInDatabase.setTechnicId(newTechnicId);

        Set<Technic> technics = new LinkedHashSet<>();
        technics.add(technicInDatabase);
        technics.add(technicNotInDatabase);

        when(technicRepository.findTechnicByTitle(anyString()))
                .thenReturn(Optional.of(technicInDatabase))
                .thenReturn(Optional.empty());
        when(technicRepository.save(technicNotInDatabase))
                .thenReturn(technicNotInDatabase);

        technicService.checkNewTechnic(technics);

        verify(technicRepository, times(2))
                .findTechnicByTitle(anyString());
        verify(technicRepository).save(technicNotInDatabase);
    }
}
