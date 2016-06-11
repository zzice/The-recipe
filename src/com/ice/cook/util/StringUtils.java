package com.ice.cook.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	public static String textValue;
	public static Pattern pattern;
	public static Matcher matcher;
	public static String replaceAll(String s){
		pattern=Pattern.compile("</?h2>");
		matcher=pattern.matcher(s);
		textValue = matcher.replaceAll("");
		pattern=Pattern.compile("</?p>");
		matcher=pattern.matcher(textValue);
		textValue = matcher.replaceAll("");
		pattern=Pattern.compile("<hr>");
		matcher=pattern.matcher(textValue);
		textValue = matcher.replaceAll("\n");
		return textValue;
	}

}
