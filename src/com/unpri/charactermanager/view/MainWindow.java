
package com.unpri.charactermanager.view;

import javax.swing.*;
import java.awt.*;

import com.unpri.charactermanager.CharacterManager;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

public class MainWindow extends JFrame {

    private final CharacterManager manager;

    private JPanel characterList;
    private JLabel totalLabel;
    private JLabel averageLabel;

    private JTextField nameField;
    private JTextField levelField;
    private JTextField manaField;
    private JComboBox<CharacterType> typeBox;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager - Sprint 4");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(AppTheme.BACKGROUND);
        mainPanel.setBorder(AppTheme.PANEL_BORDER);
        setContentPane(mainPanel);

        // HEADER
        JLabel title = new JLabel("CHARACTER MANAGER");
        title.setForeground(AppTheme.TEXT);
        title.setFont(AppTheme.TITLE_FONT);

        JLabel subtitle = new JLabel("SPRINT 4 - THEME EDITION");
        subtitle.setForeground(AppTheme.SECONDARY_TEXT);
        subtitle.setFont(AppTheme.NORMAL_FONT);

        JPanel header = new JPanel(new GridLayout(2, 1));
        header.setBackground(AppTheme.BACKGROUND);
        header.setOpaque(true);
        header.add(title);
        header.add(subtitle);

        mainPanel.add(header, BorderLayout.NORTH);

        // FORM TAMBAH KARAKTER
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBackground(AppTheme.PANEL);
        form.setOpaque(true);
        form.setBorder(AppTheme.PANEL_BORDER);

        nameField = new JTextField();
        typeBox = new JComboBox<>(CharacterType.values());
        levelField = new JTextField();
        manaField = new JTextField();

        styleInput(nameField);
        styleInput(levelField);
        styleInput(manaField);

        typeBox.setFont(AppTheme.NORMAL_FONT);
        typeBox.setBackground(AppTheme.PANEL);
        typeBox.setForeground(AppTheme.TEXT);
        typeBox.setOpaque(true);

        form.add(createLabel("Nama Karakter"));
        form.add(nameField);

        form.add(createLabel("Tipe Karakter"));
        form.add(typeBox);

        form.add(createLabel("Level"));
        form.add(levelField);

        form.add(createLabel("Mana"));
        form.add(manaField);

        JButton addButton = new JButton("Tambah Karakter");
        addButton.setFont(AppTheme.BOLD_FONT);
        addButton.setBackground(AppTheme.ACCENT);
        addButton.setForeground(AppTheme.TEXT);
        addButton.setOpaque(true);
        addButton.setFocusPainted(false);

        form.add(new JLabel(""));
        form.add(addButton);

        // DAFTAR KARAKTER
        characterList = new JPanel();
        characterList.setLayout(
                new BoxLayout(characterList, BoxLayout.Y_AXIS)
        );
        characterList.setBackground(AppTheme.BACKGROUND);
        characterList.setOpaque(true);

        JScrollPane scrollPane = new JScrollPane(characterList);
        scrollPane.setBorder(AppTheme.PANEL_BORDER);
        scrollPane.getViewport().setBackground(AppTheme.BACKGROUND);
        scrollPane.getViewport().setOpaque(true);
        scrollPane.setPreferredSize(new Dimension(600, 280));

        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        centerPanel.setBackground(AppTheme.BACKGROUND);
        centerPanel.setOpaque(true);
        centerPanel.add(form, BorderLayout.NORTH);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // STATISTIK
        totalLabel = new JLabel("0");
        averageLabel = new JLabel("0.00");

        JPanel stats = new JPanel(new GridLayout(1, 2, 15, 0));
        stats.setBackground(AppTheme.BACKGROUND);
        stats.setOpaque(true);

        stats.add(createStatPanel("TOTAL KARAKTER", totalLabel));
        stats.add(createStatPanel("RATA-RATA LEVEL", averageLabel));

        mainPanel.add(stats, BorderLayout.SOUTH);

        // AKSI TOMBOL
        addButton.addActionListener(e -> addCharacter());

        refreshCharacters();

