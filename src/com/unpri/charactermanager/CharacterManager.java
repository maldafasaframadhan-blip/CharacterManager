
package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import java.util.ArrayList;

public class CharacterManager {

    // Menyimpan daftar karakter
    private final ArrayList<Character> characters;

    // Constructor
    public CharacterManager() {
        characters = new ArrayList<>();
    }

    // Memeriksa apakah nama sudah digunakan
    public boolean isNameTaken(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        return findByName(name.trim()) != null;
    }

    // Menambahkan karakter dan menolak nama duplikat
    public boolean addCharacter(Character character) {
        if (character == null) {
            return false;
        }

        if (isNameTaken(character.getName())) {
            System.out.println(
                "Karakter " + character.getName() + " sudah ada!"
            );
            return false;
        }

        characters.add(character);
        return true;
    }

    // Mencari karakter berdasarkan nama
    public Character findByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }

        for (Character character : characters) {
            if (character.getName().equalsIgnoreCase(name.trim())) {
                return character;
            }
        }

        return null;
    }

    // Menghapus karakter berdasarkan nama
    public boolean removeCharacter(String name) {
        Character character = findByName(name);

        if (character != null) {
            characters.remove(character);
            return true;
        }

        return false;
    }

    // Menghitung jumlah karakter
    public int getTotalCharacters() {
        return characters.size();
    }

    // Mengembalikan salinan daftar karakter
    public ArrayList<Character> getAllCharacters() {
        return new ArrayList<>(characters);
    }

    // Menghitung rata-rata level karakter
    public double getAverageLevel() {
        if (characters.isEmpty()) {
            return 0;
        }

        int totalLevel = 0;

        for (Character character : characters) {
            totalLevel += character.getLevel();
        }

        return (double) totalLevel / characters.size();
    }

    // Mencari semua karakter berdasarkan tipe
    public ArrayList<Character> findByType(CharacterType type) {
        ArrayList<Character> result = new ArrayList<>();

        for (Character character : characters) {
            if (character.getType() == type) {
                result.add(character);
            }
        }

        return result;
    }

    // Mencari karakter dengan attack tertinggi
    public Character findStrongest() {
        if (characters.isEmpty()) {
            return null;
        }

        Character strongest = characters.get(0);

        for (Character character : characters) {
            if (character.getAttack() > strongest.getAttack()) {
                strongest = character;
            }
        }

        return strongest;
    }
}
