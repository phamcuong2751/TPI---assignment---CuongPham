package com.java.tpi.cuongpham.constaints;

public enum ApiLabel {
    GET_SUCCESS("Get data successfully"),
    INSERT_SUCCESS("Add success"),
    UPDATE_SUCCESS("Update success"),
    DELETE_SUCCESS("Delete success"),
    NOT_FOUND("Not found data"),
    INTERNAL_SERVER_ERROR("Internal Server Error"),
    BAD_REQUEST("Bad request");

    public final String text;
    public String getMessage() {
        return text;
    }
    ApiLabel(String text) {
        this.text = text;
    }

    public static String valueOfLabel(String key) {
        for (ApiLabel label : ApiLabel.values()) {
            if (label.name().equals(key)) {
                return label.getMessage();
            }
        }
        return "Key not found"; // or throw an exception, handle it as needed
    }
}
