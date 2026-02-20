package com.uyglm;

public class Main {
    public static void main(String[] args) {
        int k = 5;
        int steps = 1000;

        Server[] servers = new Server[k];
        servers[0] = new Server(10); // Hızlı sunucu
        servers[1] = new Server(50); // Yavaş sunucu
        servers[2] = new Server(15);
        servers[3] = new Server(30);
        servers[4] = new Server(100); // Çok yavaş

        System.out.println("Simülasyon Başlıyor...\n");
        runTest(new SoftmaxLoadBalancer(k), servers, steps);
        runTest(new RandomLoadBalancer(k), servers, steps);
    }

    private static void runTest(LoadBalancer lb, Server[] servers, int steps) {
        double totalLatency = 0;
        for (int i = 0; i < steps; i++) {
            int selected = lb.selectServer();
            double latency = servers[selected].getResponse();
            lb.update(selected, latency);
            totalLatency += latency;
        }
        System.out.printf("%s -> Ortalama Gecikme: %.2f ms\n", lb.getName(), (totalLatency / steps));
    }
}