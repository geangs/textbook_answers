using Domain.Entities;
using Domain.Services;
using Domain.Adapters;

namespace Aplication.Services
{
    public class BookService : IBookService
    {
        private readonly IBookRepository bookRepository;

        public BookService(IBookRepository bookRepository)
        {
            this.bookRepository = bookRepository;
        }
        public void Add(Book book)
        {
            bookRepository.Add(book);
        }

        public Book? Get(int id)
        {
            return bookRepository.Get(id);
        }

        public IEnumerable<Book> GetAll()
        {
            return bookRepository.GetAll();
        }
    }
}