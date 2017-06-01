package app.com.example.android.atlasreactorbuilds;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Fábio on 20/05/2017.
 * Classe que representa as modificações que podem ser aplicadas as habilidades dos freelancers
 */

class Mod implements Parcelable{
    private String name;
    private String description;
    private int value;

    Mod(){
        this.name = null;
        this.description = null;
        this.value = 0;
    }

    public Mod(String name , String description , int value){
        this.name = name;
        this.description = description;
        this.value = value;
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

    void setDescription(String description) {
        this.description = description;
    }

    int getValue() {
        return value;
    }

    void setValue(int value) {
        this.value = value;
    }

    private Mod(Parcel in) {
        name = in.readString();
        description = in.readString();
        value = in.readInt();
    }

    public static final Creator<Mod> CREATOR = new Creator<Mod>() {
        @Override
        public Mod createFromParcel(Parcel in) {
            return new Mod(in);
        }

        @Override
        public Mod[] newArray(int size) {
            return new Mod[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(description);
        dest.writeInt(value);
    }


}
