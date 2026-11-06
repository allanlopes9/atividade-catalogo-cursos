package br.edu.catalogocursos.application;

import br.edu.catalogocursos.domain.Curso;
import br.edu.catalogocursos.domain.Instrutor;
import br.edu.catalogocursos.domain.NivelCurso;
import br.edu.catalogocursos.repository.CursoRepository;
import br.edu.catalogocursos.repository.InstrutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;
    private final InstrutorRepository instrutorRepository;

    public CursoService(
            CursoRepository cursoRepository,
            InstrutorRepository instrutorRepository) {
        this.cursoRepository = cursoRepository;
        this.instrutorRepository = instrutorRepository;
    }

    @Transactional
    public Curso cadastrar(String titulo, Integer cargaHoraria,
                           NivelCurso nivel, Long instrutorId) {

        Instrutor instrutor = instrutorRepository.findById(instrutorId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Instrutor não encontrado: " + instrutorId));

        Curso curso = new Curso(titulo, cargaHoraria, nivel);

        instrutor.adicionarCurso(curso);

        return cursoRepository.save(curso);
    }

    @Transactional(readOnly = true)
    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Curso não encontrado: " + id));
    }

    @Transactional(readOnly = true)
    public List<Curso> listarPorInstrutor(Long instrutorId) {

        if (!instrutorRepository.existsById(instrutorId)) {
            throw new EntityNotFoundException(
                    "Instrutor não encontrado: " + instrutorId);
        }

        return cursoRepository.findByInstrutorIdOrderByTituloAsc(instrutorId);
    }
}