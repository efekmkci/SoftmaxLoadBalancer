package com.uyglm;

public interface LoadBalancer {
    int selectServer(); // Bir sunucu seçer
    void update(int serverIdx, double latency); // Performans verisini günceller
    String getName(); // Algoritma adını döndürür
}