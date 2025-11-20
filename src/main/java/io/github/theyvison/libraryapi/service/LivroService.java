package io.github.theyvison.libraryapi.service;

import io.github.theyvison.libraryapi.model.GeneroLivro;
import io.github.theyvison.libraryapi.model.Livro;
import io.github.theyvison.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static io.github.theyvison.libraryapi.repository.specs.LivroSpecs.*;

@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id) {
        return livroRepository.findById(id);
    }

    public void deletar(Livro livro) {
        livroRepository.delete(livro);
    }

    public List<Livro> pesquisa(String isbn, String titulo, String nomeAutor, GeneroLivro genero, Integer anoPublicacao) {
        Specification<Livro> specs = Specification.where((root, query, cb) ->
                cb.conjunction());

        if (isbn != null) {
            specs = specs.and(isbnEqual(isbn));
        }

        if (titulo != null) {
            specs = specs.and(tituloLike(titulo));
        }

        if (genero != null) {
            specs = specs.and(generoEqual(genero));
        }

        if (anoPublicacao != null) {
            specs = specs.and(anoPublicacaoEqual(anoPublicacao));
        }

        return livroRepository.findAll(specs);
    }
}
