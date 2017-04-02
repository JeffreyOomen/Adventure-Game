package nl.avans.ivh11.a2b.domain.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Media
{
    @Id
    @GeneratedValue
    private long id;

    private String imageUrl;

    private String imageName;

    public Media(String url, String imageName) {
        this.imageUrl = url;
        this.imageName = imageName;
    }
}
