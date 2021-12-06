/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mutex;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 *
 * @author shreyabhandare
 */
public class Main {
    public static void main(String args[]) throws InterruptedException{
        File bfile = new File("BakeryOutput.txt");
        try {
            boolean result = Files.deleteIfExists(bfile.toPath());
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        File ffile = new File("FilterOutput.txt");
        try {
            boolean result = Files.deleteIfExists(ffile.toPath());
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        File jfile = new File("JavaLockOutput.txt");
        try {
            boolean result = Files.deleteIfExists(jfile.toPath());
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Result resultLock = JUnitCore.runClasses(LockTest.class);
        for (Failure failure : resultLock.getFailures())
        {
                System.out.println(failure.toString());
        }
        
        Result resultBakery = JUnitCore.runClasses(BakeryTest.class);
        for (Failure failure : resultBakery.getFailures())
        {
                System.out.println(failure.toString());
        }
        
        Result resultFilter = JUnitCore.runClasses(FilterTest.class);
        for (Failure failure : resultFilter.getFailures())
        {
                System.out.println(failure.toString());
        }

    }
}
