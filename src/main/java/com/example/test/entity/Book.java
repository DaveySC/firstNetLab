package com.example.test.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOk")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookId")
    private Integer id;

    @Column(name = "CATEGORY")
    private String category;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AccountingRecords> accountingRecords = new ArrayList<>();


    @ManyToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(
            name = "BOOK_AUTHOR",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name="author_id")}
    )
    private List<Author> authors = new ArrayList<>();

    public void addAuthor(Author author) {
        this.authors.add(author);
    }

    public Book(String category, String name) {
        this.category = category;
        this.name = name;

    }


    @Override
    public String toString() {
        StringBuilder helper = new StringBuilder();
        helper.append(this.id).append(" ").append(this.category).append('\n');
        for  (Author author : authors) {
            helper.append(author.getFirstname()).append(" ");
        }
        return helper.toString();
    }
}
