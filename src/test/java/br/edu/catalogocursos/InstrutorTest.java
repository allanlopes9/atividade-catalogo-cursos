package br.edu.catalogocursos;

import br.edu.catalogocursos.domain.Curso;
import br.edu.catalogocursos.domain.Instrutor;
import br.edu.catalogocursos.domain.NivelCurso;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstrutorTest {

    @Test
    void deveAssociarCursoNosDoisLados() {
        Instrutor instrutor = new Instrutor("Ana Silva", "ana@email.com");
        Curso curso = new Curso("Java", 40, NivelCurso.INICIANTE);

        instrutor.adicionarCurso(curso);

        assertTrue(instrutor.getCursos().contains(curso));
        assertSame(instrutor, curso.getInstrutor());
    }

    @Test
    void naoDeveAdicionarOMesmoCursoDuasVezes() {
        Instrutor instrutor = new Instrutor("Ana Silva", "ana@email.com");
        Curso curso = new Curso("Java", 40, NivelCurso.INICIANTE);

        instrutor.adicionarCurso(curso);
        instrutor.adicionarCurso(curso);

        assertEquals(1, instrutor.getCursos().size());
    }

    @Test
    void naoDeveAceitarCursoNulo() {
        Instrutor instrutor = new Instrutor("Ana Silva", "ana@email.com");

        assertThrows(
                IllegalArgumentException.class,
                () -> instrutor.adicionarCurso(null)
        );
    }
}