package com.ingaia.apistarter.model.openweather.json;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherJSON {
	private MainJSON main;
	private String name;
	private SysJSON sys;
}
