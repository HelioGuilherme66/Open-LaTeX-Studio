/* 
 * Copyright (c) 2015 Sebastian Brudzinski
 * 
 * See the file LICENSE for copying permission.
 */
package latexstudio.editor.files;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import latexstudio.editor.settings.ApplicationSettings;

/**
 * This is a simple util class, generating the file/directory chooser modals and
 * returning the user choices.
 *
 * @author Sebastian
 */
public final class FileChooserService {

    private FileChooserService() {
    }

    public enum DialogType {

        SAVE, OPEN, PDF;
    }

    public static File getSelectedDirectory(String buttonText) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // retreiving last directory selected by the user
        String currentDirectory = ApplicationSettings.INSTANCE.getUserLastDir();
        if(currentDirectory != null && currentDirectory.length() > 0) {
            chooser.setCurrentDirectory(new File(currentDirectory));
        }
        
        int returnVal = chooser.showDialog(null, buttonText);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            
            // storing last directory selected by the user
            ApplicationSettings.INSTANCE.setUserLastDir(file.getAbsolutePath());
            ApplicationSettings.INSTANCE.save();
            
            return file;
        }

        return null;
    }

    public static File getSelectedFile(String extension, String description, DialogType type) {
        return getSelectedFile(extension, description, type, false);
    }

    public static File getSelectedFile(String extension, String description, DialogType type, boolean fixExtension) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(description, extension);
        chooser.setFileFilter(filter);

        String currentDirectory = ApplicationSettings.INSTANCE.getUserLastDir();
        if(currentDirectory != null && currentDirectory.length() > 0) {
            chooser.setCurrentDirectory(new File(currentDirectory));
        }
        
        int returnVal = 0;
        switch (type) {
            case SAVE:
                returnVal = chooser.showSaveDialog(null);
                break;
            case OPEN:
                returnVal = chooser.showOpenDialog(null);
                break;
            case PDF:
                returnVal = chooser.showDialog(null, "Generate PDF");
                break;
            default:
                break;
        }
        
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            if (!file.exists() && type == DialogType.OPEN) {
                JOptionPane.showMessageDialog(null, "File doesn't exist", "Item not found", JOptionPane.ERROR_MESSAGE); 
            } else {              
                String filePath = file.getAbsolutePath();
                if (fixExtension && !filePath.endsWith("." + extension)) {
                    file = new File(filePath + "." + extension);
                }
                
                // storing last directory selected by the user
                ApplicationSettings.INSTANCE.setUserLastDir(file.getAbsolutePath());
                ApplicationSettings.INSTANCE.save();

                return file;
            }
        }
        return null;
    }
}
