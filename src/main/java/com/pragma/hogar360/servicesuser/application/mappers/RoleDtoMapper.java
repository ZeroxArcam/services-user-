package com.pragma.hogar360.servicesuser.application.mappers;

import com.pragma.hogar360.servicesuser.application.dto.request.SaveRoleRequest;
import com.pragma.hogar360.servicesuser.application.dto.request.SaveUserRequest;
import com.pragma.hogar360.servicesuser.application.dto.response.RoleResponse;
import com.pragma.hogar360.servicesuser.domain.model.RoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleDtoMapper {

    RoleModel requesToModel(SaveRoleRequest request);
    RoleResponse modelToResponse(RoleModel model);
}
