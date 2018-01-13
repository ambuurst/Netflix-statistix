package ui;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class JTextView extends JLabel {
    private String fontName;
    private FontType fontType;
    private int fontSize;

    public JTextView(){
        super();
        init();
    }

    public JTextView(String text){
        super(text);
        init();
    }

    private void init(){
        fontName = "Arial";
        fontType = FontType.NORMAL;
        fontSize = 16;
        updateFont();
        setBorder(new EmptyBorder(2, 2, 2, 2));
    }

    public void setTextSize(int size){
        fontSize = size;
        updateFont();
    }

    public void setFontType(FontType type){
        fontType = type;
    }

    public void setFontName(String name){
        fontName = name;
    }

    private void updateFont(){
        setFont(new Font(fontName, fontType == FontType.BOLD ? Font.BOLD : fontType == FontType.ITALIC ? Font.ITALIC : Font.PLAIN, fontSize));
    }
}
