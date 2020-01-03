package de.ralfhergert.gw2.generator;

import de.ralfhergert.gw2.model.*;
import de.ralfhergert.gw2.modifier.CharacterAttributeModifier;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * This test ensures {@link ArmorPieceGenerator} to work correctly.
 */
public class ArmorPieceGeneratorTest {

    @Test
    public void generateHeavyAscendedZojjaVisor() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Berserker, ArmorClass.Heavy, ArmorSlot.Head);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Zojja", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Heavy, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 127),
            new CharacterAttributeModifier(CharacterAttribute.Power, 63),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 45),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 45)
        ), piece.getModifiers());
    }

    @Test
    public void generateHeavyAscendedZojjaPauldrons() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Berserker, ArmorClass.Heavy, ArmorSlot.Shoulders);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Zojja", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Heavy, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 127),
            new CharacterAttributeModifier(CharacterAttribute.Power, 47),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 34),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 34)
        ), piece.getModifiers());
    }

    @Test
    public void generateHeavyAscendedZojjaBreastplate() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Berserker, ArmorClass.Heavy, ArmorSlot.Chest);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Zojja", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Heavy, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 381),
            new CharacterAttributeModifier(CharacterAttribute.Power, 141),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 101),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 101)
        ), piece.getModifiers());
    }

    @Test
    public void generateHeavyAscendedZojjaWarfists() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Berserker, ArmorClass.Heavy, ArmorSlot.Hands);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Zojja", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Heavy, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 191),
            new CharacterAttributeModifier(CharacterAttribute.Power, 47),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 34),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 34)
        ), piece.getModifiers());
    }

    @Test
    public void generateHeavyAscendedZojjaTassets() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Berserker, ArmorClass.Heavy, ArmorSlot.Legs);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Zojja", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Heavy, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 254),
            new CharacterAttributeModifier(CharacterAttribute.Power, 94),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 67),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 67)
        ), piece.getModifiers());
    }

    @Test
    public void generateHeavyAscendedZojjaGreaves() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Berserker, ArmorClass.Heavy, ArmorSlot.Feet);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Zojja", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Heavy, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 191),
            new CharacterAttributeModifier(CharacterAttribute.Power, 47),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 34),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 34)
        ), piece.getModifiers());
    }

    @Test
    public void generateMediumAscendedSvaardVisor() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Marauder, ArmorClass.Medium, ArmorSlot.Head);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Svaard", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Medium, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 102),
            new CharacterAttributeModifier(CharacterAttribute.Power, 54),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 54),
            new CharacterAttributeModifier(CharacterAttribute.Vitality, 30),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 30)
        ), piece.getModifiers());
    }

    @Test
    public void generateMediumAscendedSvaardPauldrons() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Marauder, ArmorClass.Medium, ArmorSlot.Shoulders);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Svaard", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Medium, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 102),
            new CharacterAttributeModifier(CharacterAttribute.Power, 40),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 40),
            new CharacterAttributeModifier(CharacterAttribute.Vitality, 22),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 22)
        ), piece.getModifiers());
    }

    @Test
    public void generateMediumAscendedSvaardGuise() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Marauder, ArmorClass.Medium, ArmorSlot.Chest);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Svaard", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Medium, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 355),
            new CharacterAttributeModifier(CharacterAttribute.Power, 121),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 121),
            new CharacterAttributeModifier(CharacterAttribute.Vitality, 67),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 67)
        ), piece.getModifiers());
    }

    @Test
    public void generateMediumAscendedSvaardGrips() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Marauder, ArmorClass.Medium, ArmorSlot.Hands);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Svaard", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Medium, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 165),
            new CharacterAttributeModifier(CharacterAttribute.Power, 40),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 40),
            new CharacterAttributeModifier(CharacterAttribute.Vitality, 22),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 22)
        ), piece.getModifiers());
    }

    @Test
    public void generateMediumAscendedSvaardLeggins() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Marauder, ArmorClass.Medium, ArmorSlot.Legs);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Svaard", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Medium, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 229),
            new CharacterAttributeModifier(CharacterAttribute.Power, 81),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 81),
            new CharacterAttributeModifier(CharacterAttribute.Vitality, 44),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 44)
        ), piece.getModifiers());
    }

    @Test
    public void generateMediumAscendedSvaardStriders() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Marauder, ArmorClass.Medium, ArmorSlot.Feet);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Svaard", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Medium, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 165),
            new CharacterAttributeModifier(CharacterAttribute.Power, 40),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 40),
            new CharacterAttributeModifier(CharacterAttribute.Vitality, 22),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 22)
        ), piece.getModifiers());
    }

    @Test
    public void generateLightAscendedWupwupMasque() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Celestial, ArmorClass.Light, ArmorSlot.Head);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Wupwup", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Light, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 77),
            new CharacterAttributeModifier(CharacterAttribute.Power, 30),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 30),
            new CharacterAttributeModifier(CharacterAttribute.Toughness, 30),
            new CharacterAttributeModifier(CharacterAttribute.Vitality, 30),
            new CharacterAttributeModifier(CharacterAttribute.ConditionDamage, 30),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 30),
            new CharacterAttributeModifier(CharacterAttribute.HealingPower, 30)
        ), piece.getModifiers());
    }

    @Test
    public void generateLightAscendedWupwupEpaulets() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Celestial, ArmorClass.Light, ArmorSlot.Shoulders);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Wupwup", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Light, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 77),
            new CharacterAttributeModifier(CharacterAttribute.Power, 22),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 22),
            new CharacterAttributeModifier(CharacterAttribute.Toughness, 22),
            new CharacterAttributeModifier(CharacterAttribute.Vitality, 22),
            new CharacterAttributeModifier(CharacterAttribute.ConditionDamage, 22),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 22),
            new CharacterAttributeModifier(CharacterAttribute.HealingPower, 22)
        ), piece.getModifiers());
    }

    @Test
    public void generateLightAscendedWupwupDoublet() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Celestial, ArmorClass.Light, ArmorSlot.Chest);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Wupwup", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Light, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 330),
            new CharacterAttributeModifier(CharacterAttribute.Power, 67),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 67),
            new CharacterAttributeModifier(CharacterAttribute.Toughness, 67),
            new CharacterAttributeModifier(CharacterAttribute.Vitality, 67),
            new CharacterAttributeModifier(CharacterAttribute.ConditionDamage, 67),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 67),
            new CharacterAttributeModifier(CharacterAttribute.HealingPower, 67)
        ), piece.getModifiers());
    }

    @Test
    public void generateLightAscendedWupwupWristguards() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Celestial, ArmorClass.Light, ArmorSlot.Hands);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Wupwup", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Light, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 140),
            new CharacterAttributeModifier(CharacterAttribute.Power, 22),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 22),
            new CharacterAttributeModifier(CharacterAttribute.Toughness, 22),
            new CharacterAttributeModifier(CharacterAttribute.Vitality, 22),
            new CharacterAttributeModifier(CharacterAttribute.ConditionDamage, 22),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 22),
            new CharacterAttributeModifier(CharacterAttribute.HealingPower, 22)
        ), piece.getModifiers());
    }

    @Test
    public void generateLightAscendedWupwupBreeches() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Celestial, ArmorClass.Light, ArmorSlot.Legs);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Wupwup", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Light, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 203),
            new CharacterAttributeModifier(CharacterAttribute.Power, 44),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 44),
            new CharacterAttributeModifier(CharacterAttribute.Toughness, 44),
            new CharacterAttributeModifier(CharacterAttribute.Vitality, 44),
            new CharacterAttributeModifier(CharacterAttribute.ConditionDamage, 44),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 44),
            new CharacterAttributeModifier(CharacterAttribute.HealingPower, 44)
        ), piece.getModifiers());
    }

    @Test
    public void generateLightAscendedWupwupFootwear() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Celestial, ArmorClass.Light, ArmorSlot.Feet);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Wupwup", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Light, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 140),
            new CharacterAttributeModifier(CharacterAttribute.Power, 22),
            new CharacterAttributeModifier(CharacterAttribute.Precision, 22),
            new CharacterAttributeModifier(CharacterAttribute.Toughness, 22),
            new CharacterAttributeModifier(CharacterAttribute.Vitality, 22),
            new CharacterAttributeModifier(CharacterAttribute.ConditionDamage, 22),
            new CharacterAttributeModifier(CharacterAttribute.Ferocity, 22),
            new CharacterAttributeModifier(CharacterAttribute.HealingPower, 22)
        ), piece.getModifiers());
    }

    @Test
    public void generateMediumAscendedMaklainLegs() {
        ArmorPiece piece = new ArmorPieceGenerator().create(Prefix.Minstrel, ArmorClass.Medium, ArmorSlot.Legs);
        Assert.assertNotNull("armor piece should not be null", piece);
        Assert.assertEquals("armor piece name", "Maklain", piece.getName());
        Assert.assertEquals("armor piece type", Equipment.Type.Armor, piece.getType());
        Assert.assertEquals("armor piece class", ArmorClass.Medium, piece.getArmorClass());
        Assert.assertEquals("armor piece modifiers", Arrays.asList(
            new CharacterAttributeModifier(CharacterAttribute.Defense, 229),
            new CharacterAttributeModifier(CharacterAttribute.Toughness, 81),
            new CharacterAttributeModifier(CharacterAttribute.HealingPower, 81),
            new CharacterAttributeModifier(CharacterAttribute.Vitality, 44),
            new CharacterAttributeModifier(CharacterAttribute.Concentration, 44)
        ), piece.getModifiers());
    }
}
