import java.io.*;

public class BashScript {

    public static void executeCommand(BashCommand command) throws Exception {

        String com = String.format("%s %s %s %s %s %s ", command.getCom(),
                command.getArg(1), command.getArg(2), command.getArg(3), command.getArg(4), command.getArg(5));

        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", com);

        try {
            pb.inheritIO();
            Process process = null;
            process = pb.start();
            process.waitFor();
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void executeSudoCommand(BashCommand command, String sudoPass) throws Exception {
        String com = String.format("%s %s %s %s %s %s ", command.getCom(),
                command.getArg(1), command.getArg(2), command.getArg(3), command.getArg(4), command.getArg(5));
        String sCom = String.format("PASWD='%s' && echo $PASWD | sudo -S %s", sudoPass, com);

        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", sCom);
        pb.redirectErrorStream(true);

        Process process  = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            pb.inheritIO();

            // handle wrong password
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
                if (output.toString().contains("Sorry, try again.")) {
                    // log error
                    System.out.println("Authentication failed: Wrong password detected.");
                    // stop executing
                    process.destroy();
                    throw new Exception("Authentication failed! Aborting.");
                }
            }
            // log output
            System.out.println("Process Output:\n" + output.toString());

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static Boolean executeSudoGrepCommand(BashCommand command, String sudoPass, String grep) throws Exception {
        String com = String.format("%s %s %s %s %s %s ", command.getCom(),
                command.getArg(1), command.getArg(2), command.getArg(3), command.getArg(4), command.getArg(5));
        String sCom = String.format("PASWD='%s' && echo $PASWD | sudo -S %s", sudoPass, com);;
        String pCom = String.format("%s | grep %s", sCom, grep);

        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", pCom);
        pb.redirectErrorStream(true);

        Process process  = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            pb.inheritIO();

            // detect grep out
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
                if (output.toString().contains("Sorry, try again.")) {
                    // log error
                    System.out.println("Authentication failed: Wrong password detected.");
                    // stop executing
                    process.destroy();
                    throw new Exception("Authentication failed! Aborting.");
                }
                else if (output.toString().contains(grep)) {
                    System.out.println("Process Output:\n" + output.toString());
                    return true;
                }
            }
            // log output
            System.out.println("Process Output:\n" + output.toString());

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static Boolean executeGrepCommand(BashCommand command, String grep) throws Exception {
        String com = String.format("%s %s %s %s %s %s ", command.getCom(),
                command.getArg(1), command.getArg(2), command.getArg(3), command.getArg(4), command.getArg(5));
        String pCom = String.format("%s | grep %s", com, grep);

        ProcessBuilder pb = new ProcessBuilder("/bin/sh", "-c", pCom);
        pb.redirectErrorStream(true);

        Process process  = pb.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            pb.inheritIO();

            // detect grep out
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
                if (output.toString().contains("Sorry, try again.")) {
                    // log error
                    System.out.println("Authentication failed: Wrong password detected.");
                    // stop executing
                    process.destroy();
                    throw new Exception("Authentication failed! Aborting.");
                }
                else if (output.toString().contains(grep)) {
                    System.out.println("Process Output:\n" + output.toString());
                    return true;
                }
            }
            // log output
            System.out.println("Process Output:\n" + output.toString());

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }
}
