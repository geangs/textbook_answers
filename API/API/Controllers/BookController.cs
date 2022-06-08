using Microsoft.AspNetCore.Mvc;
using Domain.Entities;
using Domain.Adapters;

namespace API.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class BookController : Controller
    {
        private readonly IBookRepository bookRepository;

        public BookController(IBookRepository bookRepository)
        {
            this.bookRepository = bookRepository;
        }

        [HttpGet]
        public IEnumerable<Book> GetAllBooks()
        {
            return bookRepository.GetAll();
        }

        [HttpPost]
        public void Add(Book book)
        {
            bookRepository.Add(book);
        }

        [HttpGet("{bookId:int}")]
        public Book? GetById(int bookId)
        {
            return bookRepository.Get(bookId);
        }

        [HttpGet("search/{query}")]
        public IEnumerable<Book> Search(string query)
        {
            return bookRepository.SearchBooks(query);
        }
    }
}
