package oceantreasur.es.android_project;

import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomPOJO implements Parcelable
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("height")
    @Expose
    private String height;
    @SerializedName("mass")
    @Expose
    private String mass;
    @SerializedName("hair_color")
    @Expose
    private String hairColor;
    @SerializedName("skin_color")
    @Expose
    private String skinColor;
    @SerializedName("eye_color")
    @Expose
    private String eyeColor;
    @SerializedName("birth_year")
    @Expose
    private String birthYear;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("homeworld")
    @Expose
    private String homeworld;
    @SerializedName("films")
    @Expose
    private List<String> films = null;
    @SerializedName("species")
    @Expose
    private List<String> species = null;
    @SerializedName("vehicles")
    @Expose
    private List<Object> vehicles = null;
    @SerializedName("starships")
    @Expose
    private List<Object> starships = null;
    @SerializedName("created")
    @Expose
    private String created;
    @SerializedName("edited")
    @Expose
    private String edited;
    @SerializedName("url")
    @Expose
    private String url;
    public final static Parcelable.Creator<CustomPOJO> CREATOR = new Creator<CustomPOJO>() {


        @SuppressWarnings({
                "unchecked"
        })
        public CustomPOJO createFromParcel(Parcel in) {
            CustomPOJO instance = new CustomPOJO();
            instance.name = ((String) in.readValue((String.class.getClassLoader())));
            instance.height = ((String) in.readValue((String.class.getClassLoader())));
            instance.mass = ((String) in.readValue((String.class.getClassLoader())));
            instance.hairColor = ((String) in.readValue((String.class.getClassLoader())));
            instance.skinColor = ((String) in.readValue((String.class.getClassLoader())));
            instance.eyeColor = ((String) in.readValue((String.class.getClassLoader())));
            instance.birthYear = ((String) in.readValue((String.class.getClassLoader())));
            instance.gender = ((String) in.readValue((String.class.getClassLoader())));
            instance.homeworld = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.films, (java.lang.String.class.getClassLoader()));
            in.readList(instance.species, (java.lang.String.class.getClassLoader()));
            in.readList(instance.vehicles, (java.lang.Object.class.getClassLoader()));
            in.readList(instance.starships, (java.lang.Object.class.getClassLoader()));
            instance.created = ((String) in.readValue((String.class.getClassLoader())));
            instance.edited = ((String) in.readValue((String.class.getClassLoader())));
            instance.url = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public CustomPOJO[] newArray(int size) {
            return (new CustomPOJO[size]);
        }

    };

    public CustomPOJO() {
    }

    CustomPOJO(String name, String height, String mass, String hairColor, String skinColor, String eyeColor, String birthYear, String gender, String homeworld, List<String> films, List<String> species, List<Object> vehicles, List<Object> starships, String created, String edited, String url) {
        super();
        this.name = name;
        this.height = height;
        this.mass = mass;
        this.hairColor = hairColor;
        this.skinColor = skinColor;
        this.eyeColor = eyeColor;
        this.birthYear = birthYear;
        this.gender = gender;
        this.homeworld = homeworld;
        this.films = films;
        this.species = species;
        this.vehicles = vehicles;
        this.starships = starships;
        this.created = created;
        this.edited = edited;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public List<String> getFilms() {
        return films;
    }

    public void setFilms(List<String> films) {
        this.films = films;
    }

    public List<String> getSpecies() {
        return species;
    }

    public void setSpecies(List<String> species) {
        this.species = species;
    }

    public List<Object> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Object> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Object> getStarships() {
        return starships;
    }

    public void setStarships(List<Object> starships) {
        this.starships = starships;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getEdited() {
        return edited;
    }

    public void setEdited(String edited) {
        this.edited = edited;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(name).append(height).append(mass).append(hairColor).append(skinColor).append(eyeColor).append(birthYear).append(gender).append(homeworld).append(films).append(species).append(vehicles).append(starships).append(created).append(edited).append(url).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof CustomPOJO) == false) {
            return false;
        }
        CustomPOJO rhs = ((CustomPOJO) other);
        return new EqualsBuilder().append(name, rhs.name).append(height, rhs.height).append(mass, rhs.mass).append(hairColor, rhs.hairColor).append(skinColor, rhs.skinColor).append(eyeColor, rhs.eyeColor).append(birthYear, rhs.birthYear).append(gender, rhs.gender).append(homeworld, rhs.homeworld).append(films, rhs.films).append(species, rhs.species).append(vehicles, rhs.vehicles).append(starships, rhs.starships).append(created, rhs.created).append(edited, rhs.edited).append(url, rhs.url).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(name);
        dest.writeValue(height);
        dest.writeValue(mass);
        dest.writeValue(hairColor);
        dest.writeValue(skinColor);
        dest.writeValue(eyeColor);
        dest.writeValue(birthYear);
        dest.writeValue(gender);
        dest.writeValue(homeworld);
        dest.writeList(films);
        dest.writeList(species);
        dest.writeList(vehicles);
        dest.writeList(starships);
        dest.writeValue(created);
        dest.writeValue(edited);
        dest.writeValue(url);
    }

    public int describeContents() {
        return 0;
    }

}