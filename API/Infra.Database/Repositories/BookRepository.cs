using Domain.Adapters;
using Domain.Entities;
using Infra.Database.Context;
using System.Linq;

namespace Infra.Database.Repositories
{
    public class BookRepository : IBookRepository
    {
        private readonly TextBookAnswersContext context;

        public BookRepository(TextBookAnswersContext context)
        {
            this.context = context;
        }
        public void Add(Book book)
        {
            context.Books.Add(book);
            context.SaveChanges();
        }

        public Book? Get(int id)
        {
            return context.Books.Find(id);
        }

        public IEnumerable<Book> GetAll()
        {
            return context.Books.AsEnumerable();
        }



        public IEnumerable<Book> SearchBooks(string query)
        {
            return context.Books.Where(item => item.Title.ToLowerInvariant().Contains(query.ToLowerInvariant()) || 
            item.Author.ToLowerInvariant().Contains(query.ToLowerInvariant()));
        }
    }
}