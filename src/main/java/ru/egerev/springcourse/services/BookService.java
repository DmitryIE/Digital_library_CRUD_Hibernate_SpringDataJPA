package ru.egerev.springcourse.services;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.egerev.springcourse.models.Book;
import ru.egerev.springcourse.models.LibraryReader;
import ru.egerev.springcourse.repositories.BooksRepository;
import ru.egerev.springcourse.repositories.LibraryReadersRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {

    private BooksRepository booksRepository;
    private LibraryReadersRepository libraryReadersRepository;

    @Autowired
    public BookService(BooksRepository booksRepository, LibraryReadersRepository libraryReadersRepository) {
        this.booksRepository = booksRepository;
        this.libraryReadersRepository = libraryReadersRepository;
    }

    public List<Book> index() {
        return booksRepository.findAll();
    }

    public List<Book> index(int page, int booksPerPage) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage)).getContent();
    }

    public List<Book> indexSorted() {
        return booksRepository.findAll(Sort.by("year"));
    }

    public List<Book> indexSorted(int page, int booksPerPage) {
        return booksRepository.findAll(PageRequest.of(page, booksPerPage, Sort.by("year"))).getContent();
    }

    public Book show(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    public List<LibraryReader> allReaders() {
        return libraryReadersRepository.findAll();
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {
        updateBook.setId(id);
        booksRepository.save(updateBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void freeBook(int id) {
        Optional<Book> bookToBeFree = booksRepository.findById(id);
        if (bookToBeFree.isPresent()) {
            bookToBeFree.get().setOwner(null);
            bookToBeFree.get().setDateOfTakenBook(null);
        }
    }

    @Transactional
    public void takeBook(int id, LibraryReader libraryReader) {
        Optional<Book> bookToBeTaken = booksRepository.findById(id);
        if (bookToBeTaken.isPresent()) {
            bookToBeTaken.get().setOwner(libraryReader);
            bookToBeTaken.get().setDateOfTakenBook(new Date());
        }
    }

    public List<Book> search(String partOfTitle) {
        return booksRepository.findByTitleContainingIgnoreCase(partOfTitle);
    }

}
