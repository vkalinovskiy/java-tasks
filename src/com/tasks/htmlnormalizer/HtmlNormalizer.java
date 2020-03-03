package com.tasks.htmlnormalizer;

import java.util.regex.Pattern;

public class HtmlNormalizer {
    public static String normalize(String htmlString) {
        String regex = "((?<=\\>)((\\t)+|(\\n)+|(\\s)+)(?=\\<))|(class=((\"|').*(\"|')))|(\\<style\\>.*\\<\\/style\\>)";

        return htmlString.replaceAll(regex, "");
    }
}
