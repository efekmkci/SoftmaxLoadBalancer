package com.uyglm;
import java.util.Arrays;

public class SoftmaxLoadBalancer implements LoadBalancer {
    private final double[] qValues;
    private final double tau = 2.0; // Sıcaklık parametresi (Exploration/Exploitation)
    private final double alpha = 0.1; // Öğrenme oranı
    private final int k;

    public SoftmaxLoadBalancer(int k) {
        this.k = k;
        this.qValues = new double[k];
        Arrays.fill(qValues, 0.0);
    }

    @Override
    public int selectServer() {
        // NUMERICAL STABILITY: exp() taşmasını önlemek için maxQ çıkarılır
        double maxQ = Arrays.stream(qValues).max().orElse(0.0);
        double[] expValues = new double[k];
        double sumExp = 0;

        for (int i = 0; i < k; i++) {
            expValues[i] = Math.exp((qValues[i] - maxQ) / tau);
            sumExp += expValues[i];
        }

        // Olasılıksal Seçim
        double r = Math.random() * sumExp;
        double currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += expValues[i];
            if (currentSum > r) return i;
        }
        return k - 1;
    }

    @Override
    public void update(int serverIdx, double latency) {
        double reward = 100.0 / latency; // Düşük gecikme = Yüksek ödül
        qValues[serverIdx] += alpha * (reward - qValues[serverIdx]);
    }

    @Override public String getName() { return "Softmax Algoritması"; }
}