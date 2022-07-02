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
    class BookRepositoryTest
    {
        [Test]
        public void BookRepository_Get_ReturnBook()
        {
            var book = new Book()
            {
                Id = 1,
                Title = "Teste",
                Cover = "",
                Author = "Felipe",
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Books.Find(It.IsAny<int>())).Returns(book);


            var bookRepository = new BookRepository(contextMock.Object);



            bookRepository.Get(1);

            contextMock.Verify(context => context.Books.Find(1), Times.Once());

        }


        [Test]
        public void BookRepository_AddBook_SavesChanges()
        {
            var book = new Book()
            {
                Id = 1,
                Title = "Teste",
                Cover = "",
                Author = "Felipe",
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Books.Add(It.IsAny<Book>()));

            var bookRepository = new BookRepository(contextMock.Object);
            bookRepository.Add(book);

            contextMock.Verify(context => context.Books.Add(It.IsAny<Book>()), Times.Once());
            contextMock.Verify(context => context.SaveChanges(), Times.Once());
        }

        [Test]
        public void BookRepositorySearchBooks_ReturnBooks()
        {
            var books = new List<Book>() { 
            new Book() {
                Id = 1,
                Title = "Teste",
                Cover = "",
                Author = "Felipe",
            },
            new Book()
            {
                Id = 2,
                Title = "Biologia",
                Cover = "",
                Author = "Felipe",

            }
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Books).ReturnsDbSet(books);


            var bookRepository = new BookRepository(contextMock.Object);



            var resultadoBusca = bookRepository.SearchBooks("biologia");

            resultadoBusca.Should().NotBeEmpty();
            resultadoBusca.Should().HaveCount(1);

        }
    }
}
