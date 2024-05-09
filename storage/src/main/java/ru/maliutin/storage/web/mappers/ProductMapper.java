package ru.maliutin.storage.web.mappers;

import org.mapstruct.Mapper;
import ru.maliutin.storage.domain.Product;
import ru.maliutin.storage.web.dto.ProductDto;

@Mapper(componentModel = "spring")
public interface ProductMapper extends Mappable<Product, ProductDto>{
}
