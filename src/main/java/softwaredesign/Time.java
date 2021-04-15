package softwaredesign;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Time {
    static String timeInput() throws IOException {
        String userCommand;
        int x = 60; // wait x seconds at most
        boolean displayTimeAlert = true;
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        long startTime = System.currentTimeMillis();
        while (((System.currentTimeMillis() - startTime) < x * 1000)
                && !in.ready()) {
            if( displayTimeAlert && ((System.currentTimeMillis() - startTime) > (50) * 1000) ){
                Display.time(10);
                displayTimeAlert = false;
            }
        }
        if (in.ready()) userCommand = in.readLine();
        else userCommand = "";
        return userCommand;
    }
}
