package ru.egerev.springcourse.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "Book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Поле с названием книги не должно быть пустым")
    @Size(min = 2, max = 100, message = "Поле с названием книги должно быть длиной от 2 до 100 знаков")
    private String title;

    @Column(name = "author")
    @NotEmpty(message = "Поле с автором книги не должно быть пустым")
    @Size(min = 2, max = 100, message = "Поле с автором книги должно быть длиной от 2 до 100 знаков")
    private String author;

    @Column(name = "year")
    @Min(value = 1501, message = "Год издания должен быть не меньше 1501 и не больше 2099")
    @Max(value = 2099, message = "Год издания должен быть не меньше 1501 и не больше 2099")
    private int year;

    @Column(name = "took_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfTakenBook;

    @ManyToOne
    @JoinColumn(name = "libraryreader_id", referencedColumnName = "id")
    private LibraryReader owner;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public LibraryReader getOwner() {
        return owner;
    }

    public void setOwner(LibraryReader owner) {
        this.owner = owner;
    }

    public Date getDateOfTakenBook() {
        return dateOfTakenBook;
    }

    public void setDateOfTakenBook(Date dateOfTakenBook) {
        this.dateOfTakenBook = dateOfTakenBook;
    }
}
