package untar.java.tugas1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class InventoryApp extends JFrame {
    private ArrayList<ProductModel> products;
    private ProductTableModel tableModel;
    
    private JTextField kodeField;
    private JTextField namaField;
    private JTextField qtyField;
    private JTextField hargaField;
    private JButton tambahButton;
    private JButton hapusButton;
    private JTable table;
    
    public InventoryApp() {
        products = new ArrayList<>();
        tableModel = new ProductTableModel(products);
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Inventory App (Memory Only)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        
        gbc.gridx = 0; gbc.gridy = 0;
        inputPanel.add(new JLabel("Kode:"), gbc);
        gbc.gridx = 1;
        kodeField = new JTextField(15);
        inputPanel.add(kodeField, gbc);
        
        gbc.gridx = 0; gbc.gridy = 1;
        inputPanel.add(new JLabel("Nama:"), gbc);
        gbc.gridx = 1;
        namaField = new JTextField(15);
        inputPanel.add(namaField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        inputPanel.add(new JLabel("Qty:"), gbc);
        gbc.gridx = 1;
        qtyField = new JTextField(15);
        inputPanel.add(qtyField, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        inputPanel.add(new JLabel("Harga:"), gbc);
        gbc.gridx = 1;
        hargaField = new JTextField(15);
        inputPanel.add(hargaField, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        tambahButton = new JButton("Tambah");
        inputPanel.add(tambahButton, gbc);
        
        add(inputPanel, BorderLayout.NORTH);

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        hapusButton = new JButton("Hapus Produk Terpilih");
        add(hapusButton, BorderLayout.SOUTH);

        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahProduk();
            }
        });
        
        hapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusProduk();
            }
        });
        
        setSize(600, 400);
        setLocationRelativeTo(null);
    }
    
    private void tambahProduk() {
        try {
            String kode = kodeField.getText().trim();
            String nama = namaField.getText().trim();
            int qty = Integer.parseInt(qtyField.getText().trim());
            double harga = Double.parseDouble(hargaField.getText().trim());
            
            if (kode.isEmpty() || nama.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Kode dan Nama tidak boleh kosong!", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            ProductModel product = new ProductModel(kode, nama, qty, harga);
            tableModel.addProduct(product);
            
            kodeField.setText("");
            namaField.setText("");
            qtyField.setText("");
            hargaField.setText("");
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Qty dan Harga harus berupa angka!", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void hapusProduk() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih produk yang akan dihapus!", 
                "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Yakin ingin menghapus produk ini?", "Konfirmasi", 
            JOptionPane.YES_NO_OPTION);
            
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeProduct(selectedRow);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new InventoryApp().setVisible(true);
            }
        });
    }
}