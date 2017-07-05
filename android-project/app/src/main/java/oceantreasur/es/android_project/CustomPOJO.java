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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomPOJO that = (CustomPOJO) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (height != null ? !height.equals(that.height) : that.height != null) return false;
        if (mass != null ? !mass.equals(that.mass) : that.mass != null) return false;
        if (hairColor != null ? !hairColor.equals(that.hairColor) : that.hairColor != null)
            return false;
        if (skinColor != null ? !skinColor.equals(that.skinColor) : that.skinColor != null)
            return false;
        if (eyeColor != null ? !eyeColor.equals(that.eyeColor) : that.eyeColor != null)
            return false;
        if (birthYear != null ? !birthYear.equals(that.birthYear) : that.birthYear != null)
            return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (homeworld != null ? !homeworld.equals(that.homeworld) : that.homeworld != null)
            return false;
        if (films != null ? !films.equals(that.films) : that.films != null) return false;
        if (species != null ? !species.equals(that.species) : that.species != null) return false;
        if (vehicles != null ? !vehicles.equals(that.vehicles) : that.vehicles != null)
            return false;
        if (starships != null ? !starships.equals(that.starships) : that.starships != null)
            return false;
        if (created != null ? !created.equals(that.created) : that.created != null) return false;
        if (edited != null ? !edited.equals(that.edited) : that.edited != null) return false;
        return url != null ? url.equals(that.url) : that.url == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (height != null ? height.hashCode() : 0);
        result = 31 * result + (mass != null ? mass.hashCode() : 0);
        result = 31 * result + (hairColor != null ? hairColor.hashCode() : 0);
        result = 31 * result + (skinColor != null ? skinColor.hashCode() : 0);
        result = 31 * result + (eyeColor != null ? eyeColor.hashCode() : 0);
        result = 31 * result + (birthYear != null ? birthYear.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (homeworld != null ? homeworld.hashCode() : 0);
        result = 31 * result + (films != null ? films.hashCode() : 0);
        result = 31 * result + (species != null ? species.hashCode() : 0);
        result = 31 * result + (vehicles != null ? vehicles.hashCode() : 0);
        result = 31 * result + (starships != null ? starships.hashCode() : 0);
        result = 31 * result + (created != null ? created.hashCode() : 0);
        result = 31 * result + (edited != null ? edited.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CustomPOJO{" +
                "name='" + name + '\'' +
                ", height='" + height + '\'' +
                ", mass='" + mass + '\'' +
                ", hairColor='" + hairColor + '\'' +
                ", skinColor='" + skinColor + '\'' +
                ", eyeColor='" + eyeColor + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", gender='" + gender + '\'' +
                ", homeworld='" + homeworld + '\'' +
                ", films=" + films +
                ", species=" + species +
                ", vehicles=" + vehicles +
                ", starships=" + starships +
                ", created='" + created + '\'' +
                ", edited='" + edited + '\'' +
                ", url='" + url + '\'' +
                '}';
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