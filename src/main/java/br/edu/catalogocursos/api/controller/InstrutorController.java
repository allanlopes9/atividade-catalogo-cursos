package br.edu.catalogocursos.api.controller;

import br.edu.catalogocursos.api.dto.InstrutorRequest;
import br.edu.catalogocursos.api.dto.InstrutorResponse;
import br.edu.catalogocursos.api.mapper.InstrutorMapper;
import br.edu.catalogocursos.application.InstrutorService;
import br.edu.catalogocursos.domain.Instrutor;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/instrutores")
public class InstrutorController {

    private final InstrutorService instrutorService;
    private final InstrutorMapper instrutorMapper;

    public InstrutorController(
            InstrutorService instrutorService,
            InstrutorMapper instrutorMapper) {
        this.instrutorService = instrutorService;
        this.instrutorMapper = instrutorMapper;
    }

    @PostMapping
    public ResponseEntity<InstrutorResponse> cadastrar(
            @Valid @RequestBody InstrutorRequest request) {

        Instrutor instrutor = instrutorMapper.toEntity(request);
        Instrutor instrutorSalvo = instrutorService.cadastrar(instrutor);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(instrutorSalvo.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(instrutorMapper.toResponse(instrutorSalvo));
    }
}
