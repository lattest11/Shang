package greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import greendao.Product;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table PRODUCT.
*/
public class ProductDao extends AbstractDao<Product, Long> {

    public static final String TABLENAME = "PRODUCT";

    /**
     * Properties of entity Product.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Entity_id = new Property(0, Long.class, "entity_id", true, "ENTITY_ID");
        public final static Property Type_id = new Property(1, String.class, "type_id", false, "TYPE_ID");
        public final static Property Name = new Property(2, String.class, "name", false, "NAME");
        public final static Property Sku = new Property(3, String.class, "sku", false, "SKU");
        public final static Property Manufacturer = new Property(4, String.class, "manufacturer", false, "MANUFACTURER");
        public final static Property Short_description = new Property(5, String.class, "short_description", false, "SHORT_DESCRIPTION");
        public final static Property Price = new Property(6, Float.class, "price", false, "PRICE");
        public final static Property Special_price = new Property(7, Float.class, "special_price", false, "SPECIAL_PRICE");
        public final static Property Small_image_url = new Property(8, String.class, "small_image_url", false, "SMALL_IMAGE_URL");
        public final static Property Thumbnail_url = new Property(9, String.class, "thumbnail_url", false, "THUMBNAIL_URL");
        public final static Property News_from_date = new Property(10, String.class, "news_from_date", false, "NEWS_FROM_DATE");
        public final static Property News_to_date = new Property(11, String.class, "news_to_date", false, "NEWS_TO_DATE");
        public final static Property Created_at = new Property(12, String.class, "created_at", false, "CREATED_AT");
        public final static Property Updated_at = new Property(13, String.class, "updated_at", false, "UPDATED_AT");
        public final static Property Is_product_new = new Property(14, Integer.class, "is_product_new", false, "IS_PRODUCT_NEW");
        public final static Property Is_product_sale = new Property(15, Integer.class, "is_product_sale", false, "IS_PRODUCT_SALE");
    };


    public ProductDao(DaoConfig config) {
        super(config);
    }
    
    public ProductDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'PRODUCT' (" + //
                "'ENTITY_ID' INTEGER PRIMARY KEY ," + // 0: entity_id
                "'TYPE_ID' TEXT," + // 1: type_id
                "'NAME' TEXT," + // 2: name
                "'SKU' TEXT," + // 3: sku
                "'MANUFACTURER' TEXT," + // 4: manufacturer
                "'SHORT_DESCRIPTION' TEXT," + // 5: short_description
                "'PRICE' REAL," + // 6: price
                "'SPECIAL_PRICE' REAL," + // 7: special_price
                "'SMALL_IMAGE_URL' TEXT," + // 8: small_image_url
                "'THUMBNAIL_URL' TEXT," + // 9: thumbnail_url
                "'NEWS_FROM_DATE' TEXT," + // 10: news_from_date
                "'NEWS_TO_DATE' TEXT," + // 11: news_to_date
                "'CREATED_AT' TEXT," + // 12: created_at
                "'UPDATED_AT' TEXT," + // 13: updated_at
                "'IS_PRODUCT_NEW' INTEGER," + // 14: is_product_new
                "'IS_PRODUCT_SALE' INTEGER);"); // 15: is_product_sale
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'PRODUCT'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Product entity) {
        stmt.clearBindings();
 
        Long entity_id = entity.getEntity_id();
        if (entity_id != null) {
            stmt.bindLong(1, entity_id);
        }
 
        String type_id = entity.getType_id();
        if (type_id != null) {
            stmt.bindString(2, type_id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(3, name);
        }
 
        String sku = entity.getSku();
        if (sku != null) {
            stmt.bindString(4, sku);
        }
 
        String manufacturer = entity.getManufacturer();
        if (manufacturer != null) {
            stmt.bindString(5, manufacturer);
        }
 
        String short_description = entity.getShort_description();
        if (short_description != null) {
            stmt.bindString(6, short_description);
        }
 
        Float price = entity.getPrice();
        if (price != null) {
            stmt.bindDouble(7, price);
        }
 
        Float special_price = entity.getSpecial_price();
        if (special_price != null) {
            stmt.bindDouble(8, special_price);
        }
 
        String small_image_url = entity.getSmall_image_url();
        if (small_image_url != null) {
            stmt.bindString(9, small_image_url);
        }
 
        String thumbnail_url = entity.getThumbnail_url();
        if (thumbnail_url != null) {
            stmt.bindString(10, thumbnail_url);
        }
 
        String news_from_date = entity.getNews_from_date();
        if (news_from_date != null) {
            stmt.bindString(11, news_from_date);
        }
 
        String news_to_date = entity.getNews_to_date();
        if (news_to_date != null) {
            stmt.bindString(12, news_to_date);
        }
 
        String created_at = entity.getCreated_at();
        if (created_at != null) {
            stmt.bindString(13, created_at);
        }
 
        String updated_at = entity.getUpdated_at();
        if (updated_at != null) {
            stmt.bindString(14, updated_at);
        }
 
        Integer is_product_new = entity.getIs_product_new();
        if (is_product_new != null) {
            stmt.bindLong(15, is_product_new);
        }
 
        Integer is_product_sale = entity.getIs_product_sale();
        if (is_product_sale != null) {
            stmt.bindLong(16, is_product_sale);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Product readEntity(Cursor cursor, int offset) {
        Product entity = new Product( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // entity_id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // type_id
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // sku
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // manufacturer
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // short_description
            cursor.isNull(offset + 6) ? null : cursor.getFloat(offset + 6), // price
            cursor.isNull(offset + 7) ? null : cursor.getFloat(offset + 7), // special_price
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // small_image_url
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // thumbnail_url
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // news_from_date
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // news_to_date
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // created_at
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // updated_at
            cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14), // is_product_new
            cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15) // is_product_sale
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Product entity, int offset) {
        entity.setEntity_id(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setType_id(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setSku(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setManufacturer(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setShort_description(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPrice(cursor.isNull(offset + 6) ? null : cursor.getFloat(offset + 6));
        entity.setSpecial_price(cursor.isNull(offset + 7) ? null : cursor.getFloat(offset + 7));
        entity.setSmall_image_url(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setThumbnail_url(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setNews_from_date(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setNews_to_date(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setCreated_at(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setUpdated_at(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setIs_product_new(cursor.isNull(offset + 14) ? null : cursor.getInt(offset + 14));
        entity.setIs_product_sale(cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Product entity, long rowId) {
        entity.setEntity_id(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Product entity) {
        if(entity != null) {
            return entity.getEntity_id();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
