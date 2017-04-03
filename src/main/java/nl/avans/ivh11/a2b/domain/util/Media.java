package nl.avans.ivh11.a2b.domain.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Media
{
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String imageName;

    private String imageUrl;


    public Media(String url, String imageName) {
        this.imageUrl = url;
        this.imageName = imageName;
    }
}
