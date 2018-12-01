package com.withpwn;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Vector;

interface H {
    void Insert2Buf(TargetImage image);

    void WriteInFile(File file) throws FileNotFoundException;
}

abstract class HTML implements H {
    Vector<String> html = new Vector<>();

    @Override
    public void Insert2Buf(TargetImage image) {
        for (int i = 0; i < image.height; i++) {
            html.add("    <tr>\n");
            for (int j = 0; j < image.width; j++) {
                html.add(image.GetTd(j, i));
            }
            html.add("    </tr>\n");
        }
        html.add("</table>\n");
    }

    @Override
    public abstract void WriteInFile(File file) throws FileNotFoundException;
}

public class OutputHTML extends HTML {
    OutputHTML(int[] bgcolor) {
        html.add("<!DOCTYPE html>\n");
        html.add("<style type=\"text/css\">\n");
        html.add(String.format("    html{background-color:#%02x%02x%02x;}\n", bgcolor[0], bgcolor[1], bgcolor[2]));
        html.add("    table{margin:auto;text-align:center;border-spacing:0px;border-style:none;padding:0px;}\n");
        html.add("    th{height:100%;width:100%;}\n");
        html.add("</style>\n");
        html.add("<table id=\"image\">\n");
    }

    @Override
    public void WriteInFile(File file) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);
        for (int i = 0; i < html.size(); i++) {
            printWriter.println(html.elementAt(i));
        }
        printWriter.close();
    }
}
