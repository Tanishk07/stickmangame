package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class UserData implements Serializable {
    private ArrayList<IndividualUser> arr;

    UserData() {
        arr = new ArrayList<>();
    }

    public void addUserIn(IndividualUser iu) {
        arr.add(iu);
        sortArr(); // Automatically sort the array when a new user is added
    }

    public ArrayList<IndividualUser> getArr() {
        return arr;
    }

    public int checkUserName(String s) {
        for (int i = 0; i < arr.size(); i++) {
            if ((arr.get(i)).getUserName().equals(s)) {
                return i;
            }
        }
        return -1;
    }

    public void sortArr() {
        Collections.sort(arr, Comparator.comparingInt(IndividualUser::getUserScore).reversed());
    }
}

