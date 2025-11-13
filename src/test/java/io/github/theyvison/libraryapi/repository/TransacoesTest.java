package io.github.theyvison.libraryapi.repository;

import io.github.theyvison.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TransacoesTest {
    @Autowired
    TransacaoService transacaoService;

    @Test
    void transacaoSimplesTest() {
        transacaoService.executar();
    }
}
