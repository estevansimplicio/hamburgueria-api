package com.hamburgueria.hamburgueriaapi.api;

import com.hamburgueria.hamburgueriaapi.domain.Lanche;
import com.hamburgueria.hamburgueriaapi.domain.LancheService;
import com.hamburgueria.hamburgueriaapi.domain.dto.LancheDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/lanches")
public class LanchesController {

    @Autowired
    private LancheService service;

    @GetMapping
    public ResponseEntity<List<LancheDTO>> get(){
        return ResponseEntity.ok(service.getLanches());
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id){
        Optional<LancheDTO> lanche = service.getLanchesById(id);

        if(lanche.isPresent()){
            return ResponseEntity.ok(lanche.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity post (@RequestBody Lanche lanche){

            LancheDTO l = service.insert(lanche);

            URI location = getUri(l.getId());
            return ResponseEntity.created(location).build();

    }

    private URI getUri(Long id){
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri();
    }

    @PutMapping("/{id}")
    public ResponseEntity put (@PathVariable("id") Long id, @RequestBody Lanche lanche){
        lanche.setId(id);

        LancheDTO l = service.update(lanche, id);

        return l != null ?
                ResponseEntity.ok(l) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete (@PathVariable("id") Long id){
        service.delete(id);

        return ResponseEntity.ok().build();
    }





}
