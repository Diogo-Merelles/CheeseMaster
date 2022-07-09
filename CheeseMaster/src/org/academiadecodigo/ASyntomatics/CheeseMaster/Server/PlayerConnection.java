package org.academiadecodigo.ASyntomatics.CheeseMaster.Server;

import java.io.*;
import java.net.Socket;

public class PlayerConnection implements Runnable{

    private Socket socket;
    private GameServer server;
    private String playerName;
    private PrintWriter out;
    private int countReplies=0;


    public PlayerConnection(Socket socket, GameServer server, String playerName){

        this.playerName = playerName;
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run(){

        try{
            BufferedReader in = openStreams();

            while ( !server.addingPlayers(this)) {
                System.out.println(" Server is full, please hold until it is free, or quit: /q");
                close();
            }

            while(!socket.isClosed()){
                listen(in);
            }

        }catch(IOException ex){
            System.out.println("Player waiting Error: " + ex.getMessage());
        }
    }

    private void listen(BufferedReader in) throws IOException {

        String messages = in.readLine();


        if(messages.equals("Won round")) {
            server.broadCast("Oh yeah!! " + this.getPlayerName() + " won the round");
            server.broadCast("NextRound");
        }

        if(messages.equals("You lost the round")){
            server.broadCast("Ups, " + this.getPlayerName() + " lost the round");
        }
    }

    private BufferedReader openStreams() throws IOException {

        out = new PrintWriter(socket.getOutputStream(), true);
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void send(String message) {
        out.println(message);
        out.flush();
    }

    public String getPlayerName() {
        return playerName;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error closing client socket: " + e.getMessage());
        }
    }
}
