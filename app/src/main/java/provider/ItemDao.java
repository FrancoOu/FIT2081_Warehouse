package provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("select * from items")
    LiveData<List<Item>> getAllItems();

    @Query("select * from items where itemName=:name")
    List<Item> getItem(String name);

    @Insert
    void addItem(Item item);

    @Query("delete from items where itemId= :id")
    void deleteItem(int id);

    @Query("delete FROM items")
    void deleteAllItems();
}
