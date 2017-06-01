package app.com.example.android.atlasreactorbuilds;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Fábio on 19/05/2017.
 */

public class Freelancer implements Parcelable{

    private String name;
    private int health;
    private Ability ability1;
    private Ability ability2;
    private Ability ability3;
    private Ability ability4;
    private Ability ability5;
    private String role;
    private String bio;
    private String affiliation;
    //private Drawable icon;

    public Freelancer (){

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ability getAbility1() {
        return ability1;
    }

    public void setAbility1(Ability ability1) {
        this.ability1 = ability1;
    }

    public Ability getAbility2() {
        return ability2;
    }

    public void setAbility2(Ability ability2) {
        this.ability2 = ability2;
    }

    public Ability getAbility3() {
        return ability3;
    }

    public void setAbility3(Ability ability3) {
        this.ability3 = ability3;
    }

    public Ability getAbility4() {
        return ability4;
    }

    public void setAbility4(Ability ability4) {
        this.ability4 = ability4;
    }

    public Ability getAbility5() {
        return ability5;
    }

    public void setAbility5(Ability ability5) {
        this.ability5 = ability5;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeParcelable(ability1, flags);
        dest.writeParcelable(ability2, flags);
        dest.writeParcelable(ability3, flags);
        dest.writeParcelable(ability4, flags);
        dest.writeParcelable(ability5, flags);
        dest.writeString(role);
        dest.writeString(bio);
    }


    protected Freelancer(Parcel in) {
        name = in.readString();
        ability1 = in.readParcelable(Ability.class.getClassLoader());
        ability2 = in.readParcelable(Ability.class.getClassLoader());
        ability3 = in.readParcelable(Ability.class.getClassLoader());
        ability4 = in.readParcelable(Ability.class.getClassLoader());
        ability5 = in.readParcelable(Ability.class.getClassLoader());
        role = in.readString();
        bio = in.readString();
    }

    public static final Creator<Freelancer> CREATOR = new Creator<Freelancer>() {
        @Override
        public Freelancer createFromParcel(Parcel in) {
            return new Freelancer(in);
        }

        @Override
        public Freelancer[] newArray(int size) {
            return new Freelancer[size];
        }
    };
}
