using Domain.Adapters;
using Domain.Entities;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace API.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class AnswerController : ControllerBase
    {
        private readonly IAnswerRepository answerRepository;

        public AnswerController(IAnswerRepository answerRepository)
        {
            this.answerRepository = answerRepository;
        }

        [HttpGet("{questionId:int}")]
        public IEnumerable<Answer> GetQuestionAnswers(int questionId)
        {
            return answerRepository.GetQuestionAnswers(questionId);
        }

        [HttpGet("{answerId:int}/upvote")]
        public void Upvote(int answerId)
        {
            answerRepository.UpvoteAnswer(answerId);
        }

        [HttpGet("{answerId:int}/downvote")]
        public void Downvote(int answerId)
        {
            answerRepository.DownvoteAnswer(answerId);
        }

        [HttpPost]
        public void Add(Answer answer)
        {
            if (String.IsNullOrEmpty(answer.Text)
                || answer.QuestionId == 0
                || answer.AuthorId == 0
                )
            {
                Response.StatusCode = 400;
                return;
            }
            answerRepository.AddAnswer(answer);
        }
    }
}
