package nl.avans.ivh11.a2b.domain.util.observer;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.util.Opponent;

/**
 * Created by matthijs on 6-3-17.
 */
public class TextBasedOpponentLogger implements Observer {
    @Override
    public void update(Observable observable) {
        if(observable instanceof Character) {
            Opponent subject = (Opponent) observable;
            System.out.println(subject);
        }
    }
}
