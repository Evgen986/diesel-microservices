package ru.maliutin.storage.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maliutin.storage.domain.Technic;
import ru.maliutin.storage.repository.TechnicRepository;
import ru.maliutin.storage.service.TechnicService;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TechnicServiceImpl implements TechnicService {

    private final TechnicRepository technicRepository;
    @Override
    @Transactional
    public Set<Technic> checkNewTechnic(Set<Technic> technics) {
        for (Technic technic : technics){
            Optional<Technic> technicInBase = technicRepository.findTechnicByTitle(technic.getTitle());
            if (technicInBase.isEmpty()){
                technic.setTechnicId(technicRepository.save(technic).getTechnicId());
            }else{
                technic.setTechnicId(technicInBase.get().getTechnicId());
            }
        }
        return technics;
    }
}
