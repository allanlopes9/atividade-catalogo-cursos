package br.edu.catalogocursos;

import br.edu.catalogocursos.application.CursoService;
import br.edu.catalogocursos.domain.Curso;
import br.edu.catalogocursos.domain.Instrutor;
import br.edu.catalogocursos.domain.NivelCurso;
import br.edu.catalogocursos.repository.InstrutorRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class CursoPersistenciaIntegrationTest {

    @Autowired
    private InstrutorRepository instrutorRepository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private EntityManager entityManager;

    @Test
    void devePersistirCursosAssociadosAoInstrutorEListarEmOrdemAlfabetica() {

        Instrutor instrutor = new Instrutor(
                "Ana Silva",
                "ana.integracao@email.com"
        );

        Instrutor outroInstrutor = new Instrutor(
                "Carlos Souza",
                "carlos.integracao@email.com"
        );

        instrutorRepository.saveAndFlush(instrutor);
        instrutorRepository.saveAndFlush(outroInstrutor);

        cursoService.cadastrar(
                "Java",
                40,
                NivelCurso.INICIANTE,
                instrutor.getId()
        );

        cursoService.cadastrar(
                "Algoritmos",
                30,
                NivelCurso.INICIANTE,
                instrutor.getId()
        );

        cursoService.cadastrar(
                "Banco de Dados",
                50,
                NivelCurso.INTERMEDIARIO,
                outroInstrutor.getId()
        );

        entityManager.flush();
        entityManager.clear();

        List<Curso> cursos = cursoService.listarPorInstrutor(
                instrutor.getId()
        );

        assertEquals(2, cursos.size());

        assertEquals("Algoritmos", cursos.get(0).getTitulo());
        assertEquals("Java", cursos.get(1).getTitulo());

        assertTrue(cursos.stream()
                .allMatch(curso ->
                        curso.getInstrutor().getId().equals(instrutor.getId())
                )
        );
    }
}