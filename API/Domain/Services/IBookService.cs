using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Services
{
    public interface IBookService
    {
        IEnumerable<Book> GetAll();
        Book? Get(int id);
        void Add(Book book);

    }
}
