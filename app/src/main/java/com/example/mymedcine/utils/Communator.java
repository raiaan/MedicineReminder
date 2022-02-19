package com.example.mymedcine.utils;

import com.example.mymedcine.model.Prescription;

public interface Communator {
    void sendMessage(Prescription prescription);
}
