/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author admin
 */
public class Rounded extends JPanel{
    public int cornerRadius = 20;
    public int x =0,y=0;
    public Color backgroundColor;
    
    public void changeColor(Color color){
        backgroundColor = color;
    }
    
@Override
   protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Dimension arcs = new Dimension(cornerRadius, cornerRadius);
            int width = getWidth();
            int height = getHeight();
            Graphics2D graphics = (Graphics2D) g;
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setColor(backgroundColor);

            
            
            graphics.fillRoundRect(x,y, width, height, arcs.width, arcs.height); //paint background
            
          //graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height); //paint border
   }
}
