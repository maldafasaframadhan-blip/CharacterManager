# Character Manager — Java Visual SIH-6406

**Universitas Prima Indonesia (UNPRI)**

Project Character Manager merupakan aplikasi berbasis Java yang dikembangkan sebagai implementasi pembelajaran Object-Oriented Programming (OOP). Pengembangan dilakukan secara bertahap melalui beberapa sprint tugas praktikum.

## Daftar Tugas

- [TP3 — Class Skeleton](#tp3--class-skeleton)
- [TP5 — Character Manager](#tp5--character-manager)
- [TP8 — MainWindow dan GUI](#tp8--mainwindow-dan-gui)
- [TP10 — AppTheme](#tp10--apptheme)
- [TP12 — Responsive Layout](#tp12--responsive-layout)
- [TP14 — Input Validation](#tp14--input-validation)

---

## TP3 — Class Skeleton

Membangun struktur dasar aplikasi menggunakan konsep Object-Oriented Programming (OOP).

Implementasi:
- CharacterType menggunakan enum.
- Character sebagai model class.
- Constructor, getter, setter, dan toString().

### Screenshot
![TP3](Screenshot/output_tp3.png.png)

---

## TP5 — Character Manager

Mengembangkan pengelolaan data karakter menggunakan class CharacterManager.

Implementasi:
- addCharacter()
- findByName()
- removeCharacter()
- getTotalCharacters()
- getAllCharacters()
- equals() untuk membandingkan nama karakter.
- Validasi agar nama karakter duplikat tidak ditambahkan.

### Screenshot

**Pengujian CRUD**

![TP5 CRUD](Screenshot/output_tp5_crud.png.png)

**Analisis**

![TP5 Analisis](Screenshot/output_tp5_analisis.png.png)

---

## TP8 — MainWindow dan GUI

Mengembangkan aplikasi dari console menjadi Graphical User Interface (GUI) menggunakan Java Swing.

Implementasi:
- MainWindow menggunakan JFrame.
- Menampilkan data karakter secara dinamis.
- Menggunakan SwingUtilities.invokeLater().
- Menerapkan konsep pemisahan model dan view.

### Screenshot

**Sebelum**

![TP8 Sebelum](Screenshot/output_tp8_sebelum.png.png)

**Sesudah**

![TP8 Sesudah](Screenshot/output_tp8_sesudah.png.png)

---

## TP10 — AppTheme

Mengembangkan pengaturan tema aplikasi melalui class AppTheme agar pengaturan tampilan lebih terpusat.

### Screenshot

**Sebelum**

![TP10 Sebelum](<Screenshot/output_tp10_sebelum.png - Salin.png>)

**Sesudah**

![TP10 Sesudah](Screenshot/output_tp10_sesudah.png.png)

---

## TP12 — Responsive Layout

Mengembangkan tampilan aplikasi agar dapat menyesuaikan ukuran jendela.

### Screenshot

**Ukuran Awal**

![TP12 Ukuran Awal](Screenshot/TP12-01-ukuran-awal.png)

**Jendela Lebar**

![TP12 Jendela Lebar](Screenshot/TP12-02-jendela-lebar.png)

**Jendela Kecil**

![TP12 Jendela Kecil](Screenshot/TP12-03-jendela-kecil.png)

---

## TP14 — Input Validation

Mengembangkan validasi input untuk memastikan data karakter yang dimasukkan sesuai dengan ketentuan aplikasi.

Implementasi:
- Validasi nama kosong.
- Validasi nama duplikat.
- Menampilkan pesan kesalahan ketika input tidak valid.
- Menampilkan pesan keberhasilan ketika karakter berhasil ditambahkan.

### Screenshot

**Validasi Nama Kosong**

![TP14 Nama Kosong](Screenshot/tp14-error-nama-kosong.png)

**Validasi Nama Duplikat**

![TP14 Nama Duplikat](Screenshot/tp14-error-nama-duplikat.png)

**Berhasil Menambahkan Character**

![TP14 Success](Screenshot/tp14-success-tambah-character.png)

---

## Teknologi

- Java
- Java Swing
- Object-Oriented Programming (OOP)
- Visual Studio Code
- Git dan GitHub

---

**Universitas Prima Indonesia (UNPRI)**