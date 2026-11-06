package br.edu.catalogocursos;

import br.edu.catalogocursos.application.CursoService;
import br.edu.catalogocursos.domain.NivelCurso;
import br.edu.catalogocursos.repository.CursoRepository;
import br.edu.catalogocursos.repository.InstrutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CursoServiceTest {

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private InstrutorRepository instrutorRepository;

    @InjectMocks
    private CursoService cursoService;

    @Test
    void deveRejeitarCursoQuandoInstrutorNaoExiste() {
        when(instrutorRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                EntityNotFoundException.class,
                () -> cursoService.cadastrar(
                        "Java",
                        40,
                        NivelCurso.INICIANTE,
                        99L
                )
        );

        verify(cursoRepository, never()).save(any());
    }
}