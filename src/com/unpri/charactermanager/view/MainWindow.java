
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

    public MainWindow(CharacterManager manager) {

        this.manager = manager;

        setTitle("Character Manager - Sprint 5");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ROOT PANEL: BORDER LAYOUT
        JPanel mainPanel = new JPanel(new BorderLayout(12, 12));
        mainPanel.setBackground(AppTheme.BACKGROUND);
        mainPanel.setBorder(AppTheme.PANEL_BORDER);

        setContentPane(mainPanel);


        // =================================
        // NORTH: HEADER
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

        JLabel subtitle = new JLabel("SPRINT 5 - RESPONSIVE LAYOUT");
        subtitle.setForeground(AppTheme.SECONDARY_TEXT);
        subtitle.setFont(AppTheme.NORMAL_FONT);

        titlePanel.add(title);
        titlePanel.add(subtitle);

        headerArea.add(titlePanel, BorderLayout.NORTH);


        // =================================
        // PANEL TAMBAHAN: STATISTIK
        // GRID LAYOUT 2 X 2
        // =================================

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
        // CENTER: DAFTAR KARAKTER
        // =================================

        characterList = new JPanel();

        characterList.setLayout(
                new BoxLayout(characterList, BoxLayout.Y_AXIS)
        );

        characterList.setBackground(AppTheme.BACKGROUND);
        characterList.setOpaque(true);

        // ScrollPane membungkus panel daftar karakter
        JScrollPane scroll = new JScrollPane(characterList);

        scroll.setPreferredSize(new Dimension(620, 350));
        scroll.setBorder(AppTheme.PANEL_BORDER);

        scroll.getViewport().setBackground(AppTheme.BACKGROUND);
        scroll.getViewport().setOpaque(true);

        mainPanel.add(scroll, BorderLayout.CENTER);


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

        // Legenda dibuat berdasarkan enum CharacterType
        for (CharacterType type : CharacterType.values()) {

            String role = type.toString();

            JLabel roleLabel = new JLabel("  " + role + "  ");

            roleLabel.setFont(AppTheme.NORMAL_FONT);
            roleLabel.setForeground(AppTheme.TEXT);
            roleLabel.setBackground(AppTheme.colorFor(role));
            roleLabel.setBorder(AppTheme.borderFor(role));
            roleLabel.setOpaque(true);

            roleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            roleLabel.setMaximumSize(new Dimension(
                    Integer.MAX_VALUE, 35
            ));

            legendPanel.add(roleLabel);
            legendPanel.add(Box.createVerticalStrut(10));
        }

        // Glue mengisi ruang kosong secara fleksibel
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

        JLabel statusText = new JLabel("  Character Manager - Siap digunakan");
        statusText.setForeground(AppTheme.TEXT);
        statusText.setFont(AppTheme.NORMAL_FONT);

        JLabel versionLabel = new JLabel("v0.5  ");
        versionLabel.setForeground(AppTheme.GOLD);
        versionLabel.setFont(AppTheme.BOLD_FONT);

        statusPanel.add(statusText, BorderLayout.WEST);
        statusPanel.add(versionLabel, BorderLayout.EAST);

        mainPanel.add(statusPanel, BorderLayout.SOUTH);


        // =================================
        // REFRESH DATA
        // =================================

        refreshCharacters();

        // Mengatur ukuran otomatis berdasarkan komponen
        pack();

        setMinimumSize(new Dimension(650, 350));
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
    // MENAMPILKAN KARAKTER
    // =================================

    private void refreshCharacters() {

        characterList.removeAll();

        int total = manager.getTotalCharacters();
        int sumLevel = 0;

        for (Character character : manager.getAllCharacters()) {

            sumLevel += character.getLevel();

            String role = String.valueOf(character.getType());

            // Panel kartu karakter
            JPanel card = new JPanel(new BorderLayout(10, 5));

            card.setBackground(AppTheme.colorFor(role));
            card.setOpaque(true);
            card.setBorder(AppTheme.borderFor(role));

            card.setMaximumSize(new Dimension(
                    Integer.MAX_VALUE, 65
            ));

            card.setAlignmentX(Component.LEFT_ALIGNMENT);

            // Nama karakter
            JLabel nameLabel = new JLabel(character.toString());

            nameLabel.setForeground(AppTheme.TEXT);
            nameLabel.setFont(AppTheme.NORMAL_FONT);

            // Level karakter
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

            // Tooltip
            card.setToolTipText(character.toString());

            card.add(nameLabel, BorderLayout.CENTER);
            card.add(levelLabel, BorderLayout.EAST);

            // Kartu masuk ke panel daftar,
            // bukan langsung ke JScrollPane
            characterList.add(card);

            characterList.add(Box.createVerticalStrut(8));
        }

        // Menghitung statistik
        totalLabel.setText(String.valueOf(total));

        double average = total == 0
                ? 0
                : (double) sumLevel / total;

        averageLabel.setText(
                String.format(java.util.Locale.US, "%.2f", average)
        );

        // Memperbarui tampilan
        characterList.revalidate();
        characterList.repaint();
    }
}
