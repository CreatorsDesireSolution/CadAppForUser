package com.example.cadappforuser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> technology = new ArrayList<String>();
        technology.add("Beats sued for noise-cancelling tech");
        technology.add("Wikipedia blocks US Congress edits");
        technology.add("Google quizzed over deleted links");
        technology.add("Nasa seeks aid with Earth-Mars links");
        technology.add("The Good, the Bad and the Ugly");

        List<String> entertainment = new ArrayList<String>();
        entertainment.add("Goldfinch novel set for big screen");
        entertainment.add("Anderson stellar in Streetcar");
        entertainment.add("Ronstadt receives White House honour");
        entertainment.add("Toronto to open with The Judge");
        entertainment.add("British dancer return from Russia");

        List<String> science = new ArrayList<String>();
        science.add("Eggshell may act like sunblock");
        science.add("Brain hub predicts negative events");
        science.add("California hit by raging wildfires");
        science.add("Rosetta's comet seen in close-up");
        science.add("Secret of sandstone shapes revealed");

        List<String> man = new ArrayList<String>();
        man.add("Face Massage,80 minutes");
        man.add("Face Massage, 50 minutes");
        man.add("Therapeutic Massage,80 minutes");
        man.add("Classic Massage,80 minutes");

        List<String> women = new ArrayList<String>();
        women.add("Eggshell may act like sunblock");
        women.add("Eggshell may act like sunblock");
        women.add("Eggshell may act like sunblock");
        women.add("Eggshell may act like sunblock");
        women.add("Eggshell may act like sunblock");


        expandableListDetail.put("PADICURE", technology);
        expandableListDetail.put("MANICURE", entertainment);
        expandableListDetail.put("MASSAGE", science);
        expandableListDetail.put("MAN", man);
        expandableListDetail.put("MACRO", women);

        return expandableListDetail;
    }
}
