package lotto.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {
    public static long startCashInput() throws IOException {
        String text;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        text = reader.readLine().replace("\\n","\n");

        return Long.parseLong(text);
    }

    public static String[] winNumInput() throws IOException {
        String text;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        text = reader.readLine().replace("\\n","\n");

        return text.split(",");
    }

    public static int bonusNumInput() throws IOException {
        String text;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        text = reader.readLine().replace("\\n","\n");

        return Integer.parseInt(text);
    }
}
