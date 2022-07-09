package org.academiadecodigo.ASyntomatics.CheeseMaster.Prompt;

import org.academiadecodigo.ASyntomatics.CheeseMaster.player.Player;
import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

public class PromptView {

    private Player player;
    Prompt prompt;

    public PromptView( Player player){
        this.player = player;
        this.prompt = new Prompt(System.in, System.out);

    }
    public boolean conditional(String number, MenuInputScanner scanner) {

        if(!Integer.toString(prompt.getUserInput(scanner)).equals(number)) {
            System.out.println("Got it wrong! No cheese for you :(");
            return false;

        }else {
            System.out.println("You got it right! Here's your cheese!");
            System.out.println("You won " + player.setScore(player.getScore()+1) + " cheese!");
            return true;
        }
    }

    public boolean round1() {
        System.out.println("=================================================");
        System.out.println("=================================================");
        MenuInputScanner round1 = new MenuInputScanner(QuestionRounds.round1);
        round1.setMessage(QuestionRounds.Q1);
        return conditional("4", round1);
    }

    public boolean round2() {

        System.out.println("=================================================");
        System.out.println("=================================================");
        MenuInputScanner round2 = new MenuInputScanner(QuestionRounds.round2);
        round2.setMessage(QuestionRounds.Q2);
        return conditional("1", round2);
    }

    public boolean round3() {

        System.out.println("=================================================");
        System.out.println("=================================================");
        MenuInputScanner round3 = new MenuInputScanner(QuestionRounds.round3);
        round3.setMessage(QuestionRounds.Q3);
        return conditional("2", round3);
    }

    public boolean round4() {

        System.out.println("=================================================");
        System.out.println("=================================================");
        MenuInputScanner round4 = new MenuInputScanner(QuestionRounds.round4);
        round4.setMessage(QuestionRounds.Q4);
        return conditional("3", round4);
    }

   public boolean round5() {

       System.out.println("=================================================");
       System.out.println("=================================================");
       MenuInputScanner round5 = new MenuInputScanner(QuestionRounds.round5);
       round5.setMessage(QuestionRounds.Q5);
       return conditional("2", round5);

   }

}
