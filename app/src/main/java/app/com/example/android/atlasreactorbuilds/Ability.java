package app.com.example.android.atlasreactorbuilds;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.IntDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fábio on 19/05/2017.
 */

public class Ability implements Parcelable{

    //Declarando constantes que definem a fase das habilidades
    public static final int PREP_PHASE = 0;
    public static final int DASH_PHASE = 1;
    public static final int BLAST_PHASE = 2;

    @Retention(RetentionPolicy.SOURCE) @IntDef({PREP_PHASE , DASH_PHASE , BLAST_PHASE})

    public @interface AbilityPhase{}

    private String name;
    private int cooldown;
    private String description;
    private int damage;
    @AbilityPhase
    private int phase;
    private String energy;
    private boolean freeAction;
    private List<Mod> mods;

    public Ability(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @AbilityPhase
    public int getPhase() {
        return phase;
    }

    public void setPhase(@AbilityPhase int phase) {
        this.phase = phase;
    }

    public boolean isFreeAction() {
        return freeAction;
    }

    public void setFreeAction(boolean freeAction) {
        this.freeAction = freeAction;
    }

    public List<Mod> getMods() {
        return new ArrayList<Mod>(this.mods);
    }

    public void setMods(List<Mod> mods) {

        this.mods = new ArrayList<Mod>(mods);
    }

    public String getEnergy() {
        return energy;
    }

    public void setEnergy(String energy) {
        this.energy = energy;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(cooldown);
        dest.writeString(description);
        dest.writeInt(damage);
        dest.writeInt(phase);
        dest.writeString(energy);
        dest.writeByte((byte) (freeAction ? 1 : 0));
        dest.writeTypedList(mods);
    }

    protected Ability(Parcel in) {
        name = in.readString();
        cooldown = in.readInt();
        description = in.readString();
        damage = in.readInt();

        if(in.readInt() == 0)
            phase = Ability.PREP_PHASE;
        else if(in.readInt() == 1)
            phase = Ability.DASH_PHASE;
        if(in.readInt() == 2)
            phase = Ability.BLAST_PHASE;

        energy = in.readString();
        freeAction = in.readByte() != 0;
        mods = in.createTypedArrayList(Mod.CREATOR);
    }

    public static final Creator<Ability> CREATOR = new Creator<Ability>() {
        @Override
        public Ability createFromParcel(Parcel in) {
            return new Ability(in);
        }

        @Override
        public Ability[] newArray(int size) {
            return new Ability[size];
        }
    };
}
