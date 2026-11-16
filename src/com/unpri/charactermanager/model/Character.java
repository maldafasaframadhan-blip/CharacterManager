
package com.unpri.charactermanager.model;

public class Character {

    // Atribut private
    private String name;
    private CharacterType type;
    private int level;
    private int hitPoints;
    private int attack;
    private int mana;

    // Constructor pertama
    public Character(String name, CharacterType type) {
        this.name = name;
        this.type = type;
        this.level = 1;
        this.hitPoints = 100;
        this.attack = 10;
        this.mana = 0;
    }

    // Constructor kedua
    public Character(String name, CharacterType type,
                     int level, int hitPoints, int attack) {

        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = hitPoints;
        this.attack = attack;
        this.mana = 0;
    }

    // Getter dan Setter Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getter dan Setter Type
    public CharacterType getType() {
        return type;
    }

    public void setType(CharacterType type) {
        this.type = type;
    }

    // Getter dan Setter Level
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;

        // Menghitung ulang HP dan Attack
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

    // Setter Mana dengan validasi
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
}
