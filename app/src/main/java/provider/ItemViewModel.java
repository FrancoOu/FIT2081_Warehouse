package provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    private  ItemRepository repository;
    private LiveData<List<Item>> allItems;
    public ItemViewModel(@NonNull Application application) {
        super(application);
        repository = new ItemRepository(application);
        allItems = repository.getAllItems();
    }

    public LiveData<List<Item>> getAllItems() {return allItems;}

    public void insert(Item item){repository.insert(item);}
    public void deleteAll(){repository.deleteAll();}
    public void deleteItem(int id){repository.deleteItem(id);}
}
