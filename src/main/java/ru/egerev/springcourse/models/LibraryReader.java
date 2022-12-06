package ru.egerev.springcourse.models;


import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Libraryreader")
public class LibraryReader {

    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NotEmpty(message = "Поле c ФИО не должно быть пустым")
    @Size(min = 2, max = 100, message = "Поле с ФИО должно быть длиной от 2 до 100 знаков")
    private String name;

    @Column(name = "birthyear")
    @Min(value = 1901, message = "Год рождения должен быть не меньше 1901 и не больше 2099")
    @Max(value = 2099, message = "Год рождения должен быть не меньше 1901 и не больше 2099")
    private int birthYear;

    @OneToMany (mappedBy = "owner")
    private List<Book> books;

    public LibraryReader(int id, String name, int birthYear) {
        this.id = id;
        this.name = name;
        this.birthYear = birthYear;
    }

    public LibraryReader() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
