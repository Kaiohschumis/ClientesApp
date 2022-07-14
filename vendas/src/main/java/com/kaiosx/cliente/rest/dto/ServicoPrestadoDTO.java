package com.kaiosx.cliente.rest.dto;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@RequestMapping
public class ServicoPrestadoDTO {

    @NotEmpty(message = "{campo_descricao_obrigatorio}")
    private String descricao;

    @NotEmpty(message = "{campo_preco_obrigatorio}")
    private String preco;

    @NotEmpty(message = "{campo_data_obrigatorio}")
    private String data;

    @NotNull
    private Integer idCliente;
}
