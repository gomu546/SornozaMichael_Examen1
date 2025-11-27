package smUtilidades;

public class smLoading {

    public static final String COLOR_RESET = "\u001b[0m";
    public static final String COLOR_RED = "\u001b[31m";
    public static final String COLOR_GREEN = "\u001b[32m";
    public static final String COLOR_YELLOW = "\u001b[33m";
    public static final String COLOR_BLUE = "\u001b[34m";
    public static final String COLOR_PURPLE = "\u001b[35m";
    public static final String COLOR_CYAN = "\u001b[36m";
    public static final String COLOR_WHITE = "\u001b[37m";

    private static final String[] COLORS = {
            COLOR_RED,
            COLOR_GREEN,
            COLOR_YELLOW,
            COLOR_BLUE,
            COLOR_PURPLE,
            COLOR_CYAN,
            COLOR_WHITE,
            COLOR_RESET
    };

    private static int spinnerCounter = 0;
    private static final String ANIMATION_CHARS = "/—|\\";

    public static String printSpinnerFrame() {

        int charIndex = spinnerCounter % ANIMATION_CHARS.length();

        String color = COLORS[spinnerCounter % COLORS.length];

        spinnerCounter++;

        return color + ANIMATION_CHARS.charAt(charIndex) + COLOR_RESET;
    }
}