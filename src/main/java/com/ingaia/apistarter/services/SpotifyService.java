package com.ingaia.apistarter.services;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ingaia.apistarter.exceptions.UnauthorizedAccessException;
import com.ingaia.apistarter.model.spotify.TokenSpotify;
import com.ingaia.apistarter.model.spotify.json.CategoryJSON;
import com.ingaia.apistarter.model.spotify.json.MusicJSON;
import com.ingaia.apistarter.util.Util;

@Service
public class SpotifyService {
	
	@Value("${apiKeySpotify}")
	private String apiKeySpotify;
	
	private static final String URL_TOKEN_SPOTIFY = "https://accounts.spotify.com/api/token";
	
	public void returnTokenSpotify() {
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headersToken = new HttpHeaders();
		headersToken.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headersToken.set("Authorization", "Basic "+apiKeySpotify);
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", "client_credentials");
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headersToken);
		ResponseEntity<TokenSpotify> response = null;
		
		try {
			response = restTemplate.postForEntity(URL_TOKEN_SPOTIFY, entity, TokenSpotify.class);
		}catch (Exception e) {
			throw new EmptyResultDataAccessException(1);
		}
		
		if(response != null) {
			if(response.getStatusCode().equals(HttpStatus.OK)) {
				TokenSpotify.scopedToken = response.getBody();
			}
		}else {
			throw new EmptyResultDataAccessException(1);
		}
		
		
	}
	
	public String returnUrlOneOfThePlaylistsSpotifyByCategory(String category){
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.spotify.com/v1/browse/categories/"+category+"/playlists";
		HttpHeaders headers = returnHttpHeaders();
		
		HttpEntity<?> request = new HttpEntity<>(headers);
		ResponseEntity<CategoryJSON> playlistCategoryResponse = null;
		try {
			playlistCategoryResponse = restTemplate.exchange(url, HttpMethod.GET, request, CategoryJSON.class);
		}catch (Exception e) {
			throw new EmptyResultDataAccessException(1);
		}
		
		if(playlistCategoryResponse != null) {
			if(playlistCategoryResponse.getStatusCode().equals(HttpStatus.OK)) {
				int qtdItems = playlistCategoryResponse.getBody().getPlaylists().getItems().size();
				return playlistCategoryResponse.getBody().getPlaylists().getItems().get(Util.getRandomNumber(0, qtdItems)).getHref();
			}
			
			if(playlistCategoryResponse.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
				TokenSpotify.scopedToken = null;
				throw new UnauthorizedAccessException("1L");
			}
		}
		
		throw new EmptyResultDataAccessException(1);
		
	}
	
	
	@Cacheable("returnMusicsByPlaylistSpotify")
	public ResponseEntity<MusicJSON> returnMusicsByPlaylistSpotify(String url) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = returnHttpHeaders();
		
		HttpEntity<?> request = new HttpEntity<>(headers);
		ResponseEntity<MusicJSON> musicsResponse = null;
		
		
		try {
			musicsResponse = restTemplate.exchange(url, HttpMethod.GET, request, MusicJSON.class);
		}catch (Exception e) {
			throw new EmptyResultDataAccessException(1);
		}
		
		if(musicsResponse != null) {
			if(musicsResponse.getStatusCode().equals(HttpStatus.OK)) {
				return musicsResponse;
			}
			
			if(musicsResponse.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
				TokenSpotify.scopedToken = null;
				throw new UnauthorizedAccessException("1L");
			}
		}
		
		throw new EmptyResultDataAccessException(1);
	}
	
	private HttpHeaders returnHttpHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", TokenSpotify.scopedToken.getToken_type()+" "+TokenSpotify.scopedToken.getAccess_token());
		return headers;
	}
	
}
