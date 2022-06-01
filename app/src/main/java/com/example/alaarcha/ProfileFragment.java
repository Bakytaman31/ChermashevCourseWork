package com.example.alaarcha;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alaarcha.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment implements LifecycleOwner {
//    private FragmentProfileBinding binding;
//
//    public View onCreateView(@NonNull LayoutInflater inflater,
//                             ViewGroup container, Bundle savedInstanceState) {
//        RoomViewModel roomViewModel =
//                new ViewModelProvider(this).get(RoomViewModel.class);
//
//        binding = FragmentProfileBinding.inflate(inflater, container, false);
//        View root = binding.getRoot();
//
//        final RecyclerView recycler = binding.recycler;
//        recycler.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
//        recycler.setHasFixedSize(true);
//        RoomsAdapter roomsAdapter = new RoomsAdapter();
//        recycler.setAdapter(roomsAdapter);
//        roomViewModel.getMutableLiveData().observe(getViewLifecycleOwner(), roomsAdapter::setList);
//
//
//        return false;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        binding = null;
//    }
}