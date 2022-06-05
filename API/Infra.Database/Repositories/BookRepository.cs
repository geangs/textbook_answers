using Domain.Adapters;
using Domain.Entities;
using Infra.Database.Context;

namespace Infra.Database.Repositories
{
    public class BookRepository : IBookRepository
    {
        private readonly TextBookAnswersContext textBookAnswersContext;

        public BookRepository(TextBookAnswersContext textBookAnswersContext)
        {
            this.textBookAnswersContext = textBookAnswersContext;
        }
        public void Add(Book book)
        {
            textBookAnswersContext.Books.Add(book);
            textBookAnswersContext.SaveChanges();
        }

        public Book? Get(int id)
        {
            return textBookAnswersContext.Books.Find(id);
        }

        public IEnumerable<Book> GetAll()
        {
            return textBookAnswersContext.Books.AsEnumerable();
        }
    }
}