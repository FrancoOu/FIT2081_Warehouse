package provider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName="items")
public class Item {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name="itemId")
    private int id;

    @ColumnInfo(name="itemName")
    private String name;

    @ColumnInfo(name="quantity")
    private int quantity;

    @ColumnInfo(name="cost")
    private double cost;

    @ColumnInfo(name="description")
    private String description;

    @ColumnInfo(name="frozen")
    private Boolean frozen;

    @ColumnInfo(name="location")
    private String location;

    public Item(String name, int quantity, double cost, String description, Boolean frozen, String location) {
        this.name = name;
        this.quantity = quantity;
        this.cost = cost;
        this.description = description;
        this.frozen = frozen;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost() {
        return cost;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getFrozen() {
        return frozen;
    }

    public String getLocation() {
        return location;
    }
}
