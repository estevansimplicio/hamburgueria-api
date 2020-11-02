package com.hamburgueria.hamburgueriaapi.api.lanches;

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
