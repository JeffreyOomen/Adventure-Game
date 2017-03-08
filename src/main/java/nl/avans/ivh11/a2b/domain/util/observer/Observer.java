package nl.avans.ivh11.a2b.domain.util.observer;

import nl.avans.ivh11.a2b.domain.character.Enemy;
import nl.avans.ivh11.a2b.domain.character.Character;

/**
 * Created by matthijs on 5-3-17.
 */
public interface Observer
{
    public void update(Observable subject);
}
