package com.withpwn;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HtmlPainter extends JFrame implements ActionListener {
    TargetImage targetImage;
    OutputHTML outputHTML;
    int returnVal;

    JFrame chooserWindow;
    JFileChooser fileChooser = new JFileChooser();
    File file;

    JPanel panel = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel();

    JButton button = new JButton("Target Image");
    JButton button1 = new JButton("Output Path");
    JButton Paint = new JButton("Paint!");

    JLabel label = new JLabel("Choose Target Image");
    JLabel label1 = new JLabel("Input Output Path");
    JLabel label2 = new JLabel("Paint?");

    HtmlPainter() throws IOException {
        setTitle("Html-Painter");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        button.addActionListener(this);
        button1.addActionListener(this);
        Paint.addActionListener(this);

        button.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        button1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        Paint.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        label.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        label1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        label2.setAlignmentX(JComponent.CENTER_ALIGNMENT);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(label);
        panel.add(button);

        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        panel1.add(label1);
        panel1.add(button1);

        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.add(label2);
        panel2.add(Paint);

        setLayout(new FlowLayout(FlowLayout.CENTER, 50, 100));
        add(panel);
        add(panel1);
        add(panel2);

        setResizable(false);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            System.out.println("[*] Step 1. Select Target Image");
            chooserWindow = new JFrame();
            returnVal = fileChooser.showOpenDialog(chooserWindow);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            } else {
                System.out.println("[*] Canceled");
                System.exit(0);
            }

            try {
                targetImage = new TargetImage(file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            outputHTML = new OutputHTML(targetImage.GetBG(0, 0));
        } else if (e.getSource() == button1) {
            System.out.println("[*] Step 2. Select Output Location");
            fileChooser.setFileFilter(new FileNameExtensionFilter("Html file", "html"));
            returnVal = fileChooser.showSaveDialog(chooserWindow);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            } else {
                System.out.println("[*] Canceled");
                System.exit(0);
            }
        } else if (e.getSource() == Paint) {
            outputHTML.Insert2Buf(targetImage);
            try {
                outputHTML.WriteInFile(file);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

            System.out.println("[*] All process finished. Enjoy yourself!");

            this.dispose();
            try {
                new HtmlPainter();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
