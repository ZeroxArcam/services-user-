package com.pragma.hogar360.servicesuser.infrastructure.config.mappers;
import com.pragma.hogar360.servicesuser.domain.model.RoleModel;
import com.pragma.hogar360.servicesuser.infrastructure.config.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleEntityMapper {

    @Mapping(target = "id", ignore = true)
    RoleModel toModel(RoleEntity roleEntity);

    @Mapping(target = "id", ignore = true)
    RoleEntity toEntity(RoleModel roleModel);

}
