package nl.avans.ivh11.a2b.domain.character.state;

public class NormalState extends CharacterState
{
    private final static int STRENGTH_LEVEL = 0;
    private final static int MAGIC_LEVEL = 0;
    private final static int DEFENSE_LEVEL = 0;
    private final static int ARCHERY_LEVEL = 0;

    @Override
    public int getStrength() {
        return STRENGTH_LEVEL;
    }

    @Override
    public int getMagic() {
        return MAGIC_LEVEL;
    }

    @Override
    public int getDefense() {
        return DEFENSE_LEVEL;
    }

    @Override
    public int getArchery() {
        return ARCHERY_LEVEL;
    }
}
