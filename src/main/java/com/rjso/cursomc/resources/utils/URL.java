package com.rjso.cursomc.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class URL {
	
	public static String decodeParam(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	public static List<Integer> decodeIntList(String s) {
		List<String> aux = Arrays.asList(s.split(","));
		List<Integer> finalList = new ArrayList<Integer>();
		for (String a : aux) {
			finalList.add(Integer.parseInt(a));
		}
		return finalList;

		// alternativa
		// return Arrays.asList(s.split(",")).stream().map(x ->
		// Integer.parseInt(x)).collect(Collectors.toList());
	}

}
