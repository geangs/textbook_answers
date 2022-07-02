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
    public class UserRepositoryTeste
    {
        [Test]
        public void UserRepository_AddUser_SavesChanges()
        {
            var user = new User()
            {
                Name = "Teste",
                Email = "Teste",
                Login = "Teste",
                Password = "Teste"
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Users.Add(It.IsAny<User>()));

            var userRepository = new UserRepository(contextMock.Object);



            userRepository.Add(user);

            contextMock.Verify(context => context.Users.Add(It.IsAny<User>()), Times.Once());
            contextMock.Verify(context => context.SaveChanges(), Times.Once());
        }

        [Test]
        public void UserRepository_AddUser_HashesPassword()
        {
            var user = new User()
            {
                Name = "Teste",
                Email = "Teste",
                Login = "Teste",
                Password = "Teste"
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Users.Add(It.IsAny<User>()));

            var userRepository = new UserRepository(contextMock.Object);



            userRepository.Add(user);
            user.Password.Should().NotBe("Teste");
        }

        [Test]
        public void UserRepository_AddUser_HashedPassowrd_ShouldContainSalt()
        {
            var user = new User()
            {
                Name = "Teste",
                Email = "Teste",
                Login = "Teste",
                Password = "Teste"
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Users.Add(It.IsAny<User>()));

            var userRepository = new UserRepository(contextMock.Object);



            userRepository.Add(user);
            user.Password.Should().Contain(".");
        }

        [Test]
        public void UserRepository_Authenticate_SuccessfulyAuthenticate()
        {
            var user = new User()
            {
                Name = "Teste",
                Email = "Teste",
                Login = "Teste",
                Password = "zrH8FyfhqmcnXdHBkOBY7w==.ik6GzApeYcdgeRMx7ExLLpnQB7+o6WRQzw2ujlwTCJk=" //Hashed password "Teste"
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Users).ReturnsDbSet(new List<User>() { user});

            var userRepository = new UserRepository(contextMock.Object);



            var response = userRepository.Authenticate("Teste","Teste");
            response.Should().BeTrue();
        }

        [Test]
        public void UserRepository_Authenticate_WrongPassword_FailsAuthentication()
        {
            var user = new User()
            {
                Name = "Teste",
                Email = "Teste",
                Login = "Teste",
                Password = "zrH8FyfhqmcnXdHBkOBY7w==.ik6GzApeYcdgeRMx7ExLLpnQB7+o6WRQzw2ujlwTCJk=" //Hashed password "Teste"
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Users).ReturnsDbSet(new List<User>() { user });

            var userRepository = new UserRepository(contextMock.Object);



            var response = userRepository.Authenticate("Teste", "asdasdasdasd");
            response.Should().BeFalse();
        }

        [Test]
        public void UserRepository_Authenticate_UserNotExists_FailsAuthentication()
        {
            var user = new User()
            {
                Name = "Teste",
                Email = "Teste",
                Login = "Teste",
                Password = "zrH8FyfhqmcnXdHBkOBY7w==.ik6GzApeYcdgeRMx7ExLLpnQB7+o6WRQzw2ujlwTCJk=" //Hashed password "Teste"
            };

            var contextMock = new Mock<TextBookAnswersContext>();
            contextMock.Setup(x => x.Users).ReturnsDbSet(new List<User>() { user });

            var userRepository = new UserRepository(contextMock.Object);



            var response = userRepository.Authenticate("asdasdasdasdaasd", "Teste");
            response.Should().BeFalse();
        }
    }
}
