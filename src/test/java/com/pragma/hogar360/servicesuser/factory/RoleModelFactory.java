package com.pragma.hogar360.servicesuser.factory;
import com.pragma.hogar360.servicesuser.domain.model.RoleModel;

public class RoleModelFactory {

    public static RoleModel createRoleModel(Long id, String name, String description) {
        return new RoleModel(id, name, description);
    }

    public static RoleModel createDefaultRoleModel() {
        return createRoleModel(1L, "Default Role", "This is a default role description.");
    }

    public static RoleModel createRoleModelWithName(String name) {
        return createRoleModel(1L, name, "Default description");
    }

    public static RoleModel createRoleModelWithDescription(String description) {
        return createRoleModel(1L, "Default name", description);
    }

    public static RoleModel createRoleModelWithId(Long id) {
        return createRoleModel(id, "Default name", "Default description");
    }
}