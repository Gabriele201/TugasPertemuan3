package untar.java.tugas1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class MainFrame extends JFrame {
    private JTextField itemField;
    private JSpinner qtySpinner;
    private JButton addButton;
    private JButton deleteButton;
    private JTable table;
    private DefaultTableModel tableModel;
    private InventoryHistory history;

    public MainFrame() {
        super("History Inventory - Tugas Pertemuan 3");
        history = new InventoryHistory();
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4,4,4,4);
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Nama Barang:"), gbc);
        gbc.gridx = 1;
        itemField = new JTextField(20);
        inputPanel.add(itemField, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Jumlah:"), gbc);
        gbc.gridx = 1;
        qtySpinner = new JSpinner(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1));
        inputPanel.add(qtySpinner, gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        JPanel btnPanel = new JPanel();
        addButton = new JButton("Tambah (Add)");
        deleteButton = new JButton("Hapus (Delete)");
        btnPanel.add(addButton);
        btnPanel.add(deleteButton);
        inputPanel.add(btnPanel, gbc);

        add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(new Object[]{"Tanggal","Nama Barang","Jumlah"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);
        add(scroll, BorderLayout.CENTER);

        addButton.addActionListener(e -> onAdd());
        deleteButton.addActionListener(e -> onDelete());

        table.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DELETE) {
                    onDelete();
                }
            }
        });

        pack();
        setLocationRelativeTo(null);
        setSize(650, 420);
    }

    private void onAdd() {
        String name = itemField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan nama barang.", "Error", JOptionPane.ERROR_MESSAGE);
            itemField.requestFocus();
            return;
        }
        int qty = ((Number)qtySpinner.getValue()).intValue();
        Record r = new Record(name, qty);
        history.addRecord(r);
        tableModel.addRow(r.toTableRow());
        itemField.setText("");
        qtySpinner.setValue(1);
        itemField.requestFocus();
    }

    private void onDelete() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(this,
                "Yakin ingin menghapus record yang dipilih?", "Konfirmasi",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            history.removeRecord(row);
            tableModel.removeRow(row);
        }
    }
}