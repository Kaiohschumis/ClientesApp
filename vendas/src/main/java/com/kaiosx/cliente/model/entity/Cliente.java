package com.kaiosx.cliente.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    @Column(nullable = false, length = 150)
    @NotEmpty(message = "{campo_nome_obrigatorio")
    private String nome;

    @Column(nullable = false, length = 11)
    @NotNull(message = "{campo_cpf_obrigatorio")
    @CPF(message = "{campo_cpf_obrigatorio")
    private String cpf;

    @Column(name = "data_cadastro")
    @JsonFormat(pattern = "dd//MM/yyyy")
    private LocalDate dataCadastro;

    @PrePersist
    public void prePersist(){
        setDataCadastro(LocalDate.now());
    }



}
