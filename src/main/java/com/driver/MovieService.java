package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired MovieRepository movieRepository;
    public void addMovie(Movie movie){
        movieRepository.savemovie(movie);
    }
    public void addDirector(Director director){
        movieRepository.savedirector(director);
    }
    public void adddirectormoviepair(String moviename,String directorname){
        movieRepository.pairDirectorMovie(moviename,directorname);
    }
    public Movie getMoviebyname(String moviename){
        return movieRepository.getMovie(moviename);
    }
    public Director getDirectorbyname(String directorname){
        return movieRepository.getDirector(directorname);
    }
    public List<String> getMoviesbydirector(String directorname){
        return movieRepository.getMoviesbydirector(directorname);
    }
    public List<String> getAllMoviesNames(){
        return movieRepository.getAllMovies();
    }
    public void deletemoviesbyname(String directorname){
        movieRepository.deletedirector(directorname);
    }
    public void deletealldirectormovies(){
        movieRepository.deletealldirectors();
    }
}
