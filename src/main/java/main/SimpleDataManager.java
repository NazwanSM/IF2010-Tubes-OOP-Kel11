package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleDataManager<T extends items.Items> {
    private final List<T> itemList = new ArrayList<>();
    private final Map<String, T> itemByNameMap = new HashMap<>();

    public void addItem(T item) {
        if (item != null && item.getName() != null) {
            itemList.add(item);
            itemByNameMap.put(item.getName().toLowerCase(), item);
        }
    }

    public List<T> getAllItems() {
        return Collections.unmodifiableList(itemList);
    }

    public T getItemByName(String name) {
        if (name == null) {
            return null;
        }
        return itemByNameMap.get(name.toLowerCase());
    }
}