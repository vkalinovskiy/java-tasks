package com.tasks.htmlnormalizer;

public class Main {
    public static void main(String[] args) {
        String sourceHtmlString = "<body>         </body>\n"
                +
                "<body>\t</body>\n"
                +
                "<a href=\"/test\"></a>\n"
                +
                "<html><body></body><style>.class { background: #ff0000; }</style></html>\n"
                +
                "<p>example <span class=\"specialWord\">text</span></p>\n"
                +
                "<span class='specialWord'>text</span></p>";

        String normalizedHtmlString = HtmlNormalizer.normalize(sourceHtmlString);

        System.out.println(normalizedHtmlString);
    }
}
