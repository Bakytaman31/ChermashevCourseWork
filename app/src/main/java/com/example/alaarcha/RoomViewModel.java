package com.example.alaarcha;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class RoomViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<Room>> Rooms;
    private ArrayList<Room> roomsList;

    public void setList(ArrayList<Room> Room) {
        roomsList = Room;
        init();
    }

    public RoomViewModel() {
        Rooms = new MutableLiveData<>();
        init();
    }

    private void init(){
        populateList();
        Rooms.setValue(roomsList);
    }

    private void populateList(){
        roomsList = new ArrayList<>();
        roomsList.add(new Room("Люкс", "Ала-Арча", 7000, R.drawable.image1));
        roomsList.add(new Room("Полулюкс", "Ала-Арча", 3000, R.drawable.image2));
        roomsList.add(new Room("Семейный", "Ала-Арча", 4000, R.drawable.image3));
        roomsList.add(new Room("Стандарт", "Ала-Арча", 4000, R.drawable.image4));

    }

    public LiveData<ArrayList<Room>> getMutableLiveData() {
        return Rooms;
    }
}
