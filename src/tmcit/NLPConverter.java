package tmcit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class NLPConverter {

	static {


	}


	static String ptoj(String protocolString) {


		return "";
	}

	static String jtop(String japaneseString) {
        Runtime runtime = Runtime.getRuntime();
        String[] command = { "cmd", "/c", "echo " +  japaneseString + "|juman|knp -tab" };

        Process p = null;
        try {
            p = runtime.exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        InputStream is = p.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        while (true) {
            String line = "";
			try {
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
            if (line == null) {
                break;
            } else {
                System.out.println("line : " + line);
            }
        }


		return "";
	}
}
