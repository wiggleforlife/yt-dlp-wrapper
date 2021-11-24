import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import static javax.swing.JOptionPane.showMessageDialog;


public class DownloadVideo {

    static URLWindow win1;
    static DownloadWindow win2;


    public static void main(String[] args) {
        InstallerWindow win = new InstallerWindow();
        //TODO REPLACE HOMEBREW STUFF WITH https://github.com/yt-dlp/yt-dlp/releases/download/2021.11.10.1/yt-dlp AND CHMOD +X
        try {
            if (run("which", "yt-dlp")) {
                win.updateProg(100);
            } else {
                win.updateProg(50);
                win.updateStatus("Installing yt-dlp...");
                run("pwd", true);
                run("curl", "https://github.com/yt-dlp/yt-dlp/releases/download/2021.11.10.1/yt-dlp", ">", "yt-dlp", false);
                win.updateProg(100);
                win.updateStatus("Finished.");
                sleep(500);
            }
        } catch (IOException e) {
            System.out.print(e);
        }
        win.close();
        win1 = new URLWindow();
    }

    public static void run(String prog, boolean output) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(prog);
        runBuilder(builder, output);
    }

    public static void run(String prog, String arg, String arg1, String arg2, boolean output) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(prog, arg, arg1, arg2);
        runBuilder(builder, output);
    }

    public static boolean run(String prog, String arg) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(prog, arg);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        InputStream is = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line = null;
        String out = "";
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            out += line;
        }
        if (out.startsWith("/")) {return true;}
        else {return false;}
    }

    public static void run(String prog, String arg, String arg1, boolean out) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(prog, arg, arg1);
        runBuilder(builder, out);
    }

    private static void runBuilder(ProcessBuilder builder, boolean output) throws IOException {
        builder.redirectErrorStream(true);
        Process process = builder.start();
        InputStream is = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        if (output) {
            String line = null;
            String out = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                out += "\n" + line;
            }
        }
    }

    private static void outputBuilder(ProcessBuilder builder) {
        builder.redirectErrorStream(true);
        try {
            Process process = builder.start();
            InputStream is = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                win2.addToField(line);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void download(String url) {
        win1.close();
        win2 = new DownloadWindow();
        Thread ytdl = new Thread() {
            public void run() {
                outputBuilder(new ProcessBuilder("yt-dlp", url, "-f", "mp4", "-o", "~/Downloads/%(id)s.%(ext)s"));
                showMessageDialog(null, "Process finished. Please check the output in the downloader window before closing this message.\nThe video should be in your Downloads folder.");
                win2.close();
                outputBuilder(new ProcessBuilder("rm", "yt-dlp"));
            }
        };
        ytdl.start();
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {

        }
    }

}