        // PACK UNTUK MENYESUAIKAN UKURAN KOMPONEN
        pack();
        setLocationRelativeTo(null);
    }

    private void styleInput(JTextField field) {
        field.setFont(AppTheme.NORMAL_FONT);
        field.setBackground(AppTheme.PANEL);
        field.setForeground(AppTheme.TEXT);
        field.setOpaque(true);
        field.setBorder(AppTheme.PANEL_BORDER);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(AppTheme.TEXT);
        label.setFont(AppTheme.NORMAL_FONT);
        return label;
    }

    private JPanel createStatPanel(String title, JLabel value) {

        JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
        panel.setBackground(AppTheme.PANEL);
        panel.setOpaque(true);
        panel.setBorder(AppTheme.PANEL_BORDER);

        JLabel heading = new JLabel(title);
        heading.setForeground(AppTheme.SECONDARY_TEXT);
        heading.setFont(AppTheme.NORMAL_FONT);

        value.setForeground(AppTheme.TEXT);
        value.setFont(AppTheme.TITLE_FONT);

        panel.add(heading);
        panel.add(value);

        return panel;
    }

    // MENAMBAHKAN KARAKTER
    private void addCharacter() {

        try {

            String name = nameField.getText().trim();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this, "Nama karakter tidak boleh kosong!"
                );
                return;
            }

            int level = Integer.parseInt(levelField.getText().trim());
            int mana = Integer.parseInt(manaField.getText().trim());

            if (level < 1 || mana < 0) {
                JOptionPane.showMessageDialog(
                        this, "Level minimal 1 dan mana tidak boleh negatif!"
                );
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
                JOptionPane.showMessageDialog(
                        this,
                        "Karakter gagal ditambahkan. Nama mungkin sudah digunakan."
                );
                return;
            }

            nameField.setText("");
            levelField.setText("");
            manaField.setText("");

            refreshCharacters();

            JOptionPane.showMessageDialog(
                    this, "Karakter berhasil ditambahkan!"
            );

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this, "Level dan mana harus berupa angka!"
            );
        }
    }

    // MEMPERBARUI DAFTAR DAN STATISTIK
    private void refreshCharacters() {

        characterList.removeAll();

        int total = manager.getTotalCharacters();
        int sumLevel = 0;

        for (Character character : manager.getAllCharacters()) {

            sumLevel += character.getLevel();

            String role = String.valueOf(character.getType());

            JPanel card = new JPanel(new BorderLayout(10, 5));

            // WARNA KARTU SESUAI ROLE
            card.setBackground(AppTheme.colorFor(role));
            card.setOpaque(true);

            // BORDER BERBEDA SESUAI ROLE
            card.setBorder(AppTheme.borderFor(role));

            JLabel nameLabel = new JLabel(character.toString());
            nameLabel.setFont(AppTheme.NORMAL_FONT);
            nameLabel.setForeground(AppTheme.TEXT);

            JLabel levelLabel = new JLabel(
                    "LEVEL: " + character.getLevel()
            );

            // MINI-CHALLENGE LEVEL 5 KE ATAS
            if (character.getLevel() >= AppTheme.HIGH_LEVEL_LIMIT) {
                levelLabel.setForeground(AppTheme.GOLD);
                levelLabel.setFont(AppTheme.HIGH_LEVEL_FONT);
            } else {
                levelLabel.setForeground(AppTheme.TEXT);
                levelLabel.setFont(AppTheme.NORMAL_FONT);
            }

            // PROPERTI TAMBAHAN: TOOLTIP
            card.setToolTipText(
                    "Detail karakter: " + character.toString()
            );

            nameLabel.setToolTipText(
                    "Role: " + role
            );

            card.add(nameLabel, BorderLayout.CENTER);
            card.add(levelLabel, BorderLayout.EAST);

            characterList.add(card);
            characterList.add(Box.createVerticalStrut(8));
        }

        totalLabel.setText(String.valueOf(total));

        double average = total == 0
                ? 0
                : (double) sumLevel / total;

        averageLabel.setText(String.format("%.2f", average));

        characterList.revalidate();
        characterList.repaint();
    }
}
