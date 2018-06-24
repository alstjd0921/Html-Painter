package com.withpwn;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

class ChooserWindow extends JFrame {
    ChooserWindow() {
        setTitle("Select Target Image");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

public class HtmlPainter {
    Scanner sc = new Scanner(System.in);

    ChooserWindow chooserWindow;
    JFileChooser fileChooser = new JFileChooser();
    File file;

    HtmlPainter() throws IOException {
        System.out.println("[*] Step 1. Select Target Image");

        chooserWindow = new ChooserWindow();
        int returnVal = fileChooser.showOpenDialog(chooserWindow);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            file = fileChooser.getSelectedFile();
            chooserWindow.dispose();
        } else {
            System.out.println("[*] Canceled");
            System.exit(0);
        }


        TargetImage targetImage = new TargetImage(file);
        OutputHTML outputHTML = new OutputHTML(targetImage.GetBG(0, 0));

        System.out.print("[*] Start Paint? [y/n]: ");
        String YorN = sc.next();
        if (YorN.equals("y") || YorN.equals("Y")) {
            outputHTML.CreateHTML(targetImage);
        } else {
            System.out.println("[*] Canceled");
            System.exit(0);
        }

        System.out.println("[*] All process finished. Enjoy yourself!");
    }
}
