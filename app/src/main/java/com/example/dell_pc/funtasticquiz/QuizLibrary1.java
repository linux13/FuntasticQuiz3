package com.example.dell_pc.funtasticquiz;

import java.util.Arrays;

public class QuizLibrary1 {
    long[] arr2 = new long[25];

    QuizLibrary1() {
        Arrays.fill(arr2, -1);
        arr2[2] = R.drawable.l2brazil;
        arr2[4] = R.raw.l2anthem;
        arr2[6] = R.drawable.l2celebrity;
        arr2[8] = R.raw.l2video;
        arr2[12] = R.drawable.l2flag;
        arr2[14] = R.raw.l2cricket;
        arr2[18] = R.drawable.l2seattle;
    }

    public String mQuestions[] = {

            "You maybe wrong,but you may be right",
            "Which is the largest bone in the human body?",
            "Statue belongs to which country?",
            "The First ever computer programmer is a female. And she is___?",
            "The instrumental represents the anthem of __ ?",
            "Who was the first man to fly around the earth with a spaceship?",
            "Which two actress have we blended together here?",
            "Who is the only US President to serve more than two terms?",
            "The dialogue of blur video is from __?",
            "How many heart does an Octopus have?",
            "Who wrote the famous book- WE THE PEOPLE?",
            "The gas used for artificial ripening of green fruit is?",
            "Swirl flag belongs to which country?",
            "Who is the first indian woman to win an asian games gold in 400m run",
            "The song represents ___ division BPL theme song",
            "HAKUNA MATATA means?",
            "My greatest strength is the love for my people, my greatest weakness is that i love them too much- a quote by?",
            "Ottawa is the capital of ___?",
            "The picture shows us a view of ____?",
            "In Bangladesh Sylhet division is known as ____?",
    };
    public String mChoices[][] = {

            {"Ability", "Probability", "Expectation", "Possibility"},
            {"Skull", "Spine", "Femur", "Ribs"},
            {"Rio De Generio", "Cape Town", "Hong Kong", "Argentina"},
            {"Ada Samuel", "Ada Lovelace", "Ada Byron", "Ada Winson"},
            {"India", "Brazil", "Indonesia", "Bangladesh"},
            {"Galileo", "Gagarin", "Neil Amstrong", "Alan Shepard"},
            {"Adele & Taylor Swift", "Rebel Wilson & Margot Robie", "Penney Fortnite & Zac Effron", "Taylor Swift & Kate Winslet"},
            {"Abraham Lincon", "George Washington", "Donald Trump", "Franklin D. Roosevelt"},
            {"Catch me if you can", "Imitation", "Titanic", "The great gatsby"},
            {"1", "3", "2", "6"},
            {"T N Kaul", "J.R.D Ta Ta", "Khushwant Singh", "Nani Pulkhivala"},
            {"Ethylen", "Ethane", "Carbon Dioxide", "Acctylene"},
            {"United Kingdom", "Australia", "France", "United States"},
            {"M L Valsamma", "P T Usha", "Kamaljit Sandhil", "K Maleshwari"},
            {"Dhaka", "khulna", "Rajshahi", "Sylhet"},
            {"Have a good day.", "Have a good life.", "Good Morning.", "Have a good trip."},
            {"Sheikh Mujibur Rahman", "Abraham Lincon", "Barack Obama", "Vladimir Putin"},
            {"Sweden", "Canada", "New Zealand", "Australia"},
            {"Seattle", "New York", "PortLand", "Michigan"},
            {"City of Light", "City of aroma", "City of hills", "City of saint"},

    };
    public String mCorrectAnsers[] = {

            "Possibility",
            "Femur",
            "Rio De Generio",
            "Ada Lovelace",
            "Bangladesh",
            "Gagarin",
            "Rebel Wilson & Margot Robie",
            "Franklin D. Roosevelt",
            "Titanic",
            "3",
            "Nani Pulkhivala",
            "Ethylen",
            "United Kingdom",
            "Kamaljit Sandhil",
            "Sylhet",
            "Have a good day.",
            "Sheikh Mujibur Rahman",
            "Canada",
            "Seattle",
            "City of saint",
    };
    public String mHints[] = {
            ("Chance"),
            ("It is also the strongest one"),
            ("Brazil"),
            ("She was an english mathematician and writer"),
            ("The red and green symbol"),
            ("His other occupation was pilot"),
            ("She also acted in SUICIDE SQUAD"),
            ("He died in 1945"),
            ("Remember,the heart shaped neckpiece?"),
            ("1+1+*"),
            ("His occupation was jurist,economist"),
            ("C2H4"),
            ("Wales is situated in it"),
            ("She went to the 1982 Asian Games as the coach of the indian womens sprint team"),
            ("Lakkatura"),
            ("Shwahili Language phrase"),
            ("The father of Nation"),
            ("Waterfall"),
            ("on the West Coast"),
            ("Shahjalal"),

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
        return arr2[a];
    }

    public String getHints(int a) {
        return mHints[a];
    }
}
