package nl.avans.ivh11.a2b.domain.usable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
public class Inventory {

    @Id
    @GeneratedValue()
    @Column(name = "inventory_id")
    protected Long id;

    /**
     * Explictically
     */
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Map<Long, Usable> usables;

    public Inventory() {
        usables = new HashMap<>();
    }

    public boolean addUsable(Usable usable) {
        if(this.usables.put(usable.getId(), usable) != null) {
            return true;
        }
        return false;
    }

    public void dropUsable(Usable usable) {
       this.usables.remove(usable.getId());
    }

    public Usable getUsable(Long id) {
        return this.usables.get(id);
    }

}
