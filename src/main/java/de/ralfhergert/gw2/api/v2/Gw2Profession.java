package de.ralfhergert.gw2.api.v2;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.ralfhergert.gw2.model.Attunement;
import de.ralfhergert.gw2.model.Profession;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties("training")
public class Gw2Profession {

    public static class Weapon {

        private int specialization;
        private List<Gw2WeaponFlag> flags;
        private List<Skill> skills;

        public int getSpecialization() {
            return specialization;
        }

        public void setSpecialization(int specialization) {
            this.specialization = specialization;
        }

        public List<Gw2WeaponFlag> getFlags() {
            return flags;
        }

        public void setFlags(List<Gw2WeaponFlag> flags) {
            this.flags = flags;
        }

        public List<Skill> getSkills() {
            return skills;
        }

        public void setSkills(List<Skill> skills) {
            this.skills = skills;
        }
    }

    public static class Skill {

        private int id;
        private Gw2Slot slot;
        private String type;
        private String offhand;
        private Profession source;
        private Attunement attunement;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Gw2Slot getSlot() {
            return slot;
        }

        public void setSlot(Gw2Slot slot) {
            this.slot = slot;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOffhand() {
            return offhand;
        }

        public void setOffhand(String offhand) {
            this.offhand = offhand;
        }

        public Profession getSource() {
            return source;
        }

        public void setSource(Profession source) {
            this.source = source;
        }

        public Attunement getAttunement() {
            return attunement;
        }

        public void setAttunement(Attunement attunement) {
            this.attunement = attunement;
        }
    }

    private String id;
    private String name;
    private URL icon;
    private URL iconBig;
    private List<Integer> specializations;
    private Map<String,Weapon> weapons = new HashMap<>();
    private List<Gw2ProfessionFlag> flags;
    private List<Skill> skills;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URL getIcon() {
        return icon;
    }

    public void setIcon(URL icon) {
        this.icon = icon;
    }

    @JsonProperty("icon_big")
    public URL getIconBig() {
        return iconBig;
    }

    public void setIconBig(URL iconBig) {
        this.iconBig = iconBig;
    }

    public List<Integer> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(List<Integer> specializations) {
        this.specializations = specializations;
    }

    public Map<String, Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(Map<String, Weapon> weapons) {
        this.weapons = weapons;
    }

    public List<Gw2ProfessionFlag> getFlags() {
        return flags;
    }

    public void setFlags(List<Gw2ProfessionFlag> flags) {
        this.flags = flags;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
