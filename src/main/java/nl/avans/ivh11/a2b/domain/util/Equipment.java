package nl.avans.ivh11.a2b.domain.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Equipment
{
    @Id
    @GeneratedValue
    Long id;

    String name;

    EquipmentEnum type;

    public Equipment(String name, EquipmentEnum type) {
        this.name = name;
        this.type = type;
    }



}
