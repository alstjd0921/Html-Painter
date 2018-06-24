package com.withpwn;

import java.util.Vector;

public class OutputHTML {
    Vector<String> html = new Vector<String>();

    OutputHTML(int[] bgcolor) {
        html.add("<!DOCTYPE html>\n");
        html.add("<html>\n");
        html.add("<head>\n");
        html.add("    <style type=\"text/css\">\n");
        html.add("        html {\n");
        html.add(String.format("        html{background-color:#%02x%02x%02x;}\n", bgcolor[0], bgcolor[1], bgcolor[2]));
        html.add("    table{margin:auto;text-align:center;border-spacing:0px;border-style:none;padding:0px;}\n");
        html.add("    th{height:100%;width:100%;}\n");
        html.add("    </style>\n");
        html.add("</head>\n");
        html.add("<body>\n");
        html.add("    <table id=\"image\">\n");
    }

    void CreateHTML(TargetImage image) {
        for (int i = 0; i < image.height; i++) {
            html.add("        <tr>\n");
            for (int j = 0; j < image.width; j++) {
                html.add(image.GetTd(i, j));
            }
            html.add("        </tr>\n");
        }
        html.add("    </table>\n");
        html.add("</body>\n");
        html.add("</html>");
    }
}
