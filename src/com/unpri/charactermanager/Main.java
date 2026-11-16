
package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== CHARACTER MANAGER SPRINT 2 =====");

        // Membuat CharacterManager
        CharacterManager manager = new CharacterManager();

        // Membuat karakter
        Character arjuna = new Character("Arjuna", CharacterType.WARRIOR);
        arjuna.setLevel(5);
        arjuna.setMana(50);

        Character srikandi = new Character("Srikandi", CharacterType.ARCHER);
        srikandi.setLevel(3);
        srikandi.setMana(40);

        Character bima = new Character("Bima", CharacterType.WARRIOR);
        bima.setLevel(7);
        bima.setMana(30);

        // A. Menambahkan karakter
        System.out.println("\n--- MENAMBAHKAN KARAKTER ---");

        System.out.println("Arjuna: " + manager.addCharacter(arjuna));
        System.out.println("Srikandi: " + manager.addCharacter(srikandi));
        System.out.println("Bima: " + manager.addCharacter(bima));

        // Menguji nama duplikat
        System.out.println("\n--- UJI DUPLIKAT ---");

        Character duplikat = new Character("Arjuna", CharacterType.MAGE);
        System.out.println("Arjuna duplikat: "
                + manager.addCharacter(duplikat));

        // Menampilkan seluruh karakter
        System.out.println("\n--- DAFTAR KARAKTER ---");

        for (Character character : manager.getAllCharacters()) {
            System.out.println(character);
        }

        // Mencari karakter
        System.out.println("\n--- PENCARIAN KARAKTER ---");

        Character hasil = manager.findByName("Arjuna");

        if (hasil != null) {
            System.out.println("Karakter ditemukan: " + hasil);
        } else {
            System.out.println("Karakter tidak ditemukan");
        }

        // Menghitung jumlah karakter
        System.out.println("\n--- TOTAL KARAKTER ---");
        System.out.println("Total: " + manager.getTotalCharacters());

        // Mini-Challenge rata-rata level
        System.out.println("\n--- RATA-RATA LEVEL ---");
        System.out.println("Rata-rata: " + manager.getAverageLevel());

        // Mini-Challenge mencari berdasarkan tipe
        System.out.println("\n--- KARAKTER WARRIOR ---");

        ArrayList<Character> warriors =
                manager.findByType(CharacterType.WARRIOR);

        for (Character character : warriors) {
            System.out.println(character.getName());
        }

        // Fitur mandiri mencari karakter terkuat
        System.out.println("\n--- KARAKTER TERKUAT ---");

        Character strongest = manager.findStrongest();

        if (strongest != null) {
            System.out.println("Terkuat: " + strongest.getName());
            System.out.println("Attack: " + strongest.getAttack());
        }

        // Menguji defensive copy
        System.out.println("\n--- UJI DEFENSIVE COPY ---");

        ArrayList<Character> salinan = manager.getAllCharacters();
        salinan.clear();

        System.out.println("Jumlah di salinan: " + salinan.size());
        System.out.println("Jumlah di manager: "
                + manager.getTotalCharacters());

        // Menguji reference
        System.out.println("\n--- UJI REFERENCE ---");

        Character a = new Character("Raja", CharacterType.WARRIOR);
        Character b = a;

        b.setLevel(8);

        System.out.println("Level a: " + a.getLevel());
        System.out.println("Apakah a == b? " + (a == b));

        // Menghapus karakter
        System.out.println("\n--- MENGHAPUS KARAKTER ---");

        System.out.println("Hapus Srikandi: "
                + manager.removeCharacter("Srikandi"));

        System.out.println("Total setelah penghapusan: "
                + manager.getTotalCharacters());

        System.out.println("\n===== PROGRAM SELESAI =====");
    }
}
