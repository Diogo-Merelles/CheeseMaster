package org.academiadecodigo.ASyntomatics.CheeseMaster.player;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class PromptHandler implements Runnable {

    Socket socket;

    public PromptHandler(Socket socket) {

        this.socket = socket;

    }

    @Override
    public void run() {

        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner (System.in);


            while (!socket.isClosed()) {
                String input = scanner.nextLine();
                if(input.equals("exit")){
                    System.exit(0);
                }
                out.println(input);
            }


        } catch (IOException ex) {
            System.out.println(" Error in message input : " + ex.getMessage());
        }
    }
}
