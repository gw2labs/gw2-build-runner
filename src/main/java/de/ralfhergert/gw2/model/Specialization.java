package de.ralfhergert.gw2.model;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum Specialization {
    Zeal(42, Profession.Guardian, false, "Zeal"),
    Radiance(16, Profession.Guardian, false, "Radiance"),
    Valor(13, Profession.Guardian, false, "Valor"),
    Honor(49, Profession.Guardian, false, "Honor"),
    Virtues(46, Profession.Guardian, false, "Virtues"),
    Dragonhunter(27, Profession.Guardian, true, "Dragonhunter"),
    Firebrand(62, Profession.Guardian, true, "Firebrand"),
    Strength(4, Profession.Warrior, false, "Strength"),
    Arms(36, Profession.Warrior, false, "Arms"),
    Defense(22, Profession.Warrior, false, "Defense"),
    Tactics(11, Profession.Warrior, false, "Tactics"),
    Discipline(51, Profession.Warrior, false, "Discipline"),
    Berserker(18, Profession.Warrior, true, "Berserker"),
    Spellbreaker(61, Profession.Warrior, true, "Spellbreaker"),
    Explosives(6, Profession.Engineer, false, "Explosives"),
    Firearms(38, Profession.Engineer, false, "Firearms"),
    Inventions(47, Profession.Engineer, false, "Inventions"),
    Alchemy(29, Profession.Engineer, false, "Alchemy"),
    Tools(21, Profession.Engineer, false, "Tools"),
    Scrapper(43, Profession.Engineer, true, "Scrapper"),
    Holosmith(57, Profession.Engineer, true, "Holosmith"),
    Marksmanship(8, Profession.Ranger, false, "Marksmanship"),
    Skirmishing(30, Profession.Ranger, false, "Skirmishing"),
    WildernessSurvival(33, Profession.Ranger, false, "Wilderness Survival"),
    NatureMagic(25, Profession.Ranger, false, "Nature Magic"),
    Beastmastery(32, Profession.Ranger, false, "Beastmastery"),
    Druid(5, Profession.Ranger, true, "Druid"),
    Soulbeast(55, Profession.Ranger, true, "Soulbeast"),
    DeadlyArts(28, Profession.Thief, false, "Deadly Arts"),
    CriticalStrikes(35, Profession.Thief, false, "Critical Strikes"),
    ShadowArts(20, Profession.Thief, false, "Shadow Arts"),
    Acrobatics(54, Profession.Thief, false, "Acrobatics"),
    Trickery(44, Profession.Thief, false, "Trickery"),
    Daredevil(7, Profession.Thief, true, "Daredevil"),
    Deadeye(58, Profession.Thief, true, "Deadeye"),
    Fire(31, Profession.Elementalist, false, "Fire"),
    Air(41, Profession.Elementalist, false, "Air"),
    Earth(26, Profession.Elementalist, false, "Earth"),
    Water(17, Profession.Elementalist, false, "Water"),
    Arcane(37, Profession.Elementalist, false, "Arcane"),
    Tempest(48, Profession.Elementalist, true, "Tempest"),
    Weaver(56, Profession.Elementalist, true, "Weaver"),
    Domination(10, Profession.Mesmer, false, "Domination"),
    Dueling(1, Profession.Mesmer, false, "Dueling"),
    Chaos(45, Profession.Mesmer, false, "Chaos"),
    Inspiration(23, Profession.Mesmer, false, "Inspiration"),
    Illusions(24, Profession.Mesmer, false, "Illusions"),
    Chronomancer(40, Profession.Mesmer, true, "Chronomancer"),
    Mirage(59, Profession.Mesmer, true, "Mirage"),
    Spite(53, Profession.Necromancer, false, "Spite"),
    Curses(39, Profession.Necromancer, false, "Curses"),
    DeathMagic(2, Profession.Necromancer, false, "Death Magic"),
    BloodMagic(19, Profession.Necromancer, false, "Blood Magic"),
    SoulReaping(50, Profession.Necromancer, false, "Soul Reaping"),
    Reaper(34, Profession.Necromancer, true, "Reaper"),
    Scourge(60, Profession.Necromancer, true, "Scourge"),
    Corruption(14, Profession.Revenant, false, "Corruption"),
    Retribution(9, Profession.Revenant, false, "Retribution"),
    Salvation(12, Profession.Revenant, false, "Salvation"),
    Invocation(3, Profession.Revenant, false, "Invocation"),
    Devastation(15, Profession.Revenant, false, "Devastation"),
    Herald(52, Profession.Revenant, true, "Herald"),
    Renegade(63, Profession.Revenant, true, "Renegade");

    private static final Map<Integer,Specialization> idLookupMap = new HashMap<>();

    private int id;
    private Profession profession;
    private boolean isElite;
    private String name;

    Specialization(int id, Profession profession, boolean isElite, String name) {
        this.id = id;
        this.profession = profession;
        this.isElite = isElite;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Profession getProfession() {
        return profession;
    }

    public boolean isElite() {
        return isElite;
    }

    public String getName() {
        return name;
    }

    public static Specialization getById(int id) {
        return idLookupMap.computeIfAbsent(id, Specialization::findById);
    }

    private static Specialization findById(int id) {
        return Stream.of(values())
            .filter(specialization -> id == specialization.id)
            .findFirst()
            .orElse(null);
    }

    public static Stream<Specialization> getAllFor(Profession profession) {
        return Stream.of(values())
            .filter(specialization -> specialization.profession == profession);
    }
}
