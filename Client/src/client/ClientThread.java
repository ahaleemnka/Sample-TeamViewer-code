/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

/**
 *
 * @author HaLeeM
 */

import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ClientThread {
    public Canvas canvasObject=new Canvas();
    public boolean f= true;
    
    Thread imageThread=new Thread(new Runnable() {
        @Override
        public void run() {
            while(f){
                try {
                    canvasObject.receiveImage();
                } catch (IOException ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
    
    Thread mouseKeyboardThread=new Thread(new Runnable() {
        @Override
        public void run() {
            while(f){
                try {
                    canvasObject.mouseKeyboard();
                    try{
                        Thread.sleep(100);
                    }
                    catch(InterruptedException  e){
                        Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE,null,e);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    });
    
    public void startRunning(){
        try {
            canvasObject.connectToServer();
            canvasObject.setUpStream();
            imageThread.start();
            mouseKeyboardThread.start();
        } catch (IOException ex) {
            Logger.getLogger(ClientThread.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
