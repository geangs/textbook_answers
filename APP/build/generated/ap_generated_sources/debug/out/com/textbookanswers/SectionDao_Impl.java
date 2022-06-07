package com.textbookanswers;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SectionDao_Impl implements SectionDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Section> __insertionAdapterOfSection;

  public SectionDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfSection = new EntityInsertionAdapter<Section>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Section` (`id`,`ownerId`,`number`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Section value) {
        stmt.bindLong(1, value.id);
        stmt.bindLong(2, value.ownerId);
        stmt.bindLong(3, value.number);
      }
    };
  }

  @Override
  public void insertSubchapter(final Section section) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfSection.insert(section);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Section> getSubchapters(final int ownerId) {
    final String _sql = "SELECT * FROM Section WHERE ownerId = :ownerId";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, ownerId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfOwnerId = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerId");
      final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
      final List<Section> _result = new ArrayList<Section>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Section _item;
        final int _tmpOwnerId;
        _tmpOwnerId = _cursor.getInt(_cursorIndexOfOwnerId);
        final int _tmpNumber;
        _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
        _item = new Section(_tmpOwnerId,_tmpNumber);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
