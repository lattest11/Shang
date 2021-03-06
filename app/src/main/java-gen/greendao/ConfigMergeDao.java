package greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import greendao.ConfigMerge;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table CONFIG_MERGE.
*/
public class ConfigMergeDao extends AbstractDao<ConfigMerge, Long> {

    public static final String TABLENAME = "CONFIG_MERGE";

    /**
     * Properties of entity ConfigMerge.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "ID");
        public final static Property Attribute_id = new Property(1, Long.class, "attribute_id", false, "ATTRIBUTE_ID");
        public final static Property Option_id = new Property(2, Long.class, "option_id", false, "OPTION_ID");
        public final static Property Product_id = new Property(3, Long.class, "product_id", false, "PRODUCT_ID");
        public final static Property Is_chosen_att = new Property(4, Integer.class, "is_chosen_att", false, "IS_CHOSEN_ATT");
        public final static Property Is_chosen_opt = new Property(5, Integer.class, "is_chosen_opt", false, "IS_CHOSEN_OPT");
        public final static Property Position = new Property(6, Integer.class, "position", false, "POSITION");
    };


    public ConfigMergeDao(DaoConfig config) {
        super(config);
    }
    
    public ConfigMergeDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'CONFIG_MERGE' (" + //
                "'ID' INTEGER PRIMARY KEY ," + // 0: id
                "'ATTRIBUTE_ID' INTEGER," + // 1: attribute_id
                "'OPTION_ID' INTEGER," + // 2: option_id
                "'PRODUCT_ID' INTEGER," + // 3: product_id
                "'IS_CHOSEN_ATT' INTEGER," + // 4: is_chosen_att
                "'IS_CHOSEN_OPT' INTEGER," + // 5: is_chosen_opt
                "'POSITION' INTEGER);"); // 6: position
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'CONFIG_MERGE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, ConfigMerge entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long attribute_id = entity.getAttribute_id();
        if (attribute_id != null) {
            stmt.bindLong(2, attribute_id);
        }
 
        Long option_id = entity.getOption_id();
        if (option_id != null) {
            stmt.bindLong(3, option_id);
        }
 
        Long product_id = entity.getProduct_id();
        if (product_id != null) {
            stmt.bindLong(4, product_id);
        }
 
        Integer is_chosen_att = entity.getIs_chosen_att();
        if (is_chosen_att != null) {
            stmt.bindLong(5, is_chosen_att);
        }
 
        Integer is_chosen_opt = entity.getIs_chosen_opt();
        if (is_chosen_opt != null) {
            stmt.bindLong(6, is_chosen_opt);
        }
 
        Integer position = entity.getPosition();
        if (position != null) {
            stmt.bindLong(7, position);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public ConfigMerge readEntity(Cursor cursor, int offset) {
        ConfigMerge entity = new ConfigMerge( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // attribute_id
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2), // option_id
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // product_id
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // is_chosen_att
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // is_chosen_opt
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6) // position
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, ConfigMerge entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAttribute_id(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setOption_id(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
        entity.setProduct_id(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setIs_chosen_att(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setIs_chosen_opt(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setPosition(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(ConfigMerge entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(ConfigMerge entity) {
        if(entity != null) {
            return entity.getId();
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
