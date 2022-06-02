using Microsoft.AspNetCore.Mvc;
using Domain.Services;
using Domain.Entities;

namespace API.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class BookController : Controller
    {
        private readonly IBookService bookService;

        public BookController(IBookService bookService)
        {
            this.bookService = bookService;
        }

        [HttpGet]
        public IEnumerable<Book> GetAllBooks()
        {
            return bookService.GetAll();
        }

        [HttpPost]
        public void Add(Book book)
        {
            bookService.Add(book);
        }

        [HttpGet("{bookId:int}")]
        public Book? GetById(int bookId)
        {
            return bookService.Get(bookId);
        }
    }
}
