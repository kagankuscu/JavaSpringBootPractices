package com.example.database.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(name = "author_id_seq", sequenceName = "author_id_seq")
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_id_seq")
    private Long id;
    private Integer age;
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books;
}
