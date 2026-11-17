
package com.unpri.charactermanager.model;

import java.util.Locale;

public class Character {

    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attack;
    private int mana;

    // Constructor pertama
    public Character(String name, CharacterType type) {
        this(name, type, 1, 100, 10);
    }

    // Constructor kedua
    public Character(String name, CharacterType type,
                     int level, int hitPoints, int attack) {

        validateName(name);
        validateType(type);
        validateLevel(level, type);

        this.name = name.trim();
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attack = attack;
        this.mana = 0;
    }

    // Validasi nama di Model
    private void validateName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Nama karakter tidak boleh kosong!"
            );
        }

        if (name.trim().length() > 20) {
            throw new IllegalArgumentException(
                "Nama karakter maksimal 20 karakter!"
            );
        }
    }

    // Validasi role di Model
    private void validateType(CharacterType type) {
        if (type == null) {
            throw new IllegalArgumentException(
                "Role karakter harus dipilih!"
            );
        }
    }

    // Validasi level di Model
    private void validateLevel(int level, CharacterType type) {
        if (level < 1 || level > 99) {
            throw new IllegalArgumentException(
                "Level harus berada antara 1 sampai 99!"
            );
        }

        if (type == CharacterType.MAGE && level > 60) {
            throw new IllegalArgumentException(
                "Karakter MAGE tidak boleh memiliki level di atas 60!"
            );
        }
    }

    // Getter dan Setter Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        validateName(name);
        this.name = name.trim();
    }

    // Getter dan Setter Type
    public CharacterType getType() {
        return type;
    }

    public void setType(CharacterType type) {
        validateType(type);
        validateLevel(this.level, type);
        this.type = type;
    }

    // Getter dan Setter Level
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        validateLevel(level, this.type);

        this.level = level;
        this.hitPoints = 100 + (level * 20);
        this.attack = 10 + (level * 5);
    }

    // Getter dan Setter HitPoints
    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    // Getter dan Setter Attack
    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    // Getter Mana
    public int getMana() {
        return mana;
    }

    // Setter Mana
    public void setMana(int mana) {
        if (mana < 0) {
            throw new IllegalArgumentException(
                "Mana tidak boleh bernilai negatif!"
            );
        }

        this.mana = mana;
    }

    // Memeriksa apakah karakter masih hidup
    public boolean isAlive() {
        return hitPoints > 0;
    }

    // Menampilkan informasi karakter
    @Override
    public String toString() {
        return "Character{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", level=" + level +
                ", hitPoints=" + hitPoints +
                ", attack=" + attack +
                ", mana=" + mana +
                '}';
    }

    // Membandingkan karakter berdasarkan nama
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Character other = (Character) obj;

        return name.equalsIgnoreCase(other.name);
    }

    // HashCode konsisten dengan equals
    @Override
    public int hashCode() {
        return name.toLowerCase(Locale.ROOT).hashCode();
    }
}
