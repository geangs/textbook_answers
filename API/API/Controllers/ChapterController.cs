using Domain.Adapters;
using Domain.Entities;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace API.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class ChapterController : ControllerBase
    {
        private readonly IChapterRepository chapterRepository;

        public ChapterController(IChapterRepository chapterRepository)
        {
            this.chapterRepository = chapterRepository;
        }

        [HttpGet("{bookId:int}")]
        public IEnumerable<Chapter> GetBookChapters(int bookId)
        {
            return chapterRepository.GetBookChapters(bookId);
        }

        [HttpPost]
        public void AddChapter(Chapter chapter)
        {
            if (String.IsNullOrEmpty(chapter.Name) || chapter.BookId == 0)
            {
                Response.StatusCode = 400;
                return;
            }
            chapterRepository.AddChapter(chapter);
        }
    }
}
