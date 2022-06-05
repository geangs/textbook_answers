using Domain.Entities;

namespace Domain.Adapters
{
    public interface IAnswerRepository
    {
        void DownvoteAnswer(int answerId);
        IEnumerable<Answer> GetQuestionAnswers(int questionId);
        void UpvoteAnswer(int answerId);
    }
}