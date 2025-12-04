package com.myproject.controle_estoque.dto.estoque;

import lombok.Data;

@Data
public class EstoqueDTO {
    private Long id;
    private Integer quantidade;
    private Long produtoId;
}

