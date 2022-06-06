using Domain.Adapters;
using Domain.Entities;
using Infra.Database.Context;

namespace Infra.Database.Repositories
{
    public class AnswerRepository : IAnswerRepository
    {
        private readonly TextBookAnswersContext context;

        public AnswerRepository(TextBookAnswersContext context)
        {
            this.context = context;
        }

        public IEnumerable<Answer> GetQuestionAnswers(int questionId)
        {
            return context.Answers.Where(item => item.QuestionId == questionId).AsEnumerable();
        }

        public void UpvoteAnswer(int answerId)
        {
            var answer = context.Answers.Find(answerId);
            if (answer == null)
            {
                return;
            }
            answer.Upvotes += 1;
            context.SaveChanges();
        }

        public void DownvoteAnswer(int answerId)
        {
            var answer = context.Answers.Find(answerId);
            if (answer == null)
            {
                return;
            }
            answer.Downvotes += 1;
            context.SaveChanges();
        }

        public void AddAnswer(Answer answer)
        {
            context.Answers.Add(answer);
            context.SaveChanges();
        }
    }
}
