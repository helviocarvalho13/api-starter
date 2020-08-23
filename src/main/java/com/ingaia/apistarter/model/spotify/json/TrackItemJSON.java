package com.ingaia.apistarter.model.spotify.json;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackItemJSON {
	private String name;
	private List<ArtistJSON> artists;
	
}
