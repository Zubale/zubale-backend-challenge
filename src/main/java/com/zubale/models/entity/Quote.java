package com.zubale.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "quotes")
public class Quote implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = " is required")
    @Size(min = 4, max = 30 , message = "Size should be between 4 to 30")
    @Column(nullable = false)
    private String autor;

    @NotEmpty(message = " is required")
    @Size(min = 4, max = 30 , message = "Size should be between 4 to 30")
    @Column(nullable = false)
    private String book;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"quote","hibernateLazyInitializer","handler"})
    @JoinColumn(name = "quote_id")
    private List<Vote> votes;

    @NotNull
    @Column(name = "user_id")
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Date createAt;

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }


    @Override
    public String toString() {
        return "Quote{" +
                "id=" + id +
                ", autor='" + autor + '\'' +
                ", book='" + book + '\'' +
                ", createAt=" + createAt +
                ", votes=" + votes +
                '}';
    }
}
