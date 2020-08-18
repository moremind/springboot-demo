package com.javanorth.spring.springbootshiro.senum;

public enum PermissionType {

    // user resource
    USER_CREATE(1, "user:create"),
    USER_DELETE(2, "user:delete"),
    USER_UPDATE(3, "user:update"),

    // media resource
    IMAGE_CREATE(4, "image:create"),
    IMAGE_DELETE(5, "image:delete")


    ;
    private final int id;
    private final String name;

    PermissionType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
