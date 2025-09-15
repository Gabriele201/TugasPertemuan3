package untar.java.tugas1;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ProductTableModel extends AbstractTableModel {
    private ArrayList<ProductModel> products;
    private String[] columnNames = {"Kode", "Nama", "Qty", "Harga"};
    
    public ProductTableModel(ArrayList<ProductModel> products) {
        this.products = products;
    }
    
    @Override
    public int getRowCount() {
        return products.size();
    }
    
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ProductModel product = products.get(rowIndex);
        switch (columnIndex) {
            case 0: return product.getCode();
            case 1: return product.getName();
            case 2: return product.getQty();
            case 3: return product.getPrice();
            default: return null;
        }
    }
    
    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
    
    public void addProduct(ProductModel product) {
        products.add(product);
        fireTableRowsInserted(products.size() - 1, products.size() - 1);
    }
    
    public void removeProduct(int index) {
        if (index >= 0 && index < products.size()) {
            products.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }
}