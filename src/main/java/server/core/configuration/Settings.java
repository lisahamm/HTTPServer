package server.core.configuration;

public class Settings {
    public static int portNumber = 5000;
    public static String publicDirectory = "src/main/resources/cobspec";

    public static void configureFromCommandLine(String[] args) {
        if (args.length > 3) {
            switch(args[0]) {
                case "-p":
                    Settings.portNumber = Integer.parseInt(args[1]);
                    Settings.publicDirectory = args[3];
                    break;
                case "-d":
                    Settings.publicDirectory = args[1];
                    Settings.portNumber = Integer.parseInt(args[3]);
                    break;
                default:
                    break;
            }
        }
    }
}
