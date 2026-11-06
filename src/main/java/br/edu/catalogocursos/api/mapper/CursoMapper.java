package br.edu.catalogocursos.api.mapper;

import br.edu.catalogocursos.api.dto.CursoResponse;
import br.edu.catalogocursos.api.dto.CursoResumoResponse;
import br.edu.catalogocursos.domain.Curso;
import org.springframework.stereotype.Component;

@Component
public class CursoMapper {

    public CursoResponse toResponse(Curso curso) {
        return new CursoResponse(
                curso.getId(),
                curso.getTitulo(),
                curso.getCargaHoraria(),
                curso.getNivel(),
                new CursoResumoResponse(
                        curso.getInstrutor().getId(),
                        curso.getInstrutor().getNome()
                )
        );
    }
}