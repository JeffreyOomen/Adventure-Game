package nl.avans.ivh11.a2b.service;

public interface CharacterService
{
    Character findById(long id);

    Character save(Character character);

}
