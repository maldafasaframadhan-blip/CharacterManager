
package com.unpri.charactermanager.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.border.Border;

public final class AppTheme {

    // ==============================
    // WARNA APLIKASI
    // ==============================

    public static final Color BACKGROUND =
            new Color(20, 27, 45);

    public static final Color PANEL =
            new Color(35, 45, 68);

    public static final Color TEXT =
            new Color(255, 255, 255);

    public static final Color SECONDARY_TEXT =
            new Color(170, 180, 200);

    public static final Color ACCENT =
            new Color(55, 130, 235);

    public static final Color GOLD =
            new Color(255, 200, 60);


    // ==============================
    // FONT APLIKASI
    // ==============================

    public static final Font TITLE_FONT =
            new Font(Font.SANS_SERIF, Font.BOLD, 27);

    public static final Font NORMAL_FONT =
            new Font(Font.SANS_SERIF, Font.PLAIN, 14);

    public static final Font BOLD_FONT =
            new Font(Font.SANS_SERIF, Font.BOLD, 14);

    public static final Font HIGH_LEVEL_FONT =
            new Font(Font.SANS_SERIF, Font.BOLD, 14);


    // ==============================
    // BORDER APLIKASI
    // ==============================

    public static final Border PANEL_BORDER =
            BorderFactory.createLineBorder(
                    new Color(90, 110, 145), 1);


    // ==============================
    // BATAS LEVEL TINGGI
    // ==============================

    public static final int HIGH_LEVEL_LIMIT = 5;


    // ==============================
    // WARNA BERDASARKAN ROLE
    // ==============================

    public static Color colorFor(String role) {

        if (role == null) {
            return PANEL;
        }

        switch (role.toUpperCase()) {

            case "WARRIOR":
                return new Color(190, 75, 75);

            case "ARCHER":
                return new Color(55, 145, 100);

            case "MAGE":
                return new Color(100, 90, 190);

            default:
                return PANEL;
        }
    }


    // ==============================
    // BORDER BERDASARKAN ROLE
    // ==============================

    public static Border borderFor(String role) {

        if (role == null) {
            return PANEL_BORDER;
        }

        switch (role.toUpperCase()) {

            case "WARRIOR":
                return BorderFactory.createLineBorder(
                        new Color(220, 100, 100), 2);

            case "ARCHER":
                return BorderFactory.createLineBorder(
                        new Color(80, 190, 130), 2);

            case "MAGE":
                return BorderFactory.createLineBorder(
                        new Color(140, 120, 230), 2);

            default:
                return PANEL_BORDER;
        }
    }


    // ==============================
    // CONSTRUCTOR
    // ==============================

    private AppTheme() {
        // Mencegah objek AppTheme dibuat
    }

}
