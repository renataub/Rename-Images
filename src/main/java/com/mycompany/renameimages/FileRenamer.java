
package com.mycompany.renameimages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class FileRenamer {
    public static boolean renameFiles(File[] files, int changeValue, boolean isIncreasing){
        boolean renamedAtLeastOne = false;
        List<File> tempRenamedFiles = new ArrayList<>();
        
        for(File file: files){
            String fileName = file.getName();
            String imgNumber = fileName.replaceAll("\\D+", ""); 
            
            if(!imgNumber.isEmpty()){
                try{
                    int originalNumber = Integer.parseInt(imgNumber);
                    int newNumber = isIncreasing ? originalNumber + changeValue : originalNumber - changeValue;
                    
                    if(newNumber < 1 || newNumber > 9999){
                        JOptionPane.showMessageDialog(null, 
                                "Error: The new number (" + newNumber + ") must be between 1 and 9999.\n" +
                                "File: " + fileName + " was not renamed.", 
                                "Invalid Number", JOptionPane.WARNING_MESSAGE);
                        continue;
                    }
                    
                    if (newNumber == originalNumber) {
                        continue;
                    }
                    
                    String formattedNumber = String.format("%04d", newNumber) + "_temp";
                    
                    String newFileName = fileName.replace(imgNumber, formattedNumber);
                    File renamedFile = new File(file.getParent(), newFileName);
                    
                    if(file.renameTo(renamedFile)){
                        renamedAtLeastOne = true;
                        tempRenamedFiles.add(renamedFile);
                        System.out.println("Renamed: " + fileName + " to " + newFileName);
                    }
                    else{
                        System.out.println("Failed to rename: " + fileName);
                    }
                    
                }//try
                catch (NumberFormatException e){
                    System.out.println("Skipping file (invalid number): " + fileName); 
                }
            }//if
        }//for
        
        for(File file: tempRenamedFiles){
            String fileName = file.getName();
            String cleanedName = fileName.replace("_temp", "");
            File cleanedFile = new File(file.getParent(), cleanedName);
            
            if(file.renameTo(cleanedFile)){
                renamedAtLeastOne = true;
                System.out.println("Renamed: " + fileName + " to " + cleanedName);
                }
                else{
                    System.out.println("Failed to rename: " + fileName);
                }
        }//for
        
        return renamedAtLeastOne;
    }//func
}
