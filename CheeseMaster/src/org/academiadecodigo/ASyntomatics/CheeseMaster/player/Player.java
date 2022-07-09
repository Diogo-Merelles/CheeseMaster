package org.academiadecodigo.ASyntomatics.CheeseMaster.player;

import org.academiadecodigo.ASyntomatics.CheeseMaster.Prompt.PromptView;
import org.academiadecodigo.ASyntomatics.CheeseMaster.Server.PlayerConnection;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Player {

    private Socket socket;
    private PrintWriter out;
    private int score = 0;
    private PromptView promptView;
    private BufferedReader reader;
    private int roundCount;

    public Player(String host, int portNumber) throws IOException {

        socket = new Socket(host, portNumber);
        promptView = new PromptView(this);
        roundCount = 1;

    }

    public void start() throws IOException {

        Thread threadPrompt = new Thread(new PromptHandler(socket));
        threadPrompt.start();

        System.out.println("  ____   _   _   _____   _____   ____    _____ ");
        System.out.println("/ ___| | | | | | ____| | ____| / ___|  |  ___| ");
        System.out.println("|  |   | |_| | |  _|   |  _|   \\___ \\  |  _|   ");
        System.out.println("|  |__ |  _  | | |___  | |___   ___) | | |___ ");
        System.out.println("\\___ | |_| |_| |_____| |_____| |____/  |_____|");
        System.out.println(" _    _      -       ____   _____   _____   ___                ");
        System.out.println("|  \\/  |    / \\    / ___|  |_   _| | ____| |  _ \\ ");
        System.out.println("| |\\/| |   / _ \\   \\___ \\    | |   |  _|   | |_) |");
        System.out.println("| |  | |  / ___ \\   ___) |   | |   | |___  |  _ < ");
        System.out.println("|_|  |_| /_/   \\_\\ |____/    |_|   |_____| |_| \\_\\" + "\r\n");


        System.out.println("\r\n");



        System.out.println("Waiting for more players to join...");

        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while (socket.isBound()) {
            listen(reader);
        }
    }

    public void listen(BufferedReader reader) throws IOException {

        String message = reader.readLine();

        if (message == null) {
            System.out.println("connection closed from server");
            System.exit(0);
        }
        if (message.equals("Start")) {
            startGame();
            roundCount++;
        }


        if (message.equals("NextRound")) {


            switch (roundCount){
                case 2:
                    startGame2();
                    roundCount++;
                    break;
                case 3:
                    startGame3();
                    roundCount++;
                    break;
                case 4:
                    startGame4();
                    roundCount++;
                    break;
                case 5:
                    startGame5();
                    roundCount++;
                    if(roundCount >= 5) {
                        endGame();
                    }
                    break;
                   /*
                case 6:
                    endGame();
                    socket.close();
                    break;

                     */
                default:
                    System.out.println("Até já Luis...");
                    break;
            }
        }

        System.out.println(message);


    }

    public int getScore() {
        return score;
    }

    public int setScore(int score) {
        return this.score = score;
    }

    public void startGame() {

        if (promptView.round1()) {
            setScore(getScore() + 1);
            out.println("Won round");
        } else {
            out.println("You lost the round");

        }
    }
    public void startGame2(){

        if (promptView.round2()) {
            setScore(getScore() + 1);
            out.println("Won round");
        } else {
            out.println("You lost the round");
        }
    }

    public void startGame3(){

        if (promptView.round3()) {
            setScore(getScore() + 1);
            out.println("Won round");
        } else {
            out.println("You lost the round");
        }
    }

    public void startGame4() {

        if (promptView.round4()) {
            setScore(getScore() +1);
            out.println("Won Round");
        }

        out.println("You lost the round");
    }

    public void startGame5() {

        if (promptView.round5()) {
            setScore(getScore() +1);
            out.println("Won Round");

        }

        out.println("You lost the round");
    }

    public void endGame() throws IOException {


            System.out.println("  /(  ___         ");
            System.out.println("  | >:===========`");
            System.out.println("  )(              ");

    }
}
