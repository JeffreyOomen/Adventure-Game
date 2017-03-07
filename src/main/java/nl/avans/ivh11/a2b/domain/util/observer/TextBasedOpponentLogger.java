package nl.avans.ivh11.a2b.domain.util.observer;

import nl.avans.ivh11.a2b.domain.character.Character;
import nl.avans.ivh11.a2b.domain.util.Opponent;

/**
 * Created by matthijs on 6-3-17.
 */
public class TextBasesOpponentLogger extends OpponentObserver {
    @Override
    public void update(Observable observable) {
        if(observable instanceof Character) {
            Character subject = (Character) observable;
            System.out.println(subject.);
        }
    }
}
