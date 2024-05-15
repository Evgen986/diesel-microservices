package ru.maliutin.storage.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

/**
 * Сущность техники.
 */
@Entity
@Table(name = "t_technic")
@Getter
@Setter
public class Technic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long technicId;
    @Column(name = "c_title")
    private String title;
    @ManyToMany(mappedBy = "technics")
    @JsonIgnore
    private Set<Product> products;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Technic technic = (Technic) o;
        return Objects.equals(title, technic.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return "Technic{" +
                "technicId=" + technicId +
                ", title='" + title + '\'' +
                '}';
    }
}
