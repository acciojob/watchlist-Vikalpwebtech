package com.driver;

import org.springframework.stereotype.Repository;

import javax.servlet.http.PushBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Repository
public class MovieRepository {
    HashMap<String, Movie> movierepo;
    HashMap<String, Director> directorrepo;
    //Directorname and List of movies
    HashMap<String, List<String>> direcmoviespair;

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
        List<String> movies = new ArrayList<>();
        if (direcmoviespair.containsKey(directorname)){
            movies = direcmoviespair.get(directorname);

            for (String movie : movies){
                if (movierepo.containsKey(movie)){
                    movierepo.remove(movie);
                }
            }
        }
        if (direcmoviespair.containsKey(directorname)) {
            direcmoviespair.remove(directorname);
        }
    }
    public void deletealldirectors(){
        HashSet<String> moviesSet = new HashSet<String>();

        //Deleting the director's map
        directorrepo = new HashMap<>();

        //Finding out all the movies by all the directors combined
        for(String director: direcmoviespair.keySet()){

            //Iterating in the list of movies by a director.
            for(String movie: direcmoviespair.get(director)){
                moviesSet.add(movie);
            }
        }

        //Deleting the movie from the movieDb.
        for(String movie: moviesSet){
            if(movierepo.containsKey(movie)){
                movierepo.remove(movie);
            }
        }
        //clearing the pair.
        direcmoviespair = new HashMap<>();
    }

}
