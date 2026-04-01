package ContactList;

import javax.swing.text.*;

public class NumberFilter extends DocumentFilter {

    private int maxLength;

    public NumberFilter(int maxLength){
        this.maxLength = maxLength;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
            throws BadLocationException {

        if(string == null){
            return;
        }

        if(string.matches("\\d+") &&
                fb.getDocument().getLength() + string.length() <= maxLength){

            super.insertString(fb, offset, string, attr);
        }
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
            throws BadLocationException {

        if(text == null) return;

        if(text.matches("\\d*") &&
                fb.getDocument().getLength() - length + text.length() <= maxLength){

            super.replace(fb, offset, length, text, attrs);
        }
    }
}