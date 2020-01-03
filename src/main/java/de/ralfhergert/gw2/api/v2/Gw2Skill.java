package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;
import java.util.List;

public class Gw2Skill {

    private int id;
    private String name;
    private String description;
    private URL icon;
    private String chatLink;
    private List<Gw2SkillCategory> categories;
    private List<Gw2Fact> facts;
    private List<Gw2SkillFlag> flags;
    private List<Gw2Fact> traitedFacts;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public URL getIcon() {
        return icon;
    }

    public void setIcon(URL icon) {
        this.icon = icon;
    }

    @JsonProperty("chat_link")
    public String getChatLink() {
        return chatLink;
    }

    public void setChatLink(String chatLink) {
        this.chatLink = chatLink;
    }

    public List<Gw2SkillCategory> getCategories() {
        return categories;
    }

    public void setCategories(List<Gw2SkillCategory> categories) {
        this.categories = categories;
    }

    public List<Gw2Fact> getFacts() {
        return facts;
    }

    public void setFacts(List<Gw2Fact> facts) {
        this.facts = facts;
    }

    public List<Gw2SkillFlag> getFlags() {
        return flags;
    }

    public void setFlags(List<Gw2SkillFlag> flags) {
        this.flags = flags;
    }

    @JsonProperty("traited_facts")
    public List<Gw2Fact> getTraitedFacts() {
        return traitedFacts;
    }

    public void setTraitedFacts(List<Gw2Fact> traitedFacts) {
        this.traitedFacts = traitedFacts;
    }
}
