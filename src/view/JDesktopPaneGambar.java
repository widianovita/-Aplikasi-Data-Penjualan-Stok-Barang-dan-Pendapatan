/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package view;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/**
 *
 * @author Widia Novita
 */
public class JDesktopPaneGambar extends JDesktopPane {
    
        protected void paintComponent(Graphics g) {
        Graphics2D graphics = (Graphics2D) g.create();
        Image img = new ImageIcon(getClass().getResource("background.jpg")).getImage();
        graphics.drawImage(img,0,0,getWidth(),getHeight(),null);
        graphics.dispose();
    }
}
