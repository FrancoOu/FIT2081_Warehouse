package provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemRepository {
    private ItemDao itemDao;
    private LiveData<List<Item>> allItems;

    ItemRepository(Application application){
        ItemDatabase db =ItemDatabase.getDatabase(application);
        itemDao = db.itemDao();
        allItems = itemDao.getAllItems();
    }

    LiveData<List<Item>> getAllItems(){return allItems;}

    void insert(Item item){
        ItemDatabase.databaseWriteExecutor.execute(()->itemDao.addItem(item));
    }
    void deleteAll(){
        ItemDatabase.databaseWriteExecutor.execute(()->{
            itemDao.deleteAllItems();
        });
    }
    void deleteItem(int id){
        ItemDatabase.databaseWriteExecutor.execute(()->{
            itemDao.deleteItem(id);
        });
    }
}
