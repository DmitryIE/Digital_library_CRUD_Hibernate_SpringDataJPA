package ru.egerev.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.egerev.springcourse.models.LibraryReader;

import java.util.List;

@Repository
public interface LibraryReadersRepository extends JpaRepository<LibraryReader, Integer> {

    List<LibraryReader> findByName(String name);

}
