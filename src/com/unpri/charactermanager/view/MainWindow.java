
package com.unpri.charactermanager.view;

import com.unpri.charactermanager.CharacterManager;
import com.unpri.charactermanager.model.Character;
import com.unpri.charactermanager.model.CharacterType;
import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame {

    private final CharacterManager manager;

    private JPanel characterList;

    private JLabel totalLabel;
    private JLabel averageLabel;
    private JLabel statusText;

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager - Sprint 6");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBackground(AppTheme.BACKGROUND);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        setContentPane(mainPanel);

        // =================================
        // NORTH: HEADER DAN STATISTIK
        // =================================

        JPanel headerArea = new JPanel(new BorderLayout(10, 10));
        headerArea.setBackground(AppTheme.BACKGROUND);
        headerArea.setOpaque(true);

        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBackground(AppTheme.BACKGROUND);
        titlePanel.setOpaque(true);

        JLabel title = new JLabel("CHARACTER MANAGER");
        title.setForeground(AppTheme.TEXT);
        title.setFont(AppTheme.TITLE_FONT);

        JLabel subtitle = new JLabel(
                "SPRINT 6 - FORM INPUT & VALIDASI"
        );
        subtitle.setForeground(AppTheme.SECONDARY_TEXT);
        subtitle.setFont(AppTheme.NORMAL_FONT);

        titlePanel.add(title);
        titlePanel.add(subtitle);

        headerArea.add(titlePanel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel(new GridLayout(2, 2, 10, 5));
        statsPanel.setBackground(AppTheme.PANEL);
        statsPanel.setOpaque(true);
        statsPanel.setBorder(AppTheme.PANEL_BORDER);

        totalLabel = new JLabel("0");
        averageLabel = new JLabel("0.00");

        JLabel totalTitle = createLabel("TOTAL KARAKTER");
        JLabel averageTitle = createLabel("RATA-RATA LEVEL");

        totalLabel.setForeground(AppTheme.GOLD);
        totalLabel.setFont(AppTheme.BOLD_FONT);

        averageLabel.setForeground(AppTheme.GOLD);
        averageLabel.setFont(AppTheme.BOLD_FONT);

        statsPanel.add(totalTitle);
        statsPanel.add(totalLabel);
        statsPanel.add(averageTitle);
        statsPanel.add(averageLabel);

        headerArea.add(statsPanel, BorderLayout.SOUTH);

        mainPanel.add(headerArea, BorderLayout.NORTH);

        // =================================
        // CENTER: FORM DAN DAFTAR KARAKTER
        // =================================

        JPanel centerPanel = new JPanel(new BorderLayout(12, 12));
        centerPanel.setBackground(AppTheme.BACKGROUND);
        centerPanel.setOpaque(true);

        CharacterFormPanel formPanel =
                new CharacterFormPanel(manager);

        centerPanel.add(formPanel, BorderLayout.NORTH);

        // Daftar karakter
        characterList = new JPanel();

        characterList.setLayout(
                new BoxLayout(characterList, BoxLayout.Y_AXIS)
        );

        characterList.setBackground(AppTheme.BACKGROUND);
        characterList.setOpaque(true);

        JScrollPane scroll = new JScrollPane(characterList);

        scroll.setBorder(AppTheme.PANEL_BORDER);
        scroll.getViewport().setBackground(AppTheme.BACKGROUND);
        scroll.getViewport().setOpaque(true);

        centerPanel.add(scroll, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // =================================
        // EAST: LEGENDA ROLE
        // =================================

        JPanel legendPanel = new JPanel();

        legendPanel.setLayout(
                new BoxLayout(legendPanel, BoxLayout.Y_AXIS)
        );

        legendPanel.setBackground(AppTheme.PANEL);
        legendPanel.setOpaque(true);
        legendPanel.setBorder(AppTheme.PANEL_BORDER);

        JLabel legendTitle = new JLabel("LEGENDA ROLE");
        legendTitle.setForeground(AppTheme.TEXT);
        legendTitle.setFont(AppTheme.BOLD_FONT);
        legendTitle.setAlignmentX(Component.LEFT_ALIGNMENT);

        legendPanel.add(legendTitle);
        legendPanel.add(Box.createVerticalStrut(15));

        for (CharacterType type : CharacterType.values()) {

            String role = type.toString();

            JLabel roleLabel = new JLabel("  " + role + "  ");

            roleLabel.setFont(AppTheme.NORMAL_FONT);
            roleLabel.setForeground(AppTheme.TEXT);
            roleLabel.setBackground(AppTheme.colorFor(role));
            roleLabel.setBorder(AppTheme.borderFor(role));
            roleLabel.setOpaque(true);

            roleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            roleLabel.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 35)
            );

            legendPanel.add(roleLabel);
            legendPanel.add(Box.createVerticalStrut(10));
        }

        legendPanel.add(Box.createVerticalGlue());
        legendPanel.setPreferredSize(new Dimension(155, 0));

        mainPanel.add(legendPanel, BorderLayout.EAST);

        // =================================
        // SOUTH: STATUS BAR
        // =================================

        JPanel statusPanel = new JPanel(new BorderLayout());

        statusPanel.setBackground(AppTheme.PANEL);
        statusPanel.setOpaque(true);
        statusPanel.setBorder(AppTheme.PANEL_BORDER);

        statusText = new JLabel(
                "  Character Manager - Siap digunakan"
        );

        statusText.setForeground(AppTheme.TEXT);
        statusText.setFont(AppTheme.NORMAL_FONT);

        JLabel versionLabel = new JLabel("v0.6  ");
        versionLabel.setForeground(AppTheme.GOLD);
        versionLabel.setFont(AppTheme.BOLD_FONT);

        statusPanel.add(statusText, BorderLayout.WEST);
        statusPanel.add(versionLabel, BorderLayout.EAST);

        mainPanel.add(statusPanel, BorderLayout.SOUTH);

        // =================================
        // INTEGRASI FORM DENGAN DAFTAR
        // =================================

        formPanel.addPropertyChangeListener(
                "characterAdded",
                event -> {
                    refreshList();
                    statusText.setText(
                            "  Character berhasil ditambahkan!"
                    );
                }
        );

        // Tampilkan data awal
        refreshList();

        pack();
        setMinimumSize(new Dimension(850, 650));
        setSize(1050, 750);
        setLocationRelativeTo(null);
    }

    // =================================
    // LABEL HELPER
    // =================================

    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);
        label.setForeground(AppTheme.TEXT);
        label.setFont(AppTheme.NORMAL_FONT);

        return label;
    }

    // =================================
    // REFRESH DAFTAR KARAKTER
    // =================================

    private void refreshList() {

        characterList.removeAll();

        int total = manager.getTotalCharacters();
        int sumLevel = 0;

        for (Character character : manager.getAllCharacters()) {

            sumLevel += character.getLevel();

            String role = String.valueOf(character.getType());

            JPanel card = new JPanel(new BorderLayout(10, 5));

            card.setBackground(AppTheme.colorFor(role));
            card.setOpaque(true);
            card.setBorder(AppTheme.borderFor(role));

            card.setMaximumSize(
                    new Dimension(Integer.MAX_VALUE, 65)
            );

            card.setAlignmentX(Component.LEFT_ALIGNMENT);

            JLabel nameLabel = new JLabel(
                    character.getName() + " - " + role
            );

            nameLabel.setForeground(AppTheme.TEXT);
            nameLabel.setFont(AppTheme.NORMAL_FONT);

            JLabel levelLabel = new JLabel(
                    "LEVEL: " + character.getLevel()
            );

            if (character.getLevel() >= AppTheme.HIGH_LEVEL_LIMIT) {
                levelLabel.setForeground(AppTheme.GOLD);
                levelLabel.setFont(AppTheme.HIGH_LEVEL_FONT);
            } else {
                levelLabel.setForeground(AppTheme.TEXT);
                levelLabel.setFont(AppTheme.NORMAL_FONT);
            }

            card.setToolTipText(character.toString());

            card.add(nameLabel, BorderLayout.CENTER);
            card.add(levelLabel, BorderLayout.EAST);

            characterList.add(card);
            characterList.add(Box.createVerticalStrut(8));
        }

        // Perbarui statistik
        totalLabel.setText(String.valueOf(total));

        double average = total == 0
                ? 0
                : (double) sumLevel / total;

        averageLabel.setText(
                String.format(java.util.Locale.US, "%.2f", average)
        );

        // Perbarui tampilan Swing
        characterList.revalidate();
        characterList.repaint();
    }
}
