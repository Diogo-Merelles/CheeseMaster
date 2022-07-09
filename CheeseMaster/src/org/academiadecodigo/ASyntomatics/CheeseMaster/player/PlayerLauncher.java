package org.academiadecodigo.ASyntomatics.CheeseMaster.player;

import java.io.IOException;

public class PlayerLauncher {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Usage: <host> <port>");
            return;
        }

        try {
            Player player = new Player(args[0], Integer.valueOf(args[1]));
            player.start();

        } catch (IOException e) {
            System.err.println("Error connecting to server: " + e.getMessage());

        } catch (NumberFormatException e) {
            System.err.println("Error port must be a valid number: " + args[1]);
        }
    }
}
