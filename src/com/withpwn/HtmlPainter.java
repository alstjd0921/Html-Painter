package com.withpwn;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HtmlPainter {
    Scanner sc = new Scanner(System.in);

    JFrame chooserWindow;
    JFileChooser fileChooser = new JFileChooser();
    File file;

    HtmlPainter() throws IOException {
        // Step 1
        System.out.println("[*] Step 1. Select Target Image");
        chooserWindow = new JFrame();
        int returnVal = fileChooser.showOpenDialog(chooserWindow);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        } else {
            System.out.println("[*] Canceled");
            System.exit(0);
        }

        TargetImage targetImage = new TargetImage(file);
        OutputHTML outputHTML = new OutputHTML(targetImage.GetBG(0, 0));

        /////////////////////////////////////////////////////////////////////////////

        // Step 2
        System.out.println("[*] Step 2. Select Output Location");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Html file", "html"));
        returnVal = fileChooser.showSaveDialog(chooserWindow);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
        } else {
            System.out.println("[*] Canceled");
            System.exit(0);
        }

        /////////////////////////////////////////////////////////////////////////////

        // Confirm
        System.out.print("[*] Start Paint? [y/n]: ");
        String YorN = sc.next();
        if (YorN.equals("y") || YorN.equals("Y")) {
            outputHTML.Insert2Buf(targetImage);
            outputHTML.WriteInFile(file);
        } else {
            System.out.println("[*] Canceled");
            System.exit(0);
        }

        System.out.println("[*] All process finished. Enjoy yourself!");
        System.exit(0);
    }
}
