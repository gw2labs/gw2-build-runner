package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.ralfhergert.gw2.model.TraitSlot;

import java.net.URL;
import java.util.List;

public class Gw2Trait {

    private int id;
    private int tier;
    private int order;
    private String name;
    private String description;
    private TraitSlot slot;
    private int specialization;
    private URL icon;
    private List<Gw2Fact> facts;
    private List<Gw2Skill> skills;
    private List<Gw2Fact> traitedFacts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TraitSlot getSlot() {
        return slot;
    }

    public void setSlot(TraitSlot slot) {
        this.slot = slot;
    }

    public int getSpecialization() {
        return specialization;
    }

    public void setSpecialization(int specialization) {
        this.specialization = specialization;
    }

    public URL getIcon() {
        return icon;
    }

    public void setIcon(URL icon) {
        this.icon = icon;
    }

    @JsonIgnore
    public List<Gw2Fact> getFacts() {
        return facts;
    }

    public void setFacts(List<Gw2Fact> facts) {
        this.facts = facts;
    }

    public List<Gw2Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Gw2Skill> skills) {
        this.skills = skills;
    }

    @JsonProperty("traited_facts")
    public List<Gw2Fact> getTraitedFacts() {
        return traitedFacts;
    }

    public void setTraitedFacts(List<Gw2Fact> traitedFacts) {
        this.traitedFacts = traitedFacts;
    }
}
