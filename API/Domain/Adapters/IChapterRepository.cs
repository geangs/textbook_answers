using Domain.Entities;

namespace Domain.Adapters
{
    public interface IChapterRepository
    {
        void AddChapter(Chapter chapter);
        IEnumerable<Chapter> GetBookChapters(int bookId);
    }
}