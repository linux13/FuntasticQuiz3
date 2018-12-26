package com.example.dell_pc.funtasticquiz;

import java.util.Arrays;

public class QuizLibrary {

    long[] arr1 = new long[25];

    QuizLibrary() {
        Arrays.fill(arr1, -1);

        arr1[2] = R.drawable.l1himu;
        arr1[4] = R.raw.l1ekla;
        arr1[6] = R.drawable.l1food;
        arr1[8] = R.raw.l1video;
        arr1[12] = R.drawable.l1scene;
        arr1[14] = R.raw.l1titanic;
        arr1[18] = R.drawable.l1celebrity;

    }

    private String mQuestions[] = {

            "The only Country to fight a war and declare independence over language is?",
            "'OS' computer abbreviation usually means?",
            "The cartoon character represents the character of?",
            "Who is developer of yahoo?",
            "Who compose the song of given soundclip?",
            "Plants receive their nutrients mainly from?",
            "The japanese cuisine is known as?",
            "Who is the father of geometry?",
            "The scene is from the popular movie ___",
            "The full form of HTML is _____?",
            "When is the word'Population Day' observed?",
            "'Love is a waste of time' song is sang by?",
            "The picture represents the scene of famous ___?",
            "In 'Charlie and the chocolate factory' movie the role of Willie Wonka was played by?",
            "The soundclip is the theme song of ___ movie",
            "Most modern TV's draw power even if it is turned off,the circuit the power id used in does what function?",
            "2+2*2/1+2*0+2/2",
            "The number of already named bones in the human skeleton is?",
            "Which celebrity does the picture represents?",
            "Google's first office was ___?"
    };


    private String mChoices[][] = {
            {"Africa", "Bangladesh", "Pakistan", "United State"},
            {"Order of significance", "Open Software", "Operating System", "Optical Sensor"},
            {"Himu", "Shuvro", "Misir Ali", "Topu"},
            {"Dennis Ritchie & ken Thompson", "David Filo & Jerry Yang", "Vint Cherf & Robert Khan", "Steve Case & Jeff Bezos"},
            {"Kazi Nozrul Islam", "Anupom Roy", "Shofiqul Islam", "Robindronath Thakur"},
            {"Chorophyll", "Atmosphere", "Light", "Soil"},
            {"Samgyepsal", "Hangover stew", "Ramen", "Kimchi stew"},
            {"Aristotle", "Euclid", "Pythagoras", "Keplar"},
            {"Friendship for forever", "Life of an engineer", "Three idiots", "Why being an student sucks"},
            {"Hyper Text Markup Language", "Hyper Text Manipulation Language", "Hyper Text Managing Links", "Hyper Text Manipulating Link"},
            {"May 31", "October 4", "December 10", "July 11"},
            {"Sonu Nigum", "John Legend", "Alicia Cooper", "James"},
            {"Elope scene", "Dance scene", "Conversation scene", "Balcony scene"},
            {"Leonardo Decaprio", "Tom Cruise", "Jhonny Depp", "Bradley Cooper"},
            {"Titanic", "A walk to remember", "Life as we know it", "La La land"},
            {"Sound", "Remote Control", "Color Balance", "High Voltage"},
            {"5", "10", "7", "9"},
            {"200", "206", "212", "218"},
            {"Sharkira", "Pink", "Rihanna", "Katy Perry"},
            {"Rented Garage", "A condo", "Basement", "In a gymnasium"},
    };

    private String mHints[] = {
            ("Situated in ASIA"),
            ("It provides an interface between user and machine"),
            ("The book was written by Humayun Ahmed"),
            ("One of them was born in April 20,1966"),
            ("He was born on 7th may,1861"),
            ("Inside it,mineral particles are largest ingredient"),
            ("It originally came from china"),
            ("His residence was in Alexandria,Egypt"),
            ("The movie was directed by Rajkumar Hirani"),
            ("It is made of a raw of markup tag"),
            ("Related to the cancer sign"),
            ("He was trained by hindustani classical singer Ustad ghulam mustafa khan"),
            ("Related to a Patio"),
            ("He was also in the movie called 'Pirates of the Caribbean'."),
            ("It was released on 18 nov,1997"),
            ("User can control it from a distance"),
            ("3+3+*"),
            ("20*"),
            ("She owns a makeup Line herself"),
            ("Related to Vehicle"),


    };

    private String mCorrectAnsers[] = {
            "Bangladesh", "Operating System", "Himu", "David Filo & Jerry Yang", "Robindronath THakur", "Soil", "Ramen", "Euclid", "Three idiots",
            "Hyper Text Markup Language", "July 11", "Sonu Nigum", "Balcony scene",
            "Jhonny Depp", "Titanic", "Remote Control", "7", "206", "Rihanna", "Rented Garage"
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
        return arr1[a];
    }

    public String getHints(int a) {
        return mHints[a];
    }
}