package com.ingaia.apistarter.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ingaia.apistarter.model.openweather.Weather;
import com.ingaia.apistarter.model.spotify.MusicSuggestion;
import com.ingaia.apistarter.model.spotify.Playlist;
import com.ingaia.apistarter.model.spotify.TokenSpotify;
import com.ingaia.apistarter.model.spotify.json.MusicJSON;

@Service
public class PlaylistService {

	@Autowired
	private OpenWeatherService openWeatherService;
	
	@Autowired
	private SpotifyService spotifyService;
	
	@Autowired
	private CityService cityService;
	
	public Playlist returnPlaylist(String cidade) {
		Weather weather = openWeatherService.returnWeather(cidade);
		cityService.save(weather);
		String category = weather.categoriaSpotify();
		
		if(TokenSpotify.scopedToken == null) {
			spotifyService.returnTokenSpotify();
		}
		
		String url = spotifyService.returnUrlOneOfThePlaylistsSpotifyByCategory(category);
		
		ResponseEntity<MusicJSON> musicsJSON = spotifyService.returnMusicsByPlaylistSpotify(url);
		
		List<MusicSuggestion> suggestions = new ArrayList<MusicSuggestion>();
		musicsJSON.getBody().getTracks().getItems().forEach(m -> suggestions.add(new MusicSuggestion(m.getTrack().getArtists() ,m.getTrack().getName())));
		return new Playlist(suggestions);
	}

}
