package com.withpwn;

import javax.swing.*;
import java.awt.*;

public class HtmlPainter extends JFrame {
    Container contentPane;

    HtmlPainter() {
        setTitle("Html Painter");
        setSize(300, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = getContentPane();

        setVisible(true);
    }
}
