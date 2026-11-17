## TP10 â€“ Character Manager Sprint #4: Theming, Properti Komponen & Analisis MVC

### A. Deskripsi

Pada TP10 ini, aplikasi Character Manager dikembangkan dengan menerapkan sistem tema menggunakan class `AppTheme`. Pengembangan berfokus pada pengaturan warna, font, border, serta tampilan kartu karakter berdasarkan role. Selain itu, aplikasi dilengkapi dengan penanda khusus untuk karakter yang memiliki level 5 atau lebih.

### B. Implementasi Fitur

Fitur yang diterapkan pada Sprint 4:

1. **AppTheme** â€“ Mengatur warna, font, dan border aplikasi dalam satu class.
2. **Role-Based Color** â€“ Setiap karakter memiliki warna kartu berdasarkan role, seperti WARRIOR, ARCHER, dan MAGE.
3. **High-Level Highlight** â€“ Karakter dengan level minimal 5 ditampilkan menggunakan tulisan tebal berwarna emas.
4. **Dynamic Character Data** â€“ Data karakter ditampilkan secara dinamis menggunakan perulangan.
5. **Component Properties** â€“ Menambahkan tooltip pada kartu karakter dan label nama untuk memberikan informasi tambahan ketika kursor diarahkan ke komponen.

### C. Screenshot Sebelum dan Sesudah

**1. Tampilan Sebelum Perubahan (Sprint 3 / v0.3)**

![TP10 Sebelum](<Screenshot/output_tp10_sebelum.png - Salin.png>)

**2. Tampilan Setelah Perubahan (Sprint 4 / v0.4)**

![TP10 Sesudah](Screenshot/output_tp10_sesudah.png.png)

### D. Analisis Implementasi

**1. Pemisahan Theme ke AppTheme**

Pengaturan warna, font, dan border diletakkan di class `AppTheme` agar pengelolaan tampilan aplikasi lebih terpusat. Dengan demikian, perubahan desain dapat dilakukan tanpa mengubah class Model. Hal ini juga membantu menjaga pemisahan tanggung jawab antara Model dan View dalam konsep MVC.

**2. Properti Tambahan Komponen**

Dua properti tambahan yang diterapkan adalah:

- `setToolTipText()` pada kartu karakter untuk menampilkan informasi karakter ketika kursor diarahkan ke kartu.
- `setToolTipText()` pada label nama untuk menampilkan informasi role karakter.

Properti tersebut membantu pengguna memperoleh informasi tambahan tanpa memenuhi tampilan utama aplikasi.

### E. Teknologi

- Java
- Java Swing
- JavaBeans Properties
- Model-View-Controller (MVC)
- Visual Studio Code

### F. Dokumentasi Analisis TP10

Dokumen analisis TP10 yang membahas JavaBeans, properti komponen Swing, pengaturan ukuran komponen, dan penerapan konsep MVC:

**Google Docs:** [https://1drv.ms/w/c/2131cfafda44bcb4/IQBe7LYcm5ZcQadYf8-xkqaSAQ88HBPRUobSkjbLD-A8A2w?e=vOPHmE]

