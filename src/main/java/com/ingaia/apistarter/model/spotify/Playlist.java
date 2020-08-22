package com.ingaia.apistarter.model.spotify;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Playlist {
	
	private List<MusicSuggestion> suggestions;
	
	public Playlist(List<MusicSuggestion> suggestions) {
		this.suggestions = suggestions;
	}
}
