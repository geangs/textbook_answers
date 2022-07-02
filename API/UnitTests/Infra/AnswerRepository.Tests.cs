using Domain.Entities;
using FluentAssertions;
using Infra.Database.Context;
using Infra.Database.Repositories;
using Moq;
using NUnit.Framework;

namespace UnitTests.Infra
{
    class AnswerRepositoryTest
    {

        [Test]
        public void AnswerRepository_UpvoteAnswer_CallSaveChanges()
        {
            var answer = new Answer()
            {
                Id = 1,
                Text = "Teste",
                Upvotes = 0,
                Downvotes = 0,
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Answers.Find(It.IsAny<int>())).Returns(answer);


            var answerRepository = new AnswerRepository(contextMock.Object);



            answerRepository.UpvoteAnswer(1);

            contextMock.Verify(context => context.SaveChanges(), Times.Once());

        }

        [Test]
        public void AnswerRepository_UpvoteAnswer_ChangesScore()
        {
            var answer = new Answer()
            {
                Id = 1,
                Text = "Teste",
                Upvotes = 0,
                Downvotes = 0,
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Answers.Find(It.IsAny<int>())).Returns(answer);


            var answerRepository = new AnswerRepository(contextMock.Object);



            answerRepository.UpvoteAnswer(1);

            answer.Score.Should().Be(1);
        }

        [Test]
        public void AnswerRepository_DownvoteAnswer_ChangesScore()
        {
            var answer = new Answer()
            {
                Id = 1,
                Text = "Teste",
                Upvotes = 0,
                Downvotes = 0,
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Answers.Find(It.IsAny<int>())).Returns(answer);


            var answerRepository = new AnswerRepository(contextMock.Object);



            answerRepository.DownvoteAnswer(1);

            answer.Score.Should().Be(-1);
        }
        //AddAnswer

        [Test]
        public void AnswerRepository_AddAnswer_SavesChanges()
        {
            var answer = new Answer()
            {
                Text = "Teste",
                Upvotes = 0,
                Downvotes = 0,
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Answers.Add(It.IsAny<Answer>()));

            var answerRepository = new AnswerRepository(contextMock.Object);



            answerRepository.AddAnswer(answer);

            contextMock.Verify(context => context.Answers.Add(It.IsAny<Answer>()), Times.Once());
            contextMock.Verify(context => context.SaveChanges(), Times.Once());
        }
    }
}
