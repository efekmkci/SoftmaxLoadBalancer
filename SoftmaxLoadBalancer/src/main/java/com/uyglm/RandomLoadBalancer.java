package com.uyglm;
import java.util.Random;

public class RandomLoadBalancer implements LoadBalancer {
    private final int k;
    private final Random random = new Random();

    public RandomLoadBalancer(int k) { this.k = k; }

    @Override public int selectServer() { return random.nextInt(k); }
    @Override public void update(int serverIdx, double latency) { }
    @Override public String getName() { return "Random Algoritması"; }
}