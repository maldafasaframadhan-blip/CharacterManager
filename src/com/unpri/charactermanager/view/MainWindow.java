
package com.unpri.charactermanager.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import com.unpri.charactermanager.CharacterManager;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private CharacterManager manager;
    private JPanel characterList;
    private JLabel totalLabel;
    private JLabel averageLabel;

    private JTextField nameField;
    private JTextField levelField;
    private JTextField manaField;
    private JComboBox<CharacterType> typeBox;

    public MainWindow(CharacterManager manager) {
        this.manager = manager;

        setTitle("Character Manager - Sprint 3");
        setSize(850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(new Color(20, 27, 45));
        mainPanel.setBorder(new EmptyBorder(20, 25, 20, 25));
        setContentPane(mainPanel);

        // HEADER
        JLabel title = new JLabel("CHARACTER MANAGER");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 27));

        JLabel subtitle = new JLabel("Manage your characters");
        subtitle.setForeground(new Color(170, 180, 200));

        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setOpaque(false);
        header.add(title);
        header.add(subtitle);

        mainPanel.add(header, BorderLayout.NORTH);

        // FORM TAMBAH KARAKTER
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBackground(new Color(35, 45, 68));
        form.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(90, 110, 145)),
                "Tambah Karakter",
                0, 0,
                new Font("Arial", Font.BOLD, 14),
                Color.WHITE
        ));

        nameField = new JTextField();
        typeBox = new JComboBox<>(CharacterType.values());
        levelField = new JTextField();
        manaField = new JTextField();

        form.add(createLabel("Nama Karakter"));
        form.add(nameField);

        form.add(createLabel("Tipe Karakter"));
        form.add(typeBox);

        form.add(createLabel("Level"));
        form.add(levelField);

        form.add(createLabel("Mana"));
        form.add(manaField);

        JButton addButton = new JButton("Tambah Karakter");
        addButton.setBackground(new Color(55, 130, 235));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);

        form.add(new JLabel(""));
        form.add(addButton);

        // DAFTAR KARAKTER
        characterList = new JPanel();
        characterList.setLayout(new BoxLayout(characterList, BoxLayout.Y_AXIS));
        characterList.setBackground(new Color(20, 27, 45));

        JScrollPane scrollPane = new JScrollPane(characterList);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(90, 110, 145)),
                "Daftar Karakter",
                0, 0,
                new Font("Arial", Font.BOLD, 14),
                Color.WHITE
        ));
        scrollPane.getViewport().setBackground(new Color(20, 27, 45));

        JPanel centerPanel = new JPanel(new BorderLayout(10, 15));
        centerPanel.setOpaque(false);
        centerPanel.add(form, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // STATISTIK
        totalLabel = new JLabel();
        averageLabel = new JLabel();

        JPanel stats = new JPanel(new GridLayout(1, 2, 15, 0));
        stats.setOpaque(false);

        stats.add(createStatPanel("TOTAL KARAKTER", totalLabel));
        stats.add(createStatPanel("RATA-RATA LEVEL", averageLabel));

        mainPanel.add(stats, BorderLayout.SOUTH);

        // AKSI TOMBOL
        addButton.addActionListener(e -> addCharacter());

        refreshCharacters();
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        return label;
    }

    private JPanel createStatPanel(String title, JLabel value) {
        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
        panel.setBackground(new Color(35, 45, 68));
        panel.setBorder(new EmptyBorder(12, 15, 12, 15));

        JLabel heading = new JLabel(title);
        heading.setForeground(new Color(170, 190, 220));

        value.setForeground(Color.WHITE);
        value.setFont(new Font("Arial", Font.BOLD, 22));

        panel.add(heading);
        panel.add(value);

        return panel;
    }

    private void addCharacter() {
        try {
            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Nama karakter tidak boleh kosong!");
                return;
            }

            int level = Integer.parseInt(levelField.getText().trim());
            int mana = Integer.parseInt(manaField.getText().trim());

            if (level < 1 || mana < 0) {
                JOptionPane.showMessageDialog(this,
                        "Level minimal 1 dan mana tidak boleh negatif!");
                return;
            }

            CharacterType type =
                    (CharacterType) typeBox.getSelectedItem();

            Character character = new Character(name, type);
            character.setLevel(level);
            character.setMana(mana);

            int before = manager.getTotalCharacters();
            manager.addCharacter(character);
            int after = manager.getTotalCharacters();

            if (after == before) {
                JOptionPane.showMessageDialog(this,
                        "Karakter gagal ditambahkan. Nama mungkin sudah digunakan.");
                return;
            }

            nameField.setText("");
            levelField.setText("");
            manaField.setText("");

            refreshCharacters();

            JOptionPane.showMessageDialog(this,
                    "Karakter berhasil ditambahkan!");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Level dan mana harus berupa angka!");
        }
    }

    private void refreshCharacters() {
        characterList.removeAll();

        int total = manager.getTotalCharacters();
        int sumLevel = 0;

        for (Character character : manager.getAllCharacters()) {
            sumLevel += character.getLevel();

            JPanel card = new JPanel(new BorderLayout());
            card.setBackground(new Color(35, 45, 68));
            card.setBorder(new EmptyBorder(12, 15, 12, 15));
            card.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));

            JLabel name = new JLabel(character.toString());
            name.setForeground(Color.WHITE);
            name.setFont(new Font("Arial", Font.BOLD, 14));

            card.add(name, BorderLayout.CENTER);
            characterList.add(card);
            characterList.add(Box.createVerticalStrut(8));
        }

        totalLabel.setText(String.valueOf(total));

        double average = total == 0 ? 0 : (double) sumLevel / total;
        averageLabel.setText(String.format("%.2f", average));

        characterList.revalidate();
        characterList.repaint();
    }
}
