package com.example.jellyhunter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.jellyhunter.gameUtils.UserStats;
import com.example.jellyhunter.utilities.MSP;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Fragment_Map extends Fragment implements OnMapReadyCallback {
    private GoogleMap googleMap;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

        return view;
    }

    public void goToCoords(double lat, double lng) {
        LatLng pos = new LatLng(lat, lng);
        googleMap.addMarker(new MarkerOptions().position(pos));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        this.googleMap = googleMap;
        drawMarkers();
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(32, 35), 6));
    }

    private void drawMarkers() {
        MSP msp = MSP.getInstance();
        UserStats usr;
        for (int i = 0; i < 10; i++) {
            usr = new UserStats(msp.readString("STATS"+i,"0/0/0/0"));
            LatLng pos = new LatLng(usr.getLat(), usr.getLng());
            googleMap.addMarker(new MarkerOptions().position(pos));
        }
    }
}
