package com.ingaia.apistarter.model.spotify;

import java.util.List;

import com.ingaia.apistarter.model.spotify.json.ArtistJSON;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MusicSuggestion {
	
	private String artist;
	private String music;
	
	public MusicSuggestion(List<ArtistJSON> artists, String music) {
		this.music = music;
		this.artist = "";
		artists.forEach(a -> this.artist += a.getName() + " | ");
		this.artist = this.artist.substring(0, this.artist.length() - 3);
	}
}
