package com.zubale.models.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Quotes")
public class Quote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Autor is required")
    @Size(min = 4, max = 30 , message = "Size should be between 4 to 30")
    @Column(nullable = false)
    private String autor;

    @NotEmpty(message = "Book is required")
    @Size(min = 4, max = 30 , message = "Size should be between 4 to 30")
    @Column(nullable = false)
    private String book;

    private Date createAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles",
            joinColumns         = @JoinColumn(name = "quote_id"),
            inverseJoinColumns  = @JoinColumn(name = "vote_id"),
            uniqueConstraints   = {@UniqueConstraint(columnNames = {"quote_id","vote_id"})})
    private List<Vote> votes;

    private User user;


}
