package nl.avans.ivh11.a2b.domain.character;

import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * With this class hierarchy, a Character can be decorated
 * with specialization in an attack style
 */
@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class CharacterDecorator extends Character
{
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CHARACTER_ID")
    Character character; // this object will be decorated

    public CharacterDecorator(Character character) {
        this.character = character;
    }
}
