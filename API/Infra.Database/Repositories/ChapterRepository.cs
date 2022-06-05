using Domain.Adapters;
using Domain.Entities;
using Infra.Database.Context;

namespace Infra.Database.Repositories
{
    public class ChapterRepository : IChapterRepository
    {
        private readonly TextBookAnswersContext context;

        public ChapterRepository(TextBookAnswersContext context)
        {
            this.context = context;
        }

        public IEnumerable<Chapter> GetBookChapters(int bookId)
        {
            return context.Chapters.Where(c => c.BookId == bookId).AsEnumerable();
        }

        public void AddChapter(Chapter chapter)
        {
            context.Chapters.Add(chapter);
            context.SaveChanges();
        }
    }
}
