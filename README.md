# Softmax Action Selection Load Balancer Simulation

Bu proje, dağıtık sistemlerde K adet sunucudan oluşan bir kümeye gelen istekleri, toplam gecikme süresini (latency) minimize edecek şekilde dağıtan bir **Client-Side Load Balancer** uygulamasıdır.

## 🎯 Projenin Amacı
Sunucuların performansları (yanıt süreleri) sabit değildir; zamanla değişir (non-stationary distribution) ve gürültülüdür. Bu projenin amacı, statik algoritmalar yerine geçmiş performans verilerinden öğrenen ve toplam ödülü (reward) maksimize eden bir yapı kurmaktır.

## 🛠️ Kullanılan Teknolojiler
* **Programlama Dili:** Java
* **IDE:** IntelliJ IDEA 2025.2.2
* **Yapay Zeka (Agentic Coding):** Gemini 1.5 Pro (Mimari tasarım ve hata ayıklama süreçlerinde asistan olarak kullanılmıştır)

## 🧠 Algoritma Detayları

### Softmax Action Selection Nedir?
Softmax, her sunucunun geçmişteki performansına (Q-değerleri) dayanarak olasılıksal bir seçim yapar. Yüksek performans gösteren sunucuların seçilme olasılığını artırırken, sistemdeki değişimleri takip etmek için diğer sunucuları da keşfetmeye (exploration) devam eder.

### Neden Round-Robin veya Random Değil?
* **Round-Robin/Random:** Sunucuların o anki yükünü veya yanıt sürelerini dikkate almazlar.
* **Softmax:** Zamanla değişen sunucu performanslarını öğrenir ve yükü en hızlı yanıt veren sunuculara yönlendirerek toplam gecikmeyi düşürür.

### Teknik Zorluk: Nümerik Stabilite (Numerical Stability)
Üstel fonksiyon ($e^x$) hesaplamalarında, Q değerleri büyüdüğünde Java'daki `Math.exp()` fonksiyonu `Infinity` (sonsuz) hatası verebilir. Bu problem, her bir Q değerinden o anki maksimum Q değerinin çıkarılması ($Q - \max(Q)$) yöntemiyle çözülmüş ve sistemin kararlı çalışması sağlanmıştır.  $$Softmax(Q_i) = \frac{e^{(Q_i - \max(Q))/\tau}}{\sum e^{(Q_j - \max(Q))/\tau}}$$

## 📊 Çalışma Zamanı Analizi (Sonuçlar)
2000 istek üzerinden yapılan simülasyon sonuçları aşağıdadır:
* **Softmax Algoritması:** ~19.21 ms Ortalama Gecikme
* **Random Algoritması:** ~41.43 ms Ortalama Gecikme

Softmax algoritması, rastgele seçime göre gecikme süresini yaklaşık **%53 oranında iyileştirmiştir**.
