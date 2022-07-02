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
    class ChapterRepositoryTest
    {
        [Test]
        public void ChapterRepository_AddChapter_SavesChanges()
        {
            var Chapter = new Chapter()
            {
                Id = 1,
                Name = "Teste",
                ChapterNumber = 1,
                BookId = 1,
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Chapters.Add(It.IsAny<Chapter>()));

            var ChapterRepository = new ChapterRepository(contextMock.Object);
            ChapterRepository.AddChapter(Chapter);

            contextMock.Verify(context => context.Chapters.Add(It.IsAny<Chapter>()), Times.Once());
            contextMock.Verify(context => context.SaveChanges(), Times.Once());
        }

        [Test]
        public void ChapterRepositorySearchChapters_ReturnChapters()
        {
            var Chapters = new List<Chapter>() {
            new Chapter() 
            {
                Id = 1,
                Name = "Teste",
                ChapterNumber = 1,
                BookId = 1,
            },
            new Chapter()
            {
                Id = 2,
                Name = "Teste 2",
                ChapterNumber = 1,
                BookId = 2,
            }
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Chapters).ReturnsDbSet(Chapters);


            var ChapterRepository = new ChapterRepository(contextMock.Object);



            var resultadoBusca = ChapterRepository.GetBookChapters(1);

            resultadoBusca.Should().NotBeEmpty();
            resultadoBusca.Should().HaveCount(1);

        }
    }
}
