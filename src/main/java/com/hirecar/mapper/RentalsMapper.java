package com.hirecar.mapper;

import com.hirecar.dto.persist.RentalPersist;
import com.hirecar.model.Rentals;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RentalsMapper {

    RentalsMapper MAPPER = Mappers.getMapper(RentalsMapper.class);

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "car", target = "car"),
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "start_date", target = "start_date"),
            @Mapping(source = "end_date", target = "end_date")
    })
    Rentals persistToEntity(RentalPersist persist);
}
