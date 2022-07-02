using Domain.Entities;
using FluentAssertions;
using Infra.Database.Context;
using Infra.Database.Repositories;
using Moq;
using Moq.EntityFrameworkCore;
using NUnit.Framework;
using System.Collections.Generic;

namespace UnitTests.Infra
{
    class QuestionRepositoryTest
    {
        [Test]
        public void QuestionRepository_AddQuestion_SavesChanges()
        {
            var Question = new Question()
            {
                Id = 1,
                Text = "Teste",
                QuestionNumber = 1,
                ChapterId = 1,
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Questions.Add(It.IsAny<Question>()));

            var QuestionRepository = new QuestionRepository(contextMock.Object);
            QuestionRepository.AddQuestion(Question);

            contextMock.Verify(context => context.Questions.Add(It.IsAny<Question>()), Times.Once());
            contextMock.Verify(context => context.SaveChanges(), Times.Once());
        }

        [Test]
        public void QuestionRepositorySearchQuestions_ReturnQuestions()
        {
            var Questions = new List<Question>() {
            new Question()
            {
                Id = 1,
                Text = "Teste",
                QuestionNumber = 1,
                ChapterId = 1,
            },
            new Question()
            {
                Id = 2,
                Text = "Teste 2",
                QuestionNumber = 1,
                ChapterId = 2,
            }
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Questions).ReturnsDbSet(Questions);


            var QuestionRepository = new QuestionRepository(contextMock.Object);



            var resultadoBusca = QuestionRepository.GetChapterQuestions(1);

            resultadoBusca.Should().NotBeEmpty();
            resultadoBusca.Should().HaveCount(1);

        }
    }
}
