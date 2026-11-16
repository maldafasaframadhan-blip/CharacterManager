
package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class Main {

    public static void main(String[] args) {

        // Membuat tiga karakter
        Character hero1 = new Character(
            "Arthur",
            CharacterType.WARRIOR
        );

        Character hero2 = new Character(
            "Merlin",
            CharacterType.MAGE
        );

        Character hero3 = new Character(
            "Robin",
            CharacterType.ARCHER
        );

        // Menampilkan tiga karakter
        System.out.println("================================");
        System.out.println("       CHARACTER MANAGER");
        System.out.println("================================");

        System.out.println("\n=== DAFTAR KARAKTER ===");

        System.out.println(hero1);
        System.out.println(hero2);
        System.out.println(hero3);

        // Demonstrasi level up
        System.out.println("\n=== DEMO LEVEL UP ===");

        System.out.println("Sebelum Level Up:");
        System.out.println(hero1);

        hero1.setLevel(5);

        System.out.println("\nSesudah Level Up:");
        System.out.println(hero1);

        // Demonstrasi constructor kedua
        System.out.println("\n=== CONSTRUCTOR KEDUA ===");

        Character hero4 = new Character(
            "Thor",
            CharacterType.WARRIOR,
            3,
            160,
            25
        );

        System.out.println(hero4);

        // Demonstrasi fitur mana
        System.out.println("\n=== MANA TEST ===");

        hero4.setMana(150);

        System.out.println("Mana: " + hero4.getMana());

        // Demonstrasi isAlive
        System.out.println("\n=== ALIVE TEST ===");

        System.out.println(
            "Apakah Thor masih hidup? " + hero4.isAlive()
        );

        // Demonstrasi karakter mati
        hero4.setHitPoints(0);

        System.out.println(
            "Setelah HP menjadi 0: " + hero4.isAlive()
        );

        // Demonstrasi validasi mana
        System.out.println("\n=== VALIDASI MANA ===");

        try {
            hero4.setMana(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n=== PROGRAM SELESAI ===");
    }
}
