package de.ralfhergert.gw2.generator;

import de.ralfhergert.gw2.model.*;
import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;

import java.util.*;

import static de.ralfhergert.gw2.model.CharacterAttribute.*;

public class TrinketPieceGenerator {

    private static final Map<TrinketKey,TrinketPiece> trinkets = createLookUpMap();

    /**
     * List of available trinkets https://wiki.guildwars2.com/wiki/Ascended_trinket#Available_trinket_prefixes
     * Examples:
     *  - https://wiki.guildwars2.com/wiki/Talisman_of_Holt
     *  - https://wiki.guildwars2.com/wiki/Blood_Ruby_Band
     *  - https://wiki.guildwars2.com/wiki/Mist_Talisman
     *  - https://wiki.guildwars2.com/wiki/Icebrood_Horn_Backpack
     */
    private static Map<TrinketKey,TrinketPiece> createLookUpMap() {
        Map<TrinketKey,TrinketPiece> map = new HashMap<>();
        add(map, Prefix.Berserker, TrinketSlot.Back, "Icebrood Horn Backpack", inc(Power, 63), inc(Precision, 40), inc(Ferocity, 40));
        add(map, Prefix.Berserker, TrinketSlot.Neck, "Mark of the Tehyos", inc(Power, 157), inc(Precision, 108), inc(Ferocity, 108));
        add(map, Prefix.Berserker, Arrays.asList(TrinketSlot.FingerLeft, TrinketSlot.FingerRight), "Ring of Red Death", inc(Power, 126), inc(Precision, 85), inc(Ferocity, 85));
        add(map, Prefix.Berserker, Arrays.asList(TrinketSlot.EarLeft, TrinketSlot.EarRight), "Althea's Ashes", inc(Power, 110), inc(Precision, 74), inc(Ferocity, 74));
        add(map, Prefix.Minstrel, TrinketSlot.Back, "Icebrood Horn Backpack", inc(Toughness, 52), inc(HealingPower, 52), inc(Vitality, 27), inc(Concentration, 27));
        add(map, Prefix.Minstrel, TrinketSlot.Neck, "Talisman of Holt", inc(Toughness, 133), inc(HealingPower, 133), inc(Vitality, 71), inc(Concentration, 71));
        add(map, Prefix.Minstrel, Arrays.asList(TrinketSlot.FingerLeft, TrinketSlot.FingerRight), "Blood Ruby Band", inc(Toughness, 106), inc(HealingPower, 106), inc(Vitality, 56), inc(Concentration, 56));
        add(map, Prefix.Minstrel, Arrays.asList(TrinketSlot.EarLeft, TrinketSlot.EarRight), "Mist Talisman", inc(Toughness, 92), inc(HealingPower, 92), inc(Vitality, 49), inc(Concentration, 49));
        return map;
/*
            Apothecary("Veldrunner", "Ebonmane", false, increase(HealingPower, 141), increase(Toughness, 101), increase(ConditionDamage, 101)),
            Assassin("Saphir", "Soros", true, increase(Precision, 141), increase(Power, 101), increase(Ferocity, 101)),
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
            Marauder("Svaad", "Svaad", true, increase(Power, 121), increase(Precision, 121), increase(Vitality, 67), increase(Ferocity, 67)),
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
 */
    }

    private static void add(Map<TrinketKey,TrinketPiece> map, Prefix prefix, TrinketSlot slot, String name, CharacterAttributeModifier... modifiers) {
        map.put(new TrinketKey(slot, prefix), new TrinketPiece(name, slot).setModifiers(Arrays.asList(modifiers)));
    }

    private static void add(Map<TrinketKey,TrinketPiece> map, Prefix prefix, Collection<TrinketSlot> slots, String name, CharacterAttributeModifier... modifiers) {
        slots.forEach(slot -> map.put(new TrinketKey(slot, prefix), new TrinketPiece(name, slot).setModifiers(Arrays.asList(modifiers))));
    }

    private static CharacterAttributeModifier inc(CharacterAttribute key, int value) {
        return new CharacterAttributeModifier(key, value);
    }

    public TrinketPiece create(Prefix prefix, TrinketSlot slot) {
        TrinketPiece trinket = trinkets.get(new TrinketKey(slot, prefix));
        if (trinket == null) {
            throw new IllegalArgumentException("no trinket found with prefix '" + prefix + "' for slot '" + slot + "'");
        }
        return trinket.deepCopy();
    }

    /**
     * This key class helps in organizing a map.
     */
    public static class TrinketKey {

        private final TrinketSlot slot;
        private final Prefix prefix;

        public TrinketKey(TrinketSlot slot, Prefix prefix) {
            this.slot = slot;
            this.prefix = prefix;
        }

        public TrinketSlot getSlot() {
            return slot;
        }

        public Prefix getPrefix() {
            return prefix;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            TrinketKey that = (TrinketKey) o;
            return slot == that.slot &&
                prefix == that.prefix;
        }

        @Override
        public int hashCode() {
            return Objects.hash(slot, prefix);
        }
    }
}
