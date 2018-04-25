package com.kelsey.NumberTree.entities;

import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

@Component
public class NumberTree extends JPanel implements TreeSelectionListener {
    private JEditorPane htmlPane;
    private javax.swing.JTree tree;
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    private static boolean useSystemLookAndFeel = false;


    @Override
    public void valueChanged(TreeSelectionEvent e) {

    }
}
