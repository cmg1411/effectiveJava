package Chapter6.Day36;

import java.util.EnumSet;

public class EnumSetEx {
    public enum Style {
        BOLD, ITALIC, UNDERLINE, STRIKETHROUGH;
    }

    public void applyStyle(EnumSet<Style> styles) {
        for (Style style : styles) {
            System.out.println("use " + style.name());
        }
    }

    public static void main(String[] args) {
        EnumSetEx e = new EnumSetEx();
        e.applyStyle(EnumSet.of(Style.UNDERLINE, Style.BOLD));
    }
}

