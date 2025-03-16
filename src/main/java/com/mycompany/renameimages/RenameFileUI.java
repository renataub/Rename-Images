package com.mycompany.renameimages;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class RenameFileUI extends JFrame {

    private final JTextField numberField;
    private final JButton selectButton, renameButton;
    private JFileChooser fileChooser;
    private File[] selectedFiles;
    private final JRadioButton addButton, subtractButton;
    private final ButtonGroup operationGroup;

    public RenameFileUI() {
        setTitle("File Renamer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // UI components
        selectButton = new JButton("Select Files");
        numberField = new JTextField(10);
        renameButton = new JButton("Rename Files");

        // Radio buttons for selecting operation
        addButton = new JRadioButton("(+)", true);
        subtractButton = new JRadioButton("(-)");
        operationGroup = new ButtonGroup();
        operationGroup.add(addButton);
        operationGroup.add(subtractButton);

        // Layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1, 5, 5));

        panel.add(selectButton);
        panel.add(new JLabel("Enter Number:"));
        panel.add(numberField);
        panel.add(addButton);
        panel.add(subtractButton);
        panel.add(renameButton);

        add(panel);

        // Button actions
        selectButton.addActionListener(e -> selectFiles());
        renameButton.addActionListener(e -> renameFiles());
    }

    private void selectFiles() {
        fileChooser = new JFileChooser();
        fileChooser.setMultiSelectionEnabled(true);
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFiles = fileChooser.getSelectedFiles();
            JOptionPane.showMessageDialog(this, "Selected " + selectedFiles.length + " files.");
        }
    }

    private void renameFiles() {
        try {
            int changeValue = Integer.parseInt(numberField.getText());
            boolean isIncreasing = addButton.isSelected();

            if (selectedFiles != null && selectedFiles.length > 0) {
                boolean renamedAtLeastOneFile = FileRenamer.renameFiles(selectedFiles, changeValue, isIncreasing);

                if (renamedAtLeastOneFile) {
                    JOptionPane.showMessageDialog(this, "Files Renamed Successfully!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "No files selected.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.");
        }
    }
}
