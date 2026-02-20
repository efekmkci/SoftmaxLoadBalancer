package com.uyglm;
import java.util.Random;

public class Server {
    private double currentLatency;
    private final Random random = new Random();

    public Server(double baseLatency) {
        this.currentLatency = baseLatency;
    }

    public double getResponse() {
        // Non-stationary: Gecikme zamanla rastgele kayar
        currentLatency += (random.nextDouble() - 0.5) * 2;
        if (currentLatency < 5) currentLatency = 5;

        // Gürültü (Noise): Her isteğe rastgele sapma eklenir
        double noise = random.nextGaussian() * 3;
        return currentLatency + Math.abs(noise);
    }
}