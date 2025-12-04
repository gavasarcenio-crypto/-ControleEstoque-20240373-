package com.myproject.controle_estoque.dto.venda;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class VendaResponse {

    private Long id;
    private Long clienteId;
    private LocalDateTime dataVenda;

    private List<ItemVendaDetalhe> itens;

    @Data
    public static class ItemVendaDetalhe {
        private Long produtoId;
        private String produtoNome;
        private Integer quantidade;
        private String precoUnitario;
    }
}
