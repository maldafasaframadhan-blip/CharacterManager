
package com.unpri.charactermanager;

import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import com.unpri.charactermanager.view.MainWindow;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            CharacterManager manager = new CharacterManager();

            // KARAKTER 1
            Character arjuna = new Character(
                    "Arjuna", CharacterType.WARRIOR
            );
            arjuna.setLevel(8);
            arjuna.setMana(100);

            // KARAKTER 2
            Character srikandi = new Character(
                    "Srikandi", CharacterType.ARCHER
            );
            srikandi.setLevel(6);
            srikandi.setMana(140);

            // KARAKTER 3
            Character bima = new Character(
                    "Bima", CharacterType.WARRIOR
            );
            bima.setLevel(4);
            bima.setMana(80);

            // KARAKTER 4
            Character gatotkaca = new Character(
                    "Gatotkaca", CharacterType.WARRIOR
            );
            gatotkaca.setLevel(10);
            gatotkaca.setMana(150);

            // KARAKTER 5
            Character nakula = new Character(
                    "Nakula", CharacterType.MAGE
            );
            nakula.setLevel(5);
            nakula.setMana(120);

            // KARAKTER 6
            Character sadewa = new Character(
                    "Sadewa", CharacterType.MAGE
            );
            sadewa.setLevel(3);
            sadewa.setMana(90);

            // KARAKTER 7
            Character yudhistira = new Character(
                    "Yudhistira", CharacterType.ARCHER
            );
            yudhistira.setLevel(7);
            yudhistira.setMana(130);

            // KARAKTER 8
            Character karna = new Character(
                    "Karna", CharacterType.WARRIOR
            );
            karna.setLevel(9);
            karna.setMana(160);


            // MENAMBAHKAN KARAKTER
            manager.addCharacter(arjuna);
            manager.addCharacter(srikandi);
            manager.addCharacter(bima);
            manager.addCharacter(gatotkaca);
            manager.addCharacter(nakula);
            manager.addCharacter(sadewa);
            manager.addCharacter(yudhistira);
            manager.addCharacter(karna);


            // MENAMPILKAN APLIKASI
            MainWindow window = new MainWindow(manager);
            window.setVisible(true);

        });
    }
}
