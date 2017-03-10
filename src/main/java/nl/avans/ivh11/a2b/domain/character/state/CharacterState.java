package nl.avans.ivh11.a2b.domain.character.state;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Represents the Character state
 */
@Entity
@Table(name = "CHARACTER_STATE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "CHARACTER_STATE_TYPE")
@Getter
@Setter
public abstract class CharacterState
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CHARACTER_STATE_ID")
    protected Long id;

    protected String name;

    protected String description;

    /**
     * Gets the strength level which will temporary
     * effect the Character's current strength level.
     * @return a temporary additional strength level
     */
    public abstract int getStrength();

    /**
     * Gets the magic level which will temporary
     * effect the Character's current magic level.
     * @return a temporary additional magic level
     */
    public abstract int getMagic();

    /**
     * Gets the defense level which will temporary
     * effect the Character's current defense level.
     * @return a temporary additional defense level
     */
    public abstract int getDefense();

    /**
     * Gets the archery level which will temporary
     * effect the Character's current archery level.
     * @return a temporary additional archery level
     */
    public abstract int getArchery();
}
