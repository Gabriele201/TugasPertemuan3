package untar.java.tugas1;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Record {
    private Date date;
    private String itemName;
    private int quantity;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Record(String itemName, int quantity) {
        this.date = new Date();
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public Date getDate() { return date; }
    public String getFormattedDate(){ return sdf.format(date); }
    public String getItemName() { return itemName; }
    public int getQuantity() { return quantity; }

    public Object[] toTableRow() {
        return new Object[]{ getFormattedDate(), itemName, quantity };
    }
}