/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author admin
 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

public class JLabelRounded extends AbstractBorder {

public JLabelRounded(Color c, int g) {
    color = c;
    gap = g;

}

@Override
public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
    g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
    g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
    g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    g2d.setColor(color);
    
    g2d.fill(new RoundRectangle2D.Double(x + 1, y + 1, width - 2, height - 2, gap, gap));
    g2d.setColor(color.white);
    Font font = new Font("SansSerif",Font.PLAIN,12);
    FontMetrics fm = g2d.getFontMetrics();
    g2d.setFont(font);
    g2d.drawString(" Xác nhận",(width-fm.stringWidth("Xác nhận"))/2,((height-fm.getHeight())/2)+fm.getAscent());
    g2d.dispose();
}
@Override
public Insets getBorderInsets(Component c) {
    return (getBorderInsets(c, new Insets(gap, gap, gap, gap)));
}

@Override
public Insets getBorderInsets(Component c, Insets insets) {
    insets.left = insets.top = insets.right = insets.bottom = gap;
    return insets;
}

@Override
public boolean isBorderOpaque() {
    return true;
}

// Variable declarations
private final Color color;
private final int gap;

}
