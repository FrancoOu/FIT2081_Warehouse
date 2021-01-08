package provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;

public class ItemContentProvider extends ContentProvider {
    public static final String CONTENT_AUTHORITY = "fit2081.app.Gaoyuan";

    public static final Uri CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    ItemDatabase db;
    public ItemContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        int deleteCount;

        // TODO: get trailling number/ID
        deleteCount=db.getOpenHelper().getWritableDatabase().delete("Items", "itemId = 3" ,selectionArgs);
        return deleteCount;
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // TODO: Implement this to handle requests to insert a new row.
        long rowId = db.getOpenHelper().getWritableDatabase().insert("Items",0,values);

        return ContentUris.withAppendedId(CONTENT_URI,rowId);
    }

    @Override
    public boolean onCreate() {
        // TODO: Implement this to initialize your content provider on startup.
        db=ItemDatabase.getDatabase(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        // TODO: Implement this to handle query requests from clients.
        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
        builder.setTables("Items");
        String query =builder.buildQuery(projection,selection,null,null,sortOrder,null);

        final Cursor cursor = db.getOpenHelper().getReadableDatabase().query(query,selectionArgs);
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        int updateCount;

        updateCount = db.getOpenHelper().getWritableDatabase().update("Items",0,values,selection,selectionArgs);
        return updateCount;
    }
}