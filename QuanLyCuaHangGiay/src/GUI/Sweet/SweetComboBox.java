/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.Sweet;


import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.plaf.basic.BasicComboBoxUI;
public class SweetComboBox extends JComboBox<String>{
    public SweetComboBox ( String backgroundColor, String foregroundColor , int left, int top, int width, int height,String[] value)
    {
            this.setBackground(Color.decode(backgroundColor));
            this.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
            this.setForeground(Color.decode(foregroundColor));
            this.setModel(new javax.swing.DefaultComboBoxModel<>(value));
            
            this.setBorder(null);
            this.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
            this.setDoubleBuffered(true);
            this.setEditor(null);
            this.setFocusable(false);
            this.setLightWeightPopupEnabled(false);
            this.setBounds(left, top, width, height);
            
            this.setUI(new BasicComboBoxUI(){
            protected JButton createArrowButton()
            {
                JButton arrow = new JButton();
                arrow.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/down-arrow-icon.png")));
                arrow.setOpaque(true);
                arrow.setFont(new java.awt.Font("Tahoma", 0, 14));
                arrow.setText(null);
                arrow.setBorder(null);
                arrow.setBackground(Color.decode("#353746"));
                arrow.setContentAreaFilled(false);
                arrow.setBorder(null);
                return arrow;
            }
            });
//            this.setRenderer(new MyComboBoxRenderer("COLOR OF SNEAKER"));
//            this.setSelectedIndex(-1);
            this.addItemListener(new ItemChangeListener());
    }
}
class ItemChangeListener implements ItemListener{
    @Override
    public void itemStateChanged(ItemEvent event) {
       if (event.getStateChange() == ItemEvent.SELECTED) {
          Object item = event.getItem();
          
       }
    }       
}
class MyComboBoxRenderer extends JLabel implements ListCellRenderer
    {
        private String _title;

        public MyComboBoxRenderer(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }

    }
