package com.example.hitesh.okhttpregandloginjson.AsyncTasks;


public interface AsyncResponse {
    void onCallback(String response);
    void onFailure(String message);
}
