package io.github.theyvison.libraryapi.repository;

import io.github.theyvison.libraryapi.model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Maria");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1955, 3, 14));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void AtualizarTest() {
        var id = UUID.fromString("25335a11-c2a6-467d-9a23-6dfc9fab6cda");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do autor");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 31));

            autorRepository.save(autorEncontrado);
        }
    }
}
