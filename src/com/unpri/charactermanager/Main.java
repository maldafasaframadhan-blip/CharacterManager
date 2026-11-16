
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

            // Karakter 1: Arjuna
            Character arjuna = new Character(
                    "Arjuna",
                    CharacterType.WARRIOR
            );
            arjuna.setLevel(5);
            arjuna.setMana(50);

            // Karakter 2: Srikandi
            Character srikandi = new Character(
                    "Srikandi",
                    CharacterType.ARCHER
            );
            srikandi.setLevel(3);
            srikandi.setMana(140);

            // Karakter 3: Bima
            Character bima = new Character(
                    "Bima",
                    CharacterType.WARRIOR
            );
            bima.setLevel(7);
            bima.setMana(80);

            // Karakter 4: Gatotkaca
            Character gatotkaca = new Character(
                    "Gatotkaca",
                    CharacterType.WARRIOR
            );
            gatotkaca.setLevel(10);
            gatotkaca.setMana(100);

            // Karakter 5: Nakula
            Character nakula = new Character(
                    "Nakula",
                    CharacterType.MAGE
            );
            nakula.setLevel(8);
            nakula.setMana(120);

            // Menambahkan karakter ke CharacterManager
            manager.addCharacter(arjuna);
            manager.addCharacter(srikandi);
            manager.addCharacter(bima);
            manager.addCharacter(gatotkaca);
            manager.addCharacter(nakula);

            // Membuka jendela aplikasi
            MainWindow window = new MainWindow(manager);
            window.setVisible(true);

        });
    }
}
