package sample;

public class song {

    //song info would be read from metadata in the files downloaded from the online song database
    public String name;
    public String artist;
    public String genre;
    public String filename;

    public float runtime;


    public song(String name, String artist, String genre, String filename, float runtime){
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.filename = filename;
        this.runtime = runtime;
    }

    public String getName(){
        return this.name;
    }

    public String getArtist(){
        return this.artist;
    }

    public String getGenre(){
        return this.genre;
    }

    public String getFilename(){
        return this.filename;
    }

    public float getRuntime(){
        return this.runtime;
    }


}
