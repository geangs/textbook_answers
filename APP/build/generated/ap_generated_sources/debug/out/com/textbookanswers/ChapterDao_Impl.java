package com.textbookanswers;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ChapterDao_Impl implements ChapterDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Chapter> __insertionAdapterOfChapter;

  private final SharedSQLiteStatement __preparedStmtOfNuke;

  public ChapterDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfChapter = new EntityInsertionAdapter<Chapter>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Chapter` (`id`,`ownerId`,`number`,`name`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Chapter value) {
        stmt.bindLong(1, value.id);
        stmt.bindLong(2, value.ownerId);
        stmt.bindLong(3, value.number);
        if (value.name == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.name);
        }
      }
    };
    this.__preparedStmtOfNuke = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Chapter";
        return _query;
      }
    };
  }

  @Override
  public void insertChapter(final Chapter chapter) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfChapter.insert(chapter);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void nuke() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfNuke.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfNuke.release(_stmt);
    }
  }

  @Override
  public List<Chapter> getChapters(final int bookId) {
    final String _sql = "SELECT * FROM Chapter WHERE ownerId = :bookId";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, bookId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfOwnerId = CursorUtil.getColumnIndexOrThrow(_cursor, "ownerId");
      final int _cursorIndexOfNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "number");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final List<Chapter> _result = new ArrayList<Chapter>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Chapter _item;
        final int _tmpOwnerId;
        _tmpOwnerId = _cursor.getInt(_cursorIndexOfOwnerId);
        final int _tmpNumber;
        _tmpNumber = _cursor.getInt(_cursorIndexOfNumber);
        _item = new Chapter(_tmpOwnerId,_tmpNumber);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        _item.name = _cursor.getString(_cursorIndexOfName);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
