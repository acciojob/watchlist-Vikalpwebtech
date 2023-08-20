package com.driver;

import org.springframework.stereotype.Repository;

import javax.servlet.http.PushBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String, Movie> movierepo;
    HashMap<String, Director> directorrepo;
    //Directorname and List of movies
    HashMap<String, List<String>> direcmoviespair = new HashMap<String, List<String>>();

    public MovieRepository() {
        this.movierepo = new HashMap<>();
        this.directorrepo = new HashMap<>();
        this.direcmoviespair = new HashMap<>();
    }

    public void savemovie(Movie movie) {
        movierepo.put(movie.getName(), movie);
    }

    public void savedirector(Director director) {
        directorrepo.put(director.getName(), director);
    }

    public void pairDirectorMovie(String moviename, String directorname) {
        //Condition is that moviename should exists in movierepo and directorname should exists in directorrepo
        if (movierepo.containsKey(moviename) && directorrepo.containsKey(directorname)) {
            List<String> currmoviesbydirector = new ArrayList<>();
            if (direcmoviespair.containsKey(directorname))
                currmoviesbydirector = direcmoviespair.get(directorname);
            currmoviesbydirector.add(moviename);
            direcmoviespair.put(directorname, currmoviesbydirector);
        }
    }

    public Movie getMovie(String moviename){
        return movierepo.get(moviename);
    }
    public Director getDirector(String directorname){
        return directorrepo.get(directorname);
    }

    public List<String> getMoviesbydirector(String directorname){
        return direcmoviespair.get(directorname);
    }

    public List<String> getAllMovies(){
        List<String> movies = new ArrayList<>();
        for (String moviename:movierepo.keySet()){
            movies.add(moviename);
        }
        return movies;
    }

    public void deletedirector(String directorname){
        direcmoviespair.remove(directorname);
    }
    public void deletealldirectors(){
        direcmoviespair.clear();
    }


}
