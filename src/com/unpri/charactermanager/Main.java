
package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            // Membuat Character Manager
            CharacterManager manager = new CharacterManager();

            // KARAKTER 1 - ARJUNA
            Character arjuna = new Character(
                    "Arjuna", CharacterType.WARRIOR
            );
            arjuna.setLevel(8);
            arjuna.setMana(100);

            // KARAKTER 2 - SRIKANDI
            Character srikandi = new Character(
                    "Srikandi", CharacterType.ARCHER
            );
            srikandi.setLevel(6);
            srikandi.setMana(140);

            // KARAKTER 3 - BIMA
            Character bima = new Character(
                    "Bima", CharacterType.WARRIOR
            );
            bima.setLevel(4);
            bima.setMana(80);

            // KARAKTER 4 - GATOTKACA
            Character gatotkaca = new Character(
                    "Gatotkaca", CharacterType.WARRIOR
            );
            gatotkaca.setLevel(10);
            gatotkaca.setMana(150);

            // KARAKTER 5 - NAKULA
            Character nakula = new Character(
                    "Nakula", CharacterType.MAGE
            );
            nakula.setLevel(5);
            nakula.setMana(120);

            // KARAKTER 6 - SADewa
            Character sadewa = new Character(
                    "Sadewa", CharacterType.MAGE
            );
            sadewa.setLevel(3);
            sadewa.setMana(90);

            // MENAMBAHKAN SEMUA KARAKTER
            manager.addCharacter(arjuna);
            manager.addCharacter(srikandi);
            manager.addCharacter(bima);
            manager.addCharacter(gatotkaca);
            manager.addCharacter(nakula);
            manager.addCharacter(sadewa);

            // MEMBUKA APLIKASI GUI
            MainWindow window = new MainWindow(manager);
            window.setVisible(true);

        });
    }
}
