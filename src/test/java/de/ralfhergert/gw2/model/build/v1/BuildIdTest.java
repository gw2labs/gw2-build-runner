package de.ralfhergert.gw2.model.build.v1;

import de.ralfhergert.gw2.model.CharacterAttribute;
import de.ralfhergert.gw2.model.Gw2Character;
import de.ralfhergert.gw2.model.Profession;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * This test ensures that {@link BuildId} is working correctly.
 */
public class BuildIdTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAgainstNullBuildId() {
        thrown.expect(NullPointerException.class);
        new BuildId().createFrom(null);
    }

    @Test
    public void testAgainstEmptyBuildId() {
        thrown.expect(IllegalArgumentException.class);
        new BuildId().createFrom("");
    }

    @Test
    public void testAgainstFooString() {
        thrown.expect(IllegalArgumentException.class);
        new BuildId().createFrom("Foo");
    }

    @Test
    public void testWithMissingProfession() {
        thrown.expect(IllegalArgumentException.class);
        new BuildId().createFrom("GW2L1-something");
    }

    @Test
    public void testMinimalBuildIdForNakedGuardian() {
        Gw2Character character = new BuildId().createFrom("GW2L1-p:G");
        Assert.assertNotNull("character should not be null", character);
        Assert.assertEquals("character should be of profession", Profession.Guardian, character.getProfession());
    }

    /**
     * In this test a buildId with two profession-blocks are used, but both specify 'Mesmer'.
     * The profession choice should be accepted.
     */
    @Test
    public void testDuplicateProfessionDefinitionForNakedMesmer() {
        Gw2Character character = new BuildId().createFrom("GW2L1-p:M-p:Mes");
        Assert.assertNotNull("character should not be null", character);
        Assert.assertEquals("character should be of profession", Profession.Mesmer, character.getProfession());
        Assert.assertEquals("power should be", 1000, character.getAttributeValue(CharacterAttribute.Power));
        Assert.assertEquals("toughness should be", 1000, character.getAttributeValue(CharacterAttribute.Toughness));
        Assert.assertEquals("vitality should be", 1000, character.getAttributeValue(CharacterAttribute.Vitality));
        Assert.assertEquals("precision should be", 1000, character.getAttributeValue(CharacterAttribute.Precision));
        Assert.assertEquals("ferocity should be", 0, character.getAttributeValue(CharacterAttribute.Ferocity));
        Assert.assertEquals("armor should be", 1000, character.getAttributeValue(CharacterAttribute.Armor));
        Assert.assertEquals("health should be", 15922, character.getAttributeValue(CharacterAttribute.Health));
        Assert.assertEquals("CHC should be", 0.05, character.getAttributeValue(CharacterAttribute.CriticalChance));
        Assert.assertEquals("CHD should be", 1.5, character.getAttributeValue(CharacterAttribute.CriticalDamage));
        Assert.assertEquals("HealingPower should be", 0, character.getAttributeValue(CharacterAttribute.HealingPower));
        Assert.assertEquals("ConditionDuration should be", 0d, character.getAttributeValue(CharacterAttribute.ConditionDuration));
        Assert.assertEquals("ConditionDamage should be", 0, character.getAttributeValue(CharacterAttribute.ConditionDamage));
        Assert.assertEquals("Expertise should be", 0, character.getAttributeValue(CharacterAttribute.Expertise));
        Assert.assertEquals("Concentration should be", 0, character.getAttributeValue(CharacterAttribute.Concentration));
    }

    /**
     * Two professions start with an upper-case 'R': Ranger and Revenant,
     * though just specifying 'R' is not sufficient and should lead to an exception.
     */
    @Test
    public void testAgainstFuzzyProfessionDefinition() {
        thrown.expect(IllegalArgumentException.class);
        new BuildId().createFrom("GW2L1-p:R");
    }

    /**
     * Two professions start with an upper-case 'R': Ranger and Revenant,
     * though just specifying 'R' is not sufficient and should lead to an exception.
     */
    @Test
    public void testAgainstConflictingProfessionDefinitionsInBuildId() {
        thrown.expect(IllegalArgumentException.class);
        new BuildId().createFrom("GW2L1-p:Rev-p:Nec");
    }

    @Test
    public void testConfigureWarriorWithFullBerserkerArmor01() {
        Gw2Character warrior = new BuildId().createFrom("GW2L1-p:W-e:a:Berserker");
        Assert.assertEquals("profession should be", Profession.Warrior, warrior.getProfession());
        Assert.assertEquals("power should be", 1439, warrior.getAttributeValue(CharacterAttribute.Power));
        Assert.assertEquals("toughness should be", 1000, warrior.getAttributeValue(CharacterAttribute.Toughness));
        Assert.assertEquals("vitality should be", 1000, warrior.getAttributeValue(CharacterAttribute.Vitality));
        Assert.assertEquals("precision should be", 1315, warrior.getAttributeValue(CharacterAttribute.Precision));
        Assert.assertEquals("ferocity should be", 315, warrior.getAttributeValue(CharacterAttribute.Ferocity));
        Assert.assertEquals("armor should be", 2271, warrior.getAttributeValue(CharacterAttribute.Armor));
        Assert.assertEquals("health should be", 19212, warrior.getAttributeValue(CharacterAttribute.Health));
        Assert.assertEquals("CHC should be", 0.20, warrior.getAttributeValue(CharacterAttribute.CriticalChance));
        Assert.assertEquals("CHD should be", 1.71, warrior.getAttributeValue(CharacterAttribute.CriticalDamage));
        Assert.assertEquals("HealingPower should be", 0, warrior.getAttributeValue(CharacterAttribute.HealingPower));
        Assert.assertEquals("ConditionDuration should be", 0d, warrior.getAttributeValue(CharacterAttribute.ConditionDuration));
        Assert.assertEquals("ConditionDamage should be", 0, warrior.getAttributeValue(CharacterAttribute.ConditionDamage));
        Assert.assertEquals("Expertise should be", 0, warrior.getAttributeValue(CharacterAttribute.Expertise));
        Assert.assertEquals("Concentration should be", 0, warrior.getAttributeValue(CharacterAttribute.Concentration));
    }

    @Test
    public void testConfigureWarriorWithFullBerserkerArmor02() {
        Gw2Character warrior = new BuildId().createFrom("GW2L1-p:W-e:a:*=Berserker");
        Assert.assertEquals("profession should be", Profession.Warrior, warrior.getProfession());
        Assert.assertEquals("power should be", 1439, warrior.getAttributeValue(CharacterAttribute.Power));
        Assert.assertEquals("toughness should be", 1000, warrior.getAttributeValue(CharacterAttribute.Toughness));
        Assert.assertEquals("vitality should be", 1000, warrior.getAttributeValue(CharacterAttribute.Vitality));
        Assert.assertEquals("precision should be", 1315, warrior.getAttributeValue(CharacterAttribute.Precision));
        Assert.assertEquals("ferocity should be", 315, warrior.getAttributeValue(CharacterAttribute.Ferocity));
        Assert.assertEquals("armor should be", 2271, warrior.getAttributeValue(CharacterAttribute.Armor));
        Assert.assertEquals("health should be", 19212, warrior.getAttributeValue(CharacterAttribute.Health));
        Assert.assertEquals("CHC should be", 0.20, warrior.getAttributeValue(CharacterAttribute.CriticalChance));
        Assert.assertEquals("CHD should be", 1.71, warrior.getAttributeValue(CharacterAttribute.CriticalDamage));
        Assert.assertEquals("HealingPower should be", 0, warrior.getAttributeValue(CharacterAttribute.HealingPower));
        Assert.assertEquals("ConditionDuration should be", 0d, warrior.getAttributeValue(CharacterAttribute.ConditionDuration));
        Assert.assertEquals("ConditionDamage should be", 0, warrior.getAttributeValue(CharacterAttribute.ConditionDamage));
        Assert.assertEquals("Expertise should be", 0, warrior.getAttributeValue(CharacterAttribute.Expertise));
        Assert.assertEquals("Concentration should be", 0, warrior.getAttributeValue(CharacterAttribute.Concentration));
    }

    @Test
    public void testConfigureWarriorWithFullBerserkerArmor03() {
        Gw2Character warrior = new BuildId().createFrom("GW2L1-p:W-e:a:He,S,C,Ha,L,F=Berserker");
        Assert.assertEquals("profession should be", Profession.Warrior, warrior.getProfession());
        Assert.assertEquals("power should be", 1439, warrior.getAttributeValue(CharacterAttribute.Power));
        Assert.assertEquals("toughness should be", 1000, warrior.getAttributeValue(CharacterAttribute.Toughness));
        Assert.assertEquals("vitality should be", 1000, warrior.getAttributeValue(CharacterAttribute.Vitality));
        Assert.assertEquals("precision should be", 1315, warrior.getAttributeValue(CharacterAttribute.Precision));
        Assert.assertEquals("ferocity should be", 315, warrior.getAttributeValue(CharacterAttribute.Ferocity));
        Assert.assertEquals("armor should be", 2271, warrior.getAttributeValue(CharacterAttribute.Armor));
        Assert.assertEquals("health should be", 19212, warrior.getAttributeValue(CharacterAttribute.Health));
        Assert.assertEquals("CHC should be", 0.20, warrior.getAttributeValue(CharacterAttribute.CriticalChance));
        Assert.assertEquals("CHD should be", 1.71, warrior.getAttributeValue(CharacterAttribute.CriticalDamage));
        Assert.assertEquals("HealingPower should be", 0, warrior.getAttributeValue(CharacterAttribute.HealingPower));
        Assert.assertEquals("ConditionDuration should be", 0d, warrior.getAttributeValue(CharacterAttribute.ConditionDuration));
        Assert.assertEquals("ConditionDamage should be", 0, warrior.getAttributeValue(CharacterAttribute.ConditionDamage));
        Assert.assertEquals("Expertise should be", 0, warrior.getAttributeValue(CharacterAttribute.Expertise));
        Assert.assertEquals("Concentration should be", 0, warrior.getAttributeValue(CharacterAttribute.Concentration));
    }

    @Test
    public void testConfigureNecromancerWithFullCelestialArmor01() {
        Gw2Character necro = new BuildId().createFrom("GW2L1-p:N-e:a:Celestial");
        Assert.assertEquals("profession should be", Profession.Necromancer, necro.getProfession());
        Assert.assertEquals("power should be", 1207, necro.getAttributeValue(CharacterAttribute.Power));
        Assert.assertEquals("toughness should be", 1207, necro.getAttributeValue(CharacterAttribute.Toughness));
        Assert.assertEquals("vitality should be", 1207, necro.getAttributeValue(CharacterAttribute.Vitality));
        Assert.assertEquals("precision should be", 1207, necro.getAttributeValue(CharacterAttribute.Precision));
        Assert.assertEquals("ferocity should be", 207, necro.getAttributeValue(CharacterAttribute.Ferocity));
        Assert.assertEquals("armor should be", 2174, necro.getAttributeValue(CharacterAttribute.Armor));
        Assert.assertEquals("health should be", 21282, necro.getAttributeValue(CharacterAttribute.Health));
        Assert.assertEquals("CHC should be", 0.1486, necro.getAttributeValue(CharacterAttribute.CriticalChance, Double.class), 0.0001);
        Assert.assertEquals("CHD should be", 1.638, necro.getAttributeValue(CharacterAttribute.CriticalDamage));
        Assert.assertEquals("HealingPower should be", 207, necro.getAttributeValue(CharacterAttribute.HealingPower));
        Assert.assertEquals("ConditionDuration should be", 0d, necro.getAttributeValue(CharacterAttribute.ConditionDuration));
        Assert.assertEquals("ConditionDamage should be", 207, necro.getAttributeValue(CharacterAttribute.ConditionDamage));
        Assert.assertEquals("Expertise should be", 0, necro.getAttributeValue(CharacterAttribute.Expertise));
        Assert.assertEquals("Concentration should be", 0, necro.getAttributeValue(CharacterAttribute.Concentration));
    }

    @Test
    public void testConfigureDruidWithFullMinstrelArmorAndTrinkets01() {
        Gw2Character gw2Character = new BuildId().createFrom("GW2L1-p:Ra-e:a:Minstrel;t:Minstrel");
        Assert.assertEquals("profession should be", Profession.Ranger, gw2Character.getProfession());
        Assert.assertEquals("power should be", 1000, gw2Character.getAttributeValue(CharacterAttribute.Power));
        Assert.assertEquals("toughness should be", 1957, gw2Character.getAttributeValue(CharacterAttribute.Toughness));
        Assert.assertEquals("vitality should be", 1515, gw2Character.getAttributeValue(CharacterAttribute.Vitality));
        Assert.assertEquals("precision should be", 1000, gw2Character.getAttributeValue(CharacterAttribute.Precision));
        Assert.assertEquals("ferocity should be", 0, gw2Character.getAttributeValue(CharacterAttribute.Ferocity));
        Assert.assertEquals("armor should be", 3075, gw2Character.getAttributeValue(CharacterAttribute.Armor));
        Assert.assertEquals("health should be", 21072, gw2Character.getAttributeValue(CharacterAttribute.Health));
        Assert.assertEquals("CHC should be", 0.05, gw2Character.getAttributeValue(CharacterAttribute.CriticalChance, Double.class), 0.0001);
        Assert.assertEquals("CHD should be", 1.5, gw2Character.getAttributeValue(CharacterAttribute.CriticalDamage));
        Assert.assertEquals("HealingPower should be", 957, gw2Character.getAttributeValue(CharacterAttribute.HealingPower));
        Assert.assertEquals("ConditionDuration should be", 0d, gw2Character.getAttributeValue(CharacterAttribute.ConditionDuration));
        Assert.assertEquals("ConditionDamage should be", 0, gw2Character.getAttributeValue(CharacterAttribute.ConditionDamage));
        Assert.assertEquals("Expertise should be", 0, gw2Character.getAttributeValue(CharacterAttribute.Expertise));
        Assert.assertEquals("Concentration should be", 515, gw2Character.getAttributeValue(CharacterAttribute.Concentration));
        Assert.assertEquals("BoonDuration should be", 0.3433, gw2Character.getAttributeValue(CharacterAttribute.BoonDuration, Double.class), 0.0001);
    }
}
