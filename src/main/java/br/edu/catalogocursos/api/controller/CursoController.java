package br.edu.catalogocursos.api.controller;

import br.edu.catalogocursos.api.dto.CursoRequest;
import br.edu.catalogocursos.api.dto.CursoResponse;
import br.edu.catalogocursos.api.mapper.CursoMapper;
import br.edu.catalogocursos.application.CursoService;
import br.edu.catalogocursos.domain.Curso;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CursoController {

    private final CursoService cursoService;
    private final CursoMapper cursoMapper;

    public CursoController(
            CursoService cursoService,
            CursoMapper cursoMapper) {
        this.cursoService = cursoService;
        this.cursoMapper = cursoMapper;
    }

    @PostMapping("/cursos")
    public ResponseEntity<CursoResponse> cadastrar(
            @Valid @RequestBody CursoRequest request) {

        Curso cursoSalvo = cursoService.cadastrar(
                request.titulo(),
                request.cargaHoraria(),
                request.nivel(),
                request.instrutorId()
        );

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cursoSalvo.getId())
                .toUri();

        return ResponseEntity
                .created(location)
                .body(cursoMapper.toResponse(cursoSalvo));
    }

    @GetMapping("/cursos/{id}")
    public ResponseEntity<CursoResponse> buscarPorId(@PathVariable Long id) {

        Curso curso = cursoService.buscarPorId(id);

        return ResponseEntity.ok(cursoMapper.toResponse(curso));
    }

    @GetMapping("/instrutores/{instrutorId}/cursos")
    public ResponseEntity<List<CursoResponse>> listarPorInstrutor(
            @PathVariable Long instrutorId) {

        List<CursoResponse> cursos = cursoService
                .listarPorInstrutor(instrutorId)
                .stream()
                .map(cursoMapper::toResponse)
                .toList();

        return ResponseEntity.ok(cursos);
    }
}
