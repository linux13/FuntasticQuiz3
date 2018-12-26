package com.example.dell_pc.funtasticquiz;

import java.util.Arrays;

public class QuizLibrary2 {
    long[] arr3 = new long[25];

    QuizLibrary2() {
        Arrays.fill(arr3, -1);
        arr3[2] = R.drawable.l3play;
        arr3[4] = R.raw.l3speech;
        arr3[6] = R.drawable.l3celebrity;
        arr3[8] = R.raw.l3video;
        arr3[12] = R.drawable.l3cartoon;
        arr3[14] = R.raw.l3theme;
        arr3[18] = R.drawable.l3flag;

    }

    private String mQuestions[] = {
            "Which is the largest reptile in the world?",
            "The internet was originally a project of which agency?",
            "The picture shows the casts of the movie ___?",
            "Which of the gas is not an greenhouse gas?",
            "From this sound clip we hear the speech of ____?",
            "In the harry potter books,which school house is represented by a Badger?",
            "Guess the celebrity?",
            "What is part of a database that holds only one type if information?",
            "The clip shows the partial campus of ___?",
            "'.mov' extension refers usually to what kind of file?",
            "If Bob is taller than Joe,Joe is less taller than Bob,and Steve's dog is littlest but Steve is tallest than how many people are there?",
            "Which French woman was the most famous actress in the world in the late 19th and early 20th century?",
            "Which cartoon character is he?",
            "In which city did Romeo and Juliet live?",
            "This is the theme music of ___?",
            "What kind of animal is the largest living creature on earth?",
            "In which country did Shakespeare's Hamlet live?",
            "Which device was invented by Harry Mill?",
            "This is the Flag of ___?",
            "In which country were the first Olympic Games held?",
    };
    private String mChoices[][] = {
            {"Iguana", "Gila Monstar", "Saltwater Crocodile", "Alligator"},
            {"ARPA", "NSF", "NSA", "None of these"},
            {"Romeo and Juliet", "Anna Karenina", "War and Peace", "Phantom of the Opera"},
            {"Methane", "Nitrous Oxide", "Carbon Oxide", "Hydrogen"},
            {"Sheikh Mujibur Rahman", "Tajuddin Ahmed", "MAulana Vasani", "M.A.G. Osmani"},
            {"Ravenclaw", "Hufflepuff", "Gryffindor", "Rainbow"},
            {"Kate Middleton", "Jennifer Winget", "Lily Collins", "Sia"},
            {"Report", "Field", "Record", "File"},
            {"North South University", "North East University", "Metro University", "Leading University"},
            {"Image", "Animation", "Audio", "MS Office"},
            {"3", "4", "None of these ", "5"},
            {"Jane campion", "Antonio", "Sarah Bernhardt", "Seaman"},
            {"Homer Simpson", "Bart Simpson", "Marge Simpson", "Lisa Simpson"},
            {"Paris", "Greece", "Verona", "Picasso"},
            {"Harry Potter", "Game of thrones", "Lord of the rings", "Narnia"},
            {"Golden Eagle", "Whale", "Bear", "Leopards"},
            {"Denmark", "Cuba", "Greece", "Boon"},
            {"Barometer", "Steam Engine", "The Typewriter", "Air Ballon"},
            {"Czech Republic", "Italy", "Spain", "Mexico"},
            {"Japan", "Chin", "Greece", "UK"},

    };
    private String mCorrectAnsers[] = {
            "Saltwater Crocodile",
            "ARPA",
            "Phantom of the Opera",
            "Hydrogen",
            "Sheikh Mujibur Rahman",
            "Hufflepuff",
            "Kate Middleton",
            "Field",
            "Leading University",
            "Animation",
            "3",
            "Sarah Bernhardt",
            "Bart Simpson",
            "Verona",
            "Game of thrones",
            "Whale",
            "Denmark",
            "The Typewriter",
            "Czech Republic",
            "Greece",};
    public String mHints[] = {
            ("Crocodylus porosus"),
            ("Former name of the defense Advanced Research Projects agency"),
            ("It is a novel by French writter Gaston Leroux"),
            ("standard atomic weight of 1.008"),
            ("ex priminister"),
            ("Hp"),
            ("R*** DeWitt Bukater"),
            ("Support ACID properties"),
            ("Kamal Bazar"),
            ("Cartoon"),
            ("Spelling contains double E"),
            ("Cause of death was uremia"),
            ("4th grade student"),
            ("mayor isFederico Sboarina"),
            ("Jamie Lannister"),
            ("Air breathing marine mammals"),
            ("population 5.8 million"),
            ("format 7 inch"),
            ("Prague"),
            ("Hellenic Republic"),
    };


    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }

    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }

    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getChoice4(int a) {
        String choice3 = mChoices[a][3];
        return choice3;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnsers[a];
        return answer;
    }

    public long getIVS(int a) {
        return arr3[a];
    }

    public String getHints(int a) {
        return mHints[a];
    }
}







