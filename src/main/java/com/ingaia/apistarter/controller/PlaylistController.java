package com.ingaia.apistarter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ingaia.apistarter.model.spotify.Playlist;
import com.ingaia.apistarter.services.PlaylistService;

@RestController	
@RequestMapping("/playlists")
public class PlaylistController {
	
	@Autowired
	private PlaylistService playlistService;
	
	@GetMapping("/{city}")
	@PreAuthorize("hasAuthority('ROLE_PLAYLIST') and #oauth2.hasScope('/playlists')")
	public Playlist returnPlaylist(@PathVariable String city){
		return playlistService.returnPlaylist(city);
	}

}
