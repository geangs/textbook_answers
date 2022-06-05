using Domain.Entities;

namespace Domain.Adapters
{
    public interface IQuestionRepository
    {
        void AddQuestion(Question question);
        IEnumerable<Question> GetChapterQuestions(int chapterId);
    }
}