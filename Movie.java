import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("Title")
    private String name;
    @SerializedName("Year")
    private int launch;
    @SerializedName("Runtime")
    private int duration;  // Alterado para String

    public Movie(OmdbInfosTitles omdbInfos) {
        name = omdbInfos.title();
        launch = Integer.valueOf(omdbInfos.year());
        duration = Integer.valueOf(omdbInfos.runtime().substring(0, 2)); //substring irá pegar apenas os primeiros 3 valores começando pela posição 0, da informação json
    }

    String getName() {
        return name;
    }

    int getLaunch() {
        return launch;
    }

    int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Título: " + name + "\nAno de lançamento: " + launch + "\nDuração: " + duration;
    }
}