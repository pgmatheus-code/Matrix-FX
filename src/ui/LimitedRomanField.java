package ui;

import javafx.scene.control.TextField;

public class LimitedRomanField extends TextField {
	private final int limit;

    public LimitedRomanField(int limit) {
        this.limit = limit;
    }
    
    public void replaceText(int start, int end, String text) {
        if (validate(text))
        {
            super.replaceText(start, end, text);
        }
        verify();
    }
    
    public void replaceSelection(String text) {
        if (validate(text))
        {
            super.replaceSelection(text);
        }
        verify();
    }

    private void verify() {
        if (getText().length() > limit) {
            setText(getText().substring(0, limit));
        }
    }

    private boolean validate(String text)
    {
    	return text.matches("[IXCMVLD]*\\.?");
    }
}