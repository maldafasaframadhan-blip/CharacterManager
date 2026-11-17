
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.CharacterManager;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CharacterFormPanel extends JPanel {

    private final CharacterManager manager;

    private JTextField nameField;
    private JComboBox<CharacterType> roleCombo;
    private JSpinner levelSpinner;

    private JLabel messageLabel;

    private Border originalNameBorder;

    public CharacterFormPanel(CharacterManager manager) {

        this.manager = manager;

        setLayout(new GridBagLayout());
        setBackground(AppTheme.PANEL);
        setBorder(BorderFactory.createCompoundBorder(
                AppTheme.PANEL_BORDER,
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JLabel title = new JLabel("TAMBAH CHARACTER");
        title.setForeground(AppTheme.TEXT);
        title.setFont(AppTheme.BOLD_FONT);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(title, gbc);

        // Input nama
        JLabel nameLabel = createLabel("Nama Karakter");

        nameField = new JTextField(18);
        nameField.setFont(AppTheme.NORMAL_FONT);
        nameField.setBackground(AppTheme.INPUT_BACKGROUND);
        nameField.setForeground(AppTheme.INPUT_TEXT);

        originalNameBorder = nameField.getBorder();

        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(nameLabel, gbc);

        gbc.gridx = 1;
        add(nameField, gbc);

        // Input role
        JLabel roleLabel = createLabel("Role");

        roleCombo = new JComboBox<>(CharacterType.values());
        roleCombo.setFont(AppTheme.NORMAL_FONT);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(roleLabel, gbc);

        gbc.gridx = 1;
        add(roleCombo, gbc);

        // Input level
        JLabel levelLabel = createLabel("Level");

        levelSpinner = new JSpinner(
                new SpinnerNumberModel(1, 1, 99, 1)
        );

        levelSpinner.setFont(AppTheme.NORMAL_FONT);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(levelLabel, gbc);

        gbc.gridx = 1;
        add(levelSpinner, gbc);

        // Pesan validasi
        messageLabel = new JLabel(" ");
        messageLabel.setForeground(AppTheme.ERROR);
        messageLabel.setFont(AppTheme.NORMAL_FONT);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(messageLabel, gbc);

        // Tombol tambah
        JButton addButton = new JButton("Tambah Character");
        addButton.setFont(AppTheme.BOLD_FONT);
        addButton.setBackground(AppTheme.ACCENT);
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);

        gbc.gridy = 5;
        add(addButton, gbc);

        addButton.addActionListener(e -> addCharacter());
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(AppTheme.TEXT);
        label.setFont(AppTheme.NORMAL_FONT);
        return label;
    }

    // Validasi nama: kelengkapan, panjang, format, keunikan
    private String validateName(String name) {

        // 1. Kelengkapan
        if (name == null || name.trim().isEmpty()) {
            return "Nama karakter wajib diisi!";
        }

        String cleanedName = name.trim();

        // 2. Panjang
        if (cleanedName.length() > 20) {
            return "Nama maksimal 20 karakter!";
        }

        // 3. Format
        if (!cleanedName.matches("[\\p{L}]+(?:[ '-][\\p{L}]+)*")) {
            return "Nama hanya boleh berisi huruf dan pemisah sederhana!";
        }

        // 4. Keunikan
        if (manager.isNameTaken(cleanedName)) {
            return "Nama karakter sudah digunakan!";
        }

        return null;
    }

    private void addCharacter() {

        String name = nameField.getText().trim();

        String error = validateName(name);

        if (error != null) {
            showError(error);
            nameField.requestFocusInWindow();
            nameField.selectAll();
            return;
        }

        try {

            // Membaca spinner setelah commitEdit()
            levelSpinner.commitEdit();

            Integer level = (Integer) levelSpinner.getValue();

            CharacterType type =
                    (CharacterType) roleCombo.getSelectedItem();

            Character character =
                    new Character(name, type, level, 100, 10);

            manager.addCharacter(character);

            messageLabel.setForeground(
                    new Color(100, 220, 140)
            );

            messageLabel.setText(
                    "Character berhasil ditambahkan!"
            );

            nameField.setText("");
            nameField.setBorder(originalNameBorder);
            nameField.requestFocusInWindow();

            // Memberi tahu MainWindow bahwa data berhasil ditambahkan
            firePropertyChange("characterAdded", false, true);

        } catch (IllegalArgumentException ex) {

            showError(ex.getMessage());
            nameField.requestFocusInWindow();

        } catch (java.text.ParseException ex) {

            showError("Level harus berupa angka yang valid!");
            levelSpinner.requestFocusInWindow();
        }
    }

    private void showError(String message) {
        messageLabel.setForeground(AppTheme.ERROR);
        messageLabel.setText(message);
        nameField.setBorder(AppTheme.ERROR_BORDER);
    }
}
