package ua.lviv.iot.imdb.domain;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class Award {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "award")
    private List<Nomination> nominations;
    @ManyToMany
    @JoinTable(name = "award_movie", catalog = "", schema = "teliuk", joinColumns = @JoinColumn(name = "award_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id", nullable = false))
    private Set<Movie> movies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Award award = (Award) o;
        return Objects.equals(id, award.id) && Objects.equals(name, award.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public List<Nomination> getNominations() {
        return nominations;
    }

    public void setNominations(List<Nomination> nominations) {
        this.nominations = nominations;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void setMovies(Set<Movie> movies) {
        this.movies = movies;
    }
}