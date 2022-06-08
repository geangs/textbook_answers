using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Adapters
{
    public interface IBookRepository
    {
        IEnumerable<Book> GetAll();
        Book? Get(int id);
        void Add(Book book);
        IEnumerable<Book> SearchBooks(string query);
    }
}
