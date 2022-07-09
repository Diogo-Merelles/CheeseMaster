package org.academiadecodigo.ASyntomatics.CheeseMaster.Prompt;

public class QuestionRounds {

    public static final String Q1 = "Which of these principles is not from OOP?";
    public static final String A1 = "Composition";

    public static final String Q2 = "What does the constructor do?";
    public static final String A2 = "Initializes the state of an object";

    public static final String Q3 = "What is final keyword in Java??";
    public static final String A3 = "A special keyword in Java that is used as a non-access modifier";

    public static final String Q4 = "What are access modifiers in Java?";
    public static final String A4 = "Default, Private, Protected, Public";

    public static final String Q5 = "What is a Map in Java?";
    public static final String A5 = "Map is an interface of Utilities package which maps unique keys to values";

    /////////////////////////////////

    public static final String[] round1 =  {"Inheritance", "Polymorphism", "Abstraction", QuestionRounds.A1};

    public static final String[] round2 =  {QuestionRounds.A2, "Builds a house", "Initializes the methods of an object",
            "Initializes the class"};

    public static final String[] round3 =  {"A keyword to declare the end of your program", QuestionRounds.A3,
            "It's similar to a break", "It allows to change variables"};

    public static final String[] round4 =  {"Default, Static, Private, Public", "Static, Private, Public, Protected ",
            QuestionRounds.A4, "Public, Protected, Default, Final"};

    public static final String[] round5 =  {"Map is Dora's best friend", QuestionRounds.A5, "A Java container",
            "A super class"};

}
