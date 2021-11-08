package GUI.Sweet;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
public class SweetAlert extends Dialog
{
    private static SweetAlert alert;
    private static int option = 1;
    public static final int SUCCESS_ALERT = 0;
    public static final int ERROR_ALERT = 1;
    public static final int WARNING_ALERT = 2;
    public static final int INFOMATION_ALERT = 3;
    //wrapper color customize
    public static final String BG_COLOR = "#22242f";
    public static final String SUCCESS_COLOR = "#00b021";
    public static final String ERROR_COLOR = "#ff3535";
    public static final String WARNING_COLOR = "#ffcc00";
    public static final String INFOMATION_COLOR = "#1ca5ba";
    public static final String OK = "Đồng ý";
    public static final String CONFIRM = "Xác Nhận";
    public static final String CANCEL = "Hủy";
    //btnOK color customize
    private static String btnOKForeground = "#1ca5ba";
    private static String btnOKBackground = "#ffffff";
    private static String btnOKHover = "#f1f1f1";
    //btnConfirm color customize
    private static String btnConfirmForeground = "#ffffff";
    private static String btnConfirmBackground = "#00b021";
    private static String btnConfirmHover = "#007e17";
    //btnCancel color customize
    private static String btnCancelForeground = "#ffffff";
    private static String btnCancelBackground = "#ff3535";
    private static String btnCancelHover = "#b50000";
    //default item
    private static JPanel wrapper;
    private static JLabel lblTitle, lblMessage, lblConfirm, lblCancel, lblOK;
    public SweetAlert(Frame frame) 
    {
        super(frame,true);
        this.setAlwaysOnTop(true);
    }
    public static void showSweetAlert( JFrame parent, String title, String message, int type )
    {
        alert = new SweetAlert( parent );
        alert.setLayout(null);
        alert.setSize( 300, 150 );
        createUIAlert( title, message, type);
        addAlertEvent();
        alert.add(wrapper);
        alert.setUndecorated( true );
        alert.setLocationRelativeTo( parent );
        alert.setVisible( true );
    }
    public static int showSweetOption( JFrame parent, String title, String message, int type )
    {
        alert = new SweetAlert( parent );
        alert.setLayout( null );
        alert.setUndecorated( true );
        alert.setSize( 300, 150 );
        createUIOption( title, message, type);
        addOptionEvent();
        alert.add(wrapper);
        alert.setLocationRelativeTo( parent );
        alert.show();
        alert.dispose();
        return option;
    }
    private static void createOKButton( String btnName)
    {
        lblOK = new JLabel(btnName);
        lblOK.setHorizontalAlignment(JLabel.CENTER);
        lblOK.setBackground(Color.decode(btnOKBackground));
        lblOK.setOpaque(true);
        lblOK.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblOK.setForeground(Color.decode(btnOKForeground));
        lblOK.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblOK.addMouseListener(new java.awt.event.MouseAdapter() 
        {
                    public void mouseEntered(java.awt.event.MouseEvent evt) 
                    {
                        lblOK.setBackground(Color.decode(btnOKHover));
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) 
                    {
                        lblOK.setBackground(Color.decode(btnOKBackground));
                    }
                    public void mouseClicked(java.awt.event.MouseEvent evt) 
                    {
                        alert.removeAll();
                        alert.dispose();
                    }
        });
    }
    private static void createConfirmButton( String btnName)
    {
        lblConfirm = new JLabel(btnName);
        lblConfirm.setHorizontalAlignment(JLabel.CENTER);
        lblConfirm.setOpaque(true);
        lblConfirm.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblConfirm.setForeground(Color.decode(btnConfirmForeground));
        lblConfirm.setBackground(Color.decode(btnConfirmHover));
        lblConfirm.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblConfirm.addMouseListener(new java.awt.event.MouseAdapter() 
        {
            public void mouseEntered(java.awt.event.MouseEvent evt) 
            {
                lblConfirm.setBackground(Color.decode(btnConfirmHover));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) 
            {
                lblConfirm.setBackground(Color.decode(btnConfirmHover));
            }
            public void mousePressed(java.awt.event.MouseEvent evt) 
            {
                option = 1;
                alert.removeAll();
                alert.dispose();
                ResetDefalut();
            }
        });
    }
    private static void createCancelButton( String btnName)
    {
        lblCancel = new JLabel(btnName);
        lblCancel.setHorizontalAlignment(JLabel.CENTER);
        lblCancel.setOpaque(true);
        lblCancel.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lblCancel.setForeground(Color.decode(btnCancelForeground));
        lblCancel.setBackground(Color.decode(btnCancelBackground));
        lblCancel.addMouseListener(new java.awt.event.MouseAdapter()
        {                  
                    public void mouseEntered(java.awt.event.MouseEvent evt) 
                    {
                        lblCancel.setBackground(Color.decode(btnCancelHover));
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) 
                    {
                        lblCancel.setBackground(Color.decode(btnCancelBackground));
                    }
                    public void mousePressed(java.awt.event.MouseEvent evt) 
                    {
                        option = 0;
                        alert.removeAll();
                        alert.dispose();
                        ResetDefalut();
                    }
        });
    }
    private static void createUIOption( String title, String message, int type )
    {
        lblTitle = new JLabel(title);
        lblMessage = new JLabel("<html><p style=\"width:200px\">"+message+"</p></html>");
        wrapper = new JPanel();
        wrapper.setLayout(null);
        wrapper.setBounds(0, 0, 300, 150);
        wrapper.setBackground(Color.decode(BG_COLOR));
        createConfirmButton(CONFIRM);
        createCancelButton(CANCEL);
        lblConfirm.setBounds(70, 110, 70, 30);
        lblCancel.setBounds(160, 110, 70, 30);   
        switch(type)
        {
            case SUCCESS_ALERT:
                lblTitle.setForeground(Color.decode(SUCCESS_COLOR));
                break;
            case ERROR_ALERT:
                lblTitle.setForeground(Color.decode(ERROR_COLOR));                
                break;
            case WARNING_ALERT:
                lblTitle.setForeground(Color.decode(WARNING_COLOR));               
                break;
            case INFOMATION_ALERT:
                lblTitle.setForeground(Color.decode(INFOMATION_COLOR));             
                break;   
        }
        lblTitle.setBounds(10, 10, 200, 30);
        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18));
        lblMessage.setBounds(10, 40, 290, 50);
        lblMessage.setFont(new java.awt.Font("Tahoma", 0, 14));
        lblMessage.setForeground(Color.WHITE);
        wrapper.add(lblMessage);
        wrapper.add(lblTitle);
        wrapper.add(lblConfirm);
        wrapper.add(lblCancel);
    }
    private static void createUIAlert( String title, String message, int type )
    {
        lblTitle = new JLabel(title);
        lblMessage = new JLabel("<html><p style=\"width:200px\">"+message+"</p></html>");
        wrapper = new JPanel();
        wrapper.setLayout(null);
        wrapper.setBounds(0, 0, 300, 150);
        wrapper.setBackground(Color.decode(BG_COLOR));
        createOKButton(OK);
        lblOK.setBounds(120, 110, 70, 30);
        switch(type)
        {
            case SUCCESS_ALERT:              
                lblTitle.setForeground(Color.decode(SUCCESS_COLOR));
                break;
            case ERROR_ALERT:
                lblTitle.setForeground(Color.decode(ERROR_COLOR));
                break;
            case WARNING_ALERT:
                lblTitle.setForeground(Color.decode(WARNING_COLOR));
                break;
            case INFOMATION_ALERT:
                lblTitle.setForeground(Color.decode(INFOMATION_COLOR));
                break;
        }
        lblTitle.setBounds(10, 10, 200, 30);
        lblTitle.setFont(new java.awt.Font("Tahoma", 1, 18));
        lblMessage.setForeground(Color.WHITE);
        lblMessage.setBounds(10, 40, 290, 50);
        lblMessage.setFont(new java.awt.Font("Tahoma", 0, 14));        
        wrapper.add(lblMessage);
        wrapper.add(lblTitle);
        wrapper.add(lblOK);
    }
    private static void addOptionEvent()
    {
        alert.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent evt)
            {
                int keyCode = evt.getKeyCode();
                switch(keyCode)
                {
                    case 37:                       
                        FocusConfirmButton();
                        option = 1;
                        break;
                    case 39:
                        FocusCancelButton();
                        option = 0;
                        break;
                    case 10:
                        alert.removeAll();
                        alert.dispose();
                        ResetDefalut();
                        break;                  
                }
            }
        });
    }
    private static void addAlertEvent()
    {
        alert.addKeyListener(new KeyAdapter() 
        {
            @Override
            public void keyPressed(KeyEvent evt)
            {
                if ( evt.getKeyCode()==10 )
                {
                    alert.removeAll();
                    alert.dispose();
                }
            }
        });
    }
    private static void FocusConfirmButton()
    {
        lblConfirm.setBackground(Color.decode(btnConfirmHover));
        lblConfirm.addMouseListener(new java.awt.event.MouseAdapter() 
        {                  
                    public void mouseEntered(java.awt.event.MouseEvent evt) 
                    {
                        lblConfirm.setBackground(Color.decode(btnConfirmHover));
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) 
                    {
                        lblConfirm.setBackground(Color.decode(btnConfirmHover));
                    }
        });
        lblCancel.setBackground(Color.decode(btnCancelBackground));
        lblCancel.addMouseListener(new java.awt.event.MouseAdapter() 
        {
                    public void mouseEntered(java.awt.event.MouseEvent evt) 
                    {
                        lblCancel.setBackground(Color.decode(btnCancelHover));
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) 
                    {
                        lblCancel.setBackground(Color.decode(btnCancelBackground));
                    }
        });
    }
    private static void FocusCancelButton()
    {
        lblConfirm.setBackground(Color.decode(btnConfirmBackground));
        lblConfirm.addMouseListener(new java.awt.event.MouseAdapter() 
        {                  
                    public void mouseEntered(java.awt.event.MouseEvent evt) 
                    {
                        lblConfirm.setBackground(Color.decode(btnConfirmHover));
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) 
                    {
                        lblConfirm.setBackground(Color.decode(btnConfirmBackground));
                    }
        });
        lblCancel.setBackground(Color.decode(btnCancelHover));
        lblCancel.addMouseListener(new java.awt.event.MouseAdapter() 
        {
                    public void mouseEntered(java.awt.event.MouseEvent evt) 
                    {                        
                        lblCancel.setBackground(Color.decode(btnCancelHover));
                    }
                    public void mouseExited(java.awt.event.MouseEvent evt) {
                        lblCancel.setBackground(Color.decode(btnCancelHover));
                    }
        });
    }
    private static void ResetDefalut()
    {
        btnOKForeground = "#1ca5ba";
        btnOKBackground = "#ffffff";
        btnOKHover = "#f1f1f1";
        btnConfirmForeground = "#ffffff";
        btnConfirmBackground = "#00b021";
        btnConfirmHover = "#007e17";
        btnCancelForeground = "#ffffff";
        btnCancelBackground = "#ff3535";
        btnCancelHover = "#b50000";
    }
}
