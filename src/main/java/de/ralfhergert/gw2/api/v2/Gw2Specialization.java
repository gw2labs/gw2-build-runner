package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.ralfhergert.gw2.model.Profession;

import java.net.URL;
import java.util.List;

public class Gw2Specialization {

    private int id;
    private String name;
    private Profession profession;
    private boolean isElite;
    private List<Integer> minorTraits;
    private List<Integer> majorTraits;
    private int weaponTrait;
    private URL icon;
    private URL background;
    private URL professionIcon;
    private URL professionIconBig;

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

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public boolean isElite() {
        return isElite;
    }

    public void setElite(boolean elite) {
        isElite = elite;
    }

    @JsonProperty("minor_traits")
    public List<Integer> getMinorTraits() {
        return minorTraits;
    }

    public void setMinorTraits(List<Integer> minorTraits) {
        this.minorTraits = minorTraits;
    }

    @JsonProperty("major_traits")
    public List<Integer> getMajorTraits() {
        return majorTraits;
    }

    public void setMajorTraits(List<Integer> majorTraits) {
        this.majorTraits = majorTraits;
    }

    @JsonProperty("weapon_trait")
    public int getWeaponTrait() {
        return weaponTrait;
    }

    public void setWeaponTrait(int weaponTrait) {
        this.weaponTrait = weaponTrait;
    }

    public URL getIcon() {
        return icon;
    }

    public void setIcon(URL icon) {
        this.icon = icon;
    }

    public URL getBackground() {
        return background;
    }

    public void setBackground(URL background) {
        this.background = background;
    }

    @JsonProperty("profession_icon")
    public URL getProfessionIcon() {
        return professionIcon;
    }

    public void setProfessionIcon(URL professionIcon) {
        this.professionIcon = professionIcon;
    }

    @JsonProperty("profession_icon_big")
    public URL getProfessionIconBig() {
        return professionIconBig;
    }

    public void setProfessionIconBig(URL professionIconBig) {
        this.professionIconBig = professionIconBig;
    }
}
