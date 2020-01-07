package de.ralfhergert.gw2.model;

import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;

import java.util.List;

import static de.ralfhergert.gw2.model.CharacterAttribute.*;

/**
 * The values used in this class always refer to the chest piece.
 */
public enum Prefix {
    Apothecary("Veldrunner", "Ebonmane", false, increase(HealingPower, 141), increase(Toughness, 101), increase(ConditionDamage, 101)),
    Assassin("Saphir", "Soros", true, increase(Precision, 141), increase(Power, 101), increase(Ferocity, 101)),
    Berserker("Zojja", "Zojja", true, increase(Power, 141), increase(Precision, 101), increase(Ferocity, 101)),
    Bringer("Giftbringer", "Giftbringer", false, increase(Expertise, 141), increase(Precision, 101), increase(Vitality, 101)),
    Carrion("Occam", "Occam", true, increase(ConditionDamage, 141), increase(Power, 101), increase(Vitality, 101)),
    Cavalier("Angchu", "Angchu", true, increase(Toughness, 141), increase(Power, 101), increase(Ferocity, 101)),
    Celestial("Wupwup", "Wupwup", true, increase(Power,67), increase(Precision, 67), increase(Toughness, 67), increase(Vitality,67), increase(ConditionDamage, 67), increase(Ferocity, 67), increase(HealingPower, 67)),
    Cleric("Tateos", "Theodosus", false, increase(HealingPower, 141), increase(Power, 101), increase(Toughness, 101)),
    Commander("Tizlak", "Tizlak", false, increase(Power, 121), increase(Precision, 121), increase(Toughness, 67), increase(Concentration, 67)),
    Crusader("Ossa", "Ossa", false, increase(Power, 121), increase(Toughness, 121), increase(Ferocity, 67), increase(HealingPower, 67)),
    Dire("Morbach", "Mathilde", false, increase(ConditionDamage, 141), increase(Toughness, 101), increase(Vitality, 101)),
    Diviner("Steelstar", "Steelstar", true, increase(Power, 121), increase(Concentration, 121), increase(Precision, 67), increase(Ferocity, 67)),
    Giver("Tixx", "Tixx", false, increase(Toughness, 141), increase(HealingPower, 101), increase(Concentration, 101)),
    Grieving("The Twins", "The Twins", true, increase(Power, 121), increase(ConditionDamage, 121), increase(Precision, 67), increase(Concentration, 67)),
    Harrier("Zehtuka", "Zehtuka", true, increase(Power, 141), increase(HealingPower, 101), increase(Concentration, 101)),
    Knight("Beigarth", "Beigarth", true, increase(Toughness, 141), increase(Power, 101), increase(Precision, 101)),
    Magi("Hronk", "Hronk", false, increase(HealingPower, 141), increase(Precision, 101), increase(Vitality, 101)),
    Marauder("Svaard", "Svaard", true, increase(Power, 121), increase(Precision, 121), increase(Vitality, 67), increase(Ferocity, 67)),
    Marshal("Nadijeh", "Nadijeh", true, increase(Power, 121), increase(HealingPower, 121), increase(Precision, 67), increase(ConditionDamage, 67)),
    Minstrel("Maklain", "Maklain", false, increase(Toughness, 121), increase(HealingPower, 121), increase(Vitality, 67), increase(Concentration, 67)),
    Nomad("Ventari", "Ventari", false, increase(Toughness, 141), increase(Vitality, 101), increase(HealingPower, 101)),
    Plaguedoctor("Nerashi", "Nerashi", false, increase(ConditionDamage, 121), increase(Vitality, 121), increase(HealingPower, 67), increase(Concentration, 67)),
    Rabid("Ferratus", "Grizzlemouth", true, increase(ConditionDamage, 141), increase(Precision, 101), increase(Toughness, 101)),
    Rampager("Forgemaster", "Coalforge", true, increase(Precision, 141), increase(Power, 101), increase(ConditionDamage, 101)),
    Sentinel("Wei Qi", "Tonn", true, increase(Vitality, 141), increase(Power, 101), increase(Toughness, 101)),
    Seraph("Thackeray", "Thackeray", false, increase(Precision, 121), increase(ConditionDamage, 121), increase(Concentration, 67), increase(HealingPower, 67)),
    Settler("Leftpaw", "Leftpaw", false, increase(Toughness, 141), increase(ConditionDamage, 101), increase(HealingPower, 101)),
    Shaman("Zintl", "Zintl", false, increase(Vitality, 141), increase(ConditionDamage, 101), increase(HealingPower, 101)),
    Sinister("Verata", "Verata", true, increase(ConditionDamage, 141), increase(Power, 101), increase(Precision, 101)),
    Soldier("Ahamid", "Chorben", false, increase(Power, 141), increase(Toughness, 101), increase(Vitality, 101)),
    Trailblazer("Pahua", "Pahua", false, increase(Toughness, 121), increase(ConditionDamage, 121), increase(Vitality, 67), increase(Expertise, 67)),
    Valkyrie("Gobrech", "Stonecleaver", true, increase(Power, 141), increase(Vitality, 101), increase(Ferocity, 101)),
    Vigilant("Laranthir", "Laranthir", false, increase(Power, 121), increase(Toughness, 121), increase(Concentration, 67), increase(Expertise, 67)),
    Viper("Yassith", "Yassith", true, increase(Power, 121), increase(ConditionDamage, 121), increase(Precision, 67), increase(Expertise, 67)),
    Wanderer("Ruka", "Ruka", false, increase(Power, 121), increase(Vitality, 121), increase(Toughness, 67), increase(Concentration, 67)),
    Zealot("Keeper", "Keeper", false, increase(Power, 141), increase(Precision, 101), increase(HealingPower, 101));

    private final String armorName;
    private final String weaponName;
    private final boolean availableInPvP;
    private final List<CharacterAttributeModifier> modifiers;

    Prefix(String armorName, String weaponName, boolean availableInPvP, CharacterAttributeModifier... modifiers) {
        this.armorName = armorName;
        this.weaponName = weaponName;
        this.availableInPvP = availableInPvP;
        this.modifiers = List.of(modifiers);
    }

    /**
     * This utility method is just there to make the enum list better readable.
     */
    public static CharacterAttributeModifier increase(CharacterAttribute key, int value) {
        return new CharacterAttributeModifier(key, value);
    }

    public String getArmorName() {
        return armorName;
    }

    public String getWeaponName() {
        return weaponName;
    }

    public boolean isAvailableInPvP() {
        return availableInPvP;
    }

    public List<CharacterAttributeModifier> getModifiers() {
        return modifiers;
    }
}
