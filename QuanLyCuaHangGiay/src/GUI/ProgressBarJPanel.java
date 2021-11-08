/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author Administrator
 */
public class ProgressBarJPanel extends JPanel {
    float percent = 0.0f;
    public void UpdateProgress(float percent){
        this.percent = percent;
    }
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        
        g2.translate(this.getWidth()/2,this.getHeight()/2);
        g2.rotate(Math.toRadians(270));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Arc2D.Float arc =  new Arc2D.Float(Arc2D.PIE);      
        Arc2D.Float background =  new Arc2D.Float(Arc2D.PIE);
        Ellipse2D.Float circle = new Ellipse2D.Float(0,0,110,110);
        circle.setFrameFromCenter(new Point(0,0),new Point(40,40));
        arc.setFrameFromCenter(new Point(0,0),new Point(45,45));
        background.setFrameFromCenter(new Point(0,0),new Point(45,45));
        background.setAngleStart(1);
        background.setAngleExtent(-100*3.6);
        arc.setAngleStart(1);
        arc.setAngleExtent(-percent*3.6);
        g2.setColor(Color.WHITE);
        g2.draw(background);
        g2.fill(background);
        g2.setColor(new java.awt.Color(210,57,44));
        g2.draw(arc);
        g2.fill(arc);
        
        g2.setColor(new java.awt.Color(53,55,70));
        g2.draw(circle);
        g2.fill(circle);
        g2.setColor(Color.WHITE);
        g2.rotate(Math.toRadians(90));
        FontMetrics fm = g2.getFontMetrics();
        Rectangle2D rec2D = fm.getStringBounds(percent + "%", g);
        g2.drawString(percent+"%",-(int)rec2D.getWidth()/2,-(int)rec2D.getHeight()/2+fm.getAscent());
    }
}
