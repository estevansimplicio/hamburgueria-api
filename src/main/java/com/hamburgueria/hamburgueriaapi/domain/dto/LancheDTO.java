package com.hamburgueria.hamburgueriaapi.domain.dto;

import com.hamburgueria.hamburgueriaapi.domain.Lanche;
import lombok.Data;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
public class LancheDTO {

    public Long id;
    public String nome;
    public String descricao;
    public BigDecimal preco;



    public static LancheDTO create(Lanche l){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(l, LancheDTO.class);
    }
}
