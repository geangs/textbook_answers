using Domain.Adapters;
using Domain.Entities;
using Infra.Database.Context;

namespace Infra.Database.Repositories
{
    public class QuestionRepository : IQuestionRepository
    {
        private readonly TextBookAnswersContext context;

        public QuestionRepository(TextBookAnswersContext context)
        {
            this.context = context;
        }

        public IEnumerable<Question> GetChapterQuestions(int chapterId)
        {
            return context.Questions.Where(question => question.ChapterId == chapterId).AsEnumerable();
        }

        public void AddQuestion(Question question)
        {
            context.Questions.Add(question);
            context.SaveChanges();
        }
    }
}
