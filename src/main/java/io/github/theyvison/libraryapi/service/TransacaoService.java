package io.github.theyvison.libraryapi.service;

import io.github.theyvison.libraryapi.model.Autor;
import io.github.theyvison.libraryapi.model.GeneroLivro;
import io.github.theyvison.libraryapi.model.Livro;
import io.github.theyvison.libraryapi.repository.AutorRepository;
import io.github.theyvison.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemAtualizar() {
        var livro = livroRepository
                .findById(UUID.fromString("86a7e7f0-bc4b-468d-891d-47573b41d65f"))
                .orElse(null);

        livro.setDataPublicacao(LocalDate.of(2024, 6, 1));
    }

    @Transactional
    public void executar() {
        Autor autor = new Autor();
        autor.setNome("Teste Francisco");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1960, 8, 11));

        autorRepository.saveAndFlush(autor);


        Livro livro = new Livro();
        livro.setIsbn("58413-99856");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Teste livro do Francisco");
        livro.setDataPublicacao(LocalDate.of(1999, 4, 16));

        livro.setAutor(autor);

        livroRepository.saveAndFlush(livro);

        if (autor.getNome().equals("Teste Francisco")) {
            throw new RuntimeException("Rollback");
        }
    }
}
