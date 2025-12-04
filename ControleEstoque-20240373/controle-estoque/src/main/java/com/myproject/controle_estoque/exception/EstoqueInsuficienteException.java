package com.myproject.controle_estoque.exception;

public class EstoqueInsuficienteException extends RuntimeException {
    public EstoqueInsuficienteException(String msg) {
        super(msg);
    }
}
