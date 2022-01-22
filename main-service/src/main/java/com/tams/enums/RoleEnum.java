package com.tams.enums;

import lombok.AllArgsConstructor;

/**
 * @author swiChen
 * @date 2022/1/12
 **/

@AllArgsConstructor
public enum RoleEnum {


    ROOT("root"),

    ADMIN("admin"),

    TEACHER("teacher"),

    STUDENT("student");

    public static RoleEnum StringParseRole(String role){

        switch (role){
            case "student":
                return STUDENT;
            case "root":
                return ROOT;
            case "admin":
                return ADMIN;
            default:
                return TEACHER;
        }
    }

    private String role;

    public String getRole() {
        return role;
    }
}
