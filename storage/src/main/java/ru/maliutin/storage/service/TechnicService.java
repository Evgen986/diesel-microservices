package ru.maliutin.storage.service;

import ru.maliutin.storage.domain.Technic;

import java.util.Set;

public interface TechnicService {

    Set<Technic> checkNewTechnic(Set<Technic> technics);

}
