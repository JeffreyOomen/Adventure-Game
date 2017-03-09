package nl.avans.ivh11.a2b.domain.usable;

public class BandosEquipment extends Equipment {


    public BandosEquipment(UsableType type, int level) {
        super(type, level);
        setName("Bandos " + getEquipmentType(type));
        setDescription("Item from the Bandos collection. Very useful as a warrior.");
        setEnchantedStats(1.5, 1.2, 1, 1);
    }

    @Override
    // TODO: misschien in superklasse deze
    protected void setEnchantedStats(double strength, double defense, double archery, double magic) {
        int level = this.stats.getLevel();

        int enchantedStrength = (int) Math.round(level * strength);
        int enchantedDefense = (int) Math.round(level * defense);
        int enchantedArchery = (int) Math.round(level * archery);
        int enchantedMagic = (int) Math.round(level * magic);

        stats.setStrength(getStats().getStrength() + enchantedStrength);
        stats.setDefense(getStats().getDefense() + enchantedDefense);
        stats.setArchery(getStats().getDefense() + enchantedArchery);
        stats.setMagic(getStats().getDefense() + enchantedMagic);
    }

}
