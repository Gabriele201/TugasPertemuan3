package untar.java.tugas1;

import java.util.ArrayList;
import java.util.List;

public class InventoryHistory {
    private final List<Record> records = new ArrayList<>();

    public void addRecord(Record r) { records.add(r); }
    public Record removeRecord(int index) { return records.remove(index); }
    public List<Record> getRecords() { return new ArrayList<>(records); }
    public int size() { return records.size(); }
    public Record get(int index) { return records.get(index); }
    public void clear(){ records.clear(); }
}