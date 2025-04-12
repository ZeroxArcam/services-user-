package com.pragma.hogar360.servicesuser.infrastructure.config.mappers;
import com.pragma.hogar360.servicesuser.domain.model.UserModel;
import com.pragma.hogar360.servicesuser.infrastructure.config.entities.RoleEntity;
import com.pragma.hogar360.servicesuser.infrastructure.config.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserEntityMapper {

    //@Mapping(target = "id", ignore = true)
    @Mapping(source = "id" , target = "id")
    @Mapping(source = "role", target = "role")
    UserModel toModel(UserEntity userEntity);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.lastName", target = "lastName")
    @Mapping(source = "user.idNumber", target = "idNumber")
    @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    @Mapping(source = "user.birthDate", target = "birthDate")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.password", target = "password")
    @Mapping(source = "user.role", target = "role")
    UserEntity toEntity(UserModel user, RoleEntity role);

//    default UserEntity toEntity(UserEntity user, RoleEntity role) {
//        return new UserEntity(null,name,lastName,);
//    }

}
