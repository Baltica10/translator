package com.test.translator.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class Dictionary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String ru;

    @Column(nullable = false)
    private String en;
}
