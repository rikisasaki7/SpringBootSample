package com.example.demo.user.application;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserApplicationService {

	private final MessageSource messageSource;

	public Map<String, Integer> getGengerMap(Locale locale) {

		Map<String, Integer> genderMap = new LinkedHashMap<>();
		String male = messageSource.getMessage("male", null, null);
		String female = messageSource.getMessage("female", null, null);
		String[] array = {"田中", "晴れ"};
//		String helloString = messageSource.getMessage("hello", array, locale);
		String helloString = messageSource.getMessage("hello", array, locale);
		genderMap.put(male, 1);
		genderMap.put(female, 2);
		genderMap.put(helloString, 3);
		return genderMap;
	}
}
