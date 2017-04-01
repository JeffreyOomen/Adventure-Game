package nl.avans.ivh11.a2b.domain.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NamedQuery(
        name = "Media.findByName",
        query = "SELECT m FROM Media m WHERE m.name = ?"
)
@Getter
@Setter
@NoArgsConstructor
public class Media {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String name;
    private String imageUrl;

    public Media(String name, String url) {
        this.imageUrl = url;
        this.name = name;
    }

}
