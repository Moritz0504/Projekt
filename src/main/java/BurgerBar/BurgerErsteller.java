package BurgerBar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BurgerErsteller {

    private JPanel mainPanel;
    private JTextField tfName;
    private JTextField tfPreis;
    private JCheckBox chkVegan;
    private JButton btnSpeichern;
    private JButton btnBerechnen;
    private JTable tableAusgabe;
    private JCheckBox chkFilterAnzeigen;

    private ArrayList<Burger> burgerListe = new ArrayList<>();
    private DefaultTableModel tableModel;

    public BurgerErsteller() {
        JFrame frame = new JFrame("BurgerBar Manager");
        frame.setContentPane(mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(400,400);
        String[] spalten = {"Name", "Preis", "Vegan", "Brutto"};
        tableModel = new DefaultTableModel(spalten, 0);
        tableAusgabe.setModel(tableModel);
        tableAusgabe.setAutoCreateRowSorter(true);
        initObjekte();

        btnSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speichern();
            }
        });

        btnBerechnen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                berechneDurchschnitt();
            }
        });

        chkFilterAnzeigen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabelleAktualisieren();
            }
        });
    }

    public void initObjekte() {
        burgerListe.add(new Burger("Cheeseburger", 5.99, false));
        burgerListe.add(new Burger("Vegan Royal", 7.50, true));
        burgerListe.add(new Burger("BBQ Bacon", 8.90, false));
        tabelleAktualisieren();
    }

    private void speichern() {
        try {
            String name = tfName.getText();
            if (name.trim().isEmpty()) {
                JOptionPane.showMessageDialog(mainPanel, "Name fehlt!");
                return;
            }

            double preis = Double.parseDouble(tfPreis.getText());
            boolean istVegan = chkVegan.isSelected();

            Burger neu = new Burger(name, preis, istVegan);
            burgerListe.add(neu);

            tfName.setText("");
            tfPreis.setText("");
            chkVegan.setSelected(false);

            tabelleAktualisieren();

        //Exception Handling
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Bitte einen gültigen Preis eingeben (z.B. 5.99)!");
        }
    }

    private void tabelleAktualisieren() {
        tableModel.setRowCount(0);
        boolean filterAktiv = chkFilterAnzeigen.isSelected();

        for (Burger b : burgerListe) {
            if (filterAktiv && !b.isIstVegan()) {
                continue; //Überspringen
            }

            Object[] zeile = {
                    b.getName(),
                    b.getPreis(),
                    b.isIstVegan() ? "Ja" : "Nein",
                    String.format("%.2f", b.berechneBruttoPreis()) //Platzhalter und nach Komma was eingefügt wird
            };
            tableModel.addRow(zeile);
        }
    }

    private void berechneDurchschnitt() {
        if (burgerListe.isEmpty()) return;
        double summe = 0;
        for (Burger b : burgerListe) {
            summe += b.getPreis();
        }
        double schnitt = summe / burgerListe.size();
        JOptionPane.showMessageDialog(mainPanel, "Durchschnitt: " + String.format("%.2f", schnitt) + " €"); // % leitet Platzhalter ein, .2 sind Nachkommastellen, f ist der Datentyp
    }


    public static void main(String[] args) {
        new BurgerErsteller();
    }
}

