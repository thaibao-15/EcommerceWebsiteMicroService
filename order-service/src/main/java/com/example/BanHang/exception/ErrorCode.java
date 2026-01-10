package com.example.BanHang.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(1001, "Uncategorized error", HttpStatus.BAD_REQUEST),
    USER_EXISTED(1002, "User existed", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1003, "Username must be at least {min} characters", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(1004, "Password must be at least {min} characters", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1005, "User not existed", HttpStatus.NOT_FOUND),
    UNAUTHENTICATED(1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1007, "You do not have permission", HttpStatus.FORBIDDEN),
    INVALID_DOB(1008, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    CATEGORY_EXIST(1008,"Category has been existed",HttpStatus.BAD_REQUEST),
    CATEGORY_NOT_EXIST(1009,"Category does not exist yet",HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_EXIST(1009,"Product does not exist yet",HttpStatus.BAD_REQUEST),
    PERMISSION_NOT_EXIST(1010,"Permission does not exist yet",HttpStatus.BAD_REQUEST),
    PERMISSION_EXIST(1011,"Permission has been existed ",HttpStatus.BAD_REQUEST),
    ROLE_NOT_EXIST(1012,"Role does not exist yet",HttpStatus.BAD_REQUEST),
    ROLE_EXIST(1013,"Role has been existed ",HttpStatus.BAD_REQUEST),
    CART_NOT_EXIST(1014,"Cart does not exist yet",HttpStatus.BAD_REQUEST),
    CART_EXIST(1015,"cart has been existed ",HttpStatus.BAD_REQUEST),
    CARTITEM_NOT_EXIST(1016,"Cart Items does not exist yet",HttpStatus.BAD_REQUEST),
    CARTITEM_EXIST(1017,"CartItems has been existed ",HttpStatus.BAD_REQUEST),
    ORDER_NOT_EXIST(1014,"Order does not exist yet",HttpStatus.BAD_REQUEST),
    ORDER_EXIST(1015,"Order has been existed ",HttpStatus.BAD_REQUEST),
    ORDER_DETAIL_NOT_EXIST(1016,"Order detail does not exist yet",HttpStatus.BAD_REQUEST),
    ORDER_DETAIL_EXIST(1017,"Order Detail has been existed ",HttpStatus.BAD_REQUEST),

    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
