package com.hamburgueria.hamburgueriaapi.domain;


import com.hamburgueria.hamburgueriaapi.domain.dto.LancheDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LancheService {

    @Autowired
    private LancheRepository rep;

    public List<LancheDTO> getLanches() {
        List<Lanche> lanches = rep.findAll();

        return rep.findAll().stream().map(LancheDTO::create).collect(Collectors.toList());


    }

    public Optional<LancheDTO> getLanchesById(Long id) {

        return rep.findById(id).map(LancheDTO::create);
    }


    public LancheDTO insert (Lanche lanche) {
        Assert.isNull(lanche.getId(), "Não foi possível inserir o registro.");
        return LancheDTO.create(rep.save(lanche));
    }

    public LancheDTO update(Lanche lanche, Long id) {
        Assert.notNull(id,"Não foi possível atualizar o registro");

        Optional<Lanche> optional = rep.findById(id);
        if(optional.isPresent()){
            Lanche db = optional.get();
            db.setNome(lanche.getNome());
            db.setDescricao(lanche.getDescricao());
            db.setPreco(lanche.getPreco());
            System.out.println("Lanche id" + db.getId());

            rep.save(db);
            return LancheDTO.create(db);
        }else{
            return null;
//            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
            rep.deleteById(id);

    }
}
