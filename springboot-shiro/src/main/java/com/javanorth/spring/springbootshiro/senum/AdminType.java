package com.javanorth.spring.springbootshiro.senum;

public enum AdminType {
    // ROOT > ADMIN >

    /**
     * root administrator
     * permission: all, include delete other admin
     */
    ROOT(1, "Root"),

    /**
     * application administrator
     * permission: most, can't delete root administrator, can delete other user
     */
    ADMIN(2, "Admin"),

    /**
     * ordinary user
     * permission: some
     */
    GENERAL_USER(3, "General_User"),

    /**
     * anonymous
     * permission: anonymous api
     */
    ANONYMOUS(4, "Anonymous"),

    ;

    private final int id;
    private final String type;

    AdminType(int id, String type) {
        this.id = id;
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
