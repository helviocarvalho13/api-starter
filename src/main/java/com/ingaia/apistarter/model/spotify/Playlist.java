package com.ingaia.apistarter.model.spotify;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Playlist {
	private String urlOpenSpotify;
	private List<MusicSuggestion> suggestions;
	
	public Playlist(List<MusicSuggestion> suggestions, String urlOpenSpotify) {
		this.suggestions = suggestions;
		this.urlOpenSpotify = urlOpenSpotify;
	}
}
