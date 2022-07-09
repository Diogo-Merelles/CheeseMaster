package org.academiadecodigo.ASyntomatics.CheeseMaster.Server;

import org.academiadecodigo.ASyntomatics.CheeseMaster.Server.PlayerConnection;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GameServer {

    private static final int MAXPLAYERS = 300;

    private List<PlayerConnection> players;
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private ExecutorService pool;
    DataOutputStream outing;
    DataInputStream ining;

    public GameServer(int PORT) throws IOException {

        players = Collections.synchronizedList(new LinkedList<>());
        serverSocket = new ServerSocket(PORT);
        pool = Executors.newFixedThreadPool(MAXPLAYERS);
        System.out.println("Server Listening \n" + serverSocket); }

    public void start() {

        int connections = 1;

        while (true) {
            connectionToServer(connections);
            connections++;

        }
    }
    public void connectionToServer(int connections) {
        // accepts clients and connects them to the server and puts them in the pool.
        try {
            clientSocket = serverSocket.accept();
            System.out.println("Player " + connections + " has connected ");
           // messages();

            PlayerConnection playerConnection = new PlayerConnection(clientSocket, this, "Player " + connections);
            pool.submit(playerConnection);

        } catch (IOException io) {
            System.out.println(" Error on connection: " + io.getMessage());
        }
    }

  /*  public void messages() throws IOException {
        ining  = new DataInputStream(clientSocket.getInputStream());;
        outing = new DataOutputStream(clientSocket.getOutputStream());
    }

   */

    // Method to add clients to the server;
    public boolean addingPlayers(PlayerConnection playerConnection) {

        synchronized (players) {

            if (players.size() >= MAXPLAYERS) {
                return false;
            }

            broadCast("Hello there  welcome to the pool ok Knowledge");
            players.add(playerConnection);
            gameReady();

            return true;
        }
    }

    public void broadCast(String message) {

        synchronized (players) {
            for (PlayerConnection player : players) {
                player.send(message);
            }
        }
    }

    public void gameReady() {
        if (players.size() <3) {
            broadCast(" Waiting for more players to join the server...");
        } else {
            broadCast("Start");
        }
    }
}
