package br.edu.catalogocursos;

import br.edu.catalogocursos.application.CursoService;
import br.edu.catalogocursos.domain.Curso;
import br.edu.catalogocursos.domain.Instrutor;
import br.edu.catalogocursos.domain.NivelCurso;
import br.edu.catalogocursos.repository.CursoRepository;
import br.edu.catalogocursos.repository.InstrutorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CursoServiceListagemTest {

    @Mock
    private CursoRepository cursoRepository;

    @Mock
    private InstrutorRepository instrutorRepository;

    @InjectMocks
    private CursoService cursoService;

    @Test
    void deveListarSomenteCursosDoInstrutorEmOrdemAlfabetica() {
        Instrutor instrutor = new Instrutor(
                "Ana Silva",
                "ana@email.com"
        );

        Instrutor outroInstrutor = new Instrutor(
                "Carlos Souza",
                "carlos@email.com"
        );

        Curso cursoJava = new Curso(
                "Java",
                40,
                NivelCurso.INICIANTE
        );

        Curso cursoAlgoritmos = new Curso(
                "Algoritmos",
                30,
                NivelCurso.INICIANTE
        );

        instrutor.adicionarCurso(cursoJava);
        instrutor.adicionarCurso(cursoAlgoritmos);

        Curso cursoOutroInstrutor = new Curso(
                "Banco de Dados",
                50,
                NivelCurso.INTERMEDIARIO
        );

        outroInstrutor.adicionarCurso(cursoOutroInstrutor);

        when(instrutorRepository.existsById(1L)).thenReturn(true);

        when(cursoRepository.findByInstrutorIdOrderByTituloAsc(1L))
                .thenReturn(List.of(cursoAlgoritmos, cursoJava));

        List<Curso> resultado = cursoService.listarPorInstrutor(1L);

        assertEquals(2, resultado.size());
        assertEquals("Algoritmos", resultado.get(0).getTitulo());
        assertEquals("Java", resultado.get(1).getTitulo());

        assertTrue(resultado.stream()
                .allMatch(curso -> curso.getInstrutor() == instrutor));

        assertFalse(resultado.contains(cursoOutroInstrutor));

        verify(cursoRepository)
                .findByInstrutorIdOrderByTituloAsc(1L);
    }
}