
package com.mycompany.renameimages;

import javax.swing.SwingUtilities;

public class RenameImages {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RenameFileUI().setVisible(true));
    }
}
