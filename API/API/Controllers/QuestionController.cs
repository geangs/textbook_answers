using Domain.Adapters;
using Domain.Entities;
using Infra.Database.Repositories;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;

namespace API.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class QuestionController : ControllerBase
    {
        private readonly IQuestionRepository questionRepository;

        public QuestionController(IQuestionRepository questionRepository)
        {
            this.questionRepository = questionRepository;
        }

        [HttpGet("{chapterId:int}")]
        public IEnumerable<Question> GetChapterQuestions(int chapterId)
        {
            return questionRepository.GetChapterQuestions(chapterId);
        }

        [HttpPost]
        public void AddChapter(Question question)
        {
            if (String.IsNullOrEmpty(question.Text) || question.ChapterId == 0)
            {
                Response.StatusCode = 400;
            return;
            }
            questionRepository.AddQuestion(question);
        }
    }
}
