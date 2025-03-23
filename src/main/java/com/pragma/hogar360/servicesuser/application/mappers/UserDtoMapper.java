package com.pragma.hogar360.servicesuser.application.mappers;

import com.pragma.hogar360.servicesuser.application.dto.request.SaveUserRequest;
import com.pragma.hogar360.servicesuser.application.dto.response.UserResponse;
import com.pragma.hogar360.servicesuser.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {
    //String name,String lastName,String idNumber,String phoneNumber,
    // LocalDate birthDate, String email,String password,String role) {
    //}
    @Mapping(source="name",target="name")
    @Mapping(source="lastName",target="lastName")
    @Mapping(source="idNumber",target="idNumber")
    @Mapping(source="phoneNumber",target="phoneNumber")
    @Mapping(source="birthDate",target="birthDate")
    @Mapping(source="email",target="email")
    @Mapping(source="password",target="password")
    @Mapping(source="role",target="role.name")
    UserModel requestToModel(SaveUserRequest request);


    default UserResponse modelToResponse(UserModel model){
        return new UserResponse(
                //Long id,String firstName,String lastName,String email,String password,String role
            model.getId(),
            model.getName(),
            model.getLastName(),
            model.getEmail(),
            model.getRoleName()
        );
    }

}
