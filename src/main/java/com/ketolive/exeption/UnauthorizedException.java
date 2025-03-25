package com.ketolive.exeption;

//Исключение, которое выбрасывается, когда пользователь пытается выполнить
// * действие, на которое у него нет прав.
public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
