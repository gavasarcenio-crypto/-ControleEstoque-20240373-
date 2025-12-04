package com.myproject.controle_estoque.dto.venda;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class VendaRequest {

    @NotNull(message = "O ID do cliente é obrigatório.")
    private Long clienteId;

    @NotNull(message = "A lista de itens não pode ser nula.")
    @Size(min = 1, message = "A venda deve conter pelo menos um item.")
    private List<ItemVendaRequest> itens;

}