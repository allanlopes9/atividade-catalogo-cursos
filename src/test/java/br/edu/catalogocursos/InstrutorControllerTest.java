package br.edu.catalogocursos;

import br.edu.catalogocursos.api.controller.InstrutorController;
import br.edu.catalogocursos.api.exception.GlobalExceptionHandler;
import br.edu.catalogocursos.api.mapper.InstrutorMapper;
import br.edu.catalogocursos.application.InstrutorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class InstrutorControllerTest {

    private MockMvc mockMvc;
    private InstrutorService instrutorService;

    @BeforeEach
    void configurar() {
        instrutorService = mock(InstrutorService.class);

        InstrutorController controller = new InstrutorController(
                instrutorService,
                new InstrutorMapper()
        );

        LocalValidatorFactoryBean validator =
                new LocalValidatorFactoryBean();
        validator.afterPropertiesSet();

        mockMvc = standaloneSetup(controller)
                .setControllerAdvice(new GlobalExceptionHandler())
                .setValidator(validator)
                .build();
    }

    @Test
    void deveRetornar400QuandoDadosDoInstrutorSaoInvalidos()
            throws Exception {

        mockMvc.perform(post("/api/instrutores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "nome": "Al",
                                    "email": "email-invalido"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.path")
                        .value("/api/instrutores"));

        verifyNoInteractions(instrutorService);
    }
}
