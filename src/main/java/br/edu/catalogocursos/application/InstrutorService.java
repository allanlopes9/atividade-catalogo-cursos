package br.edu.catalogocursos.application;

import br.edu.catalogocursos.domain.Instrutor;
import br.edu.catalogocursos.repository.InstrutorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstrutorService {

    private final InstrutorRepository instrutorRepository;

    public InstrutorService(InstrutorRepository instrutorRepository) {
        this.instrutorRepository = instrutorRepository;
    }

    @Transactional
    public Instrutor cadastrar(Instrutor instrutor) {
        if (instrutorRepository.existsByEmail(instrutor.getEmail())) {
            throw new IllegalStateException("E-mail já cadastrado.");
        }

        return instrutorRepository.save(instrutor);
    }

    @Transactional(readOnly = true)
    public Instrutor buscarPorId(Long id) {
        return instrutorRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Instrutor não encontrado: " + id));
    }
}