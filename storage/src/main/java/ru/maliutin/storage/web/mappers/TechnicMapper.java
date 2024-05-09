package ru.maliutin.storage.web.mappers;

import org.mapstruct.Mapper;
import ru.maliutin.storage.domain.Technic;
import ru.maliutin.storage.web.dto.TechnicDto;

@Mapper(componentModel = "spring")
public interface TechnicMapper extends Mappable<Technic, TechnicDto>{
}
