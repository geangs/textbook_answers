using Domain.Entities;
using FluentAssertions;
using Microsoft.AspNetCore.Mvc.Testing;
using Microsoft.VisualStudio.TestPlatform.TestHost;
using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Text.Json;
using System.Threading.Tasks;

namespace IntegrationTest
{
    class UserTest
    {
        [Test]
        public async Task Get_BookEndpointReturnSuccessAndCorrectContentType()
        {
            var application = new WebApplicationFactory<Program>();
            var client = application.CreateClient();

            var response = await client.GetAsync("/Book");

            response.EnsureSuccessStatusCode(); // Status Code 200-299
            response.Content.Headers.ContentType.ToString().Should().Be("application/json; charset=utf-8");
        }

        [Test, Order(1)]
        public async Task Post_AddUserEndpointReturnSuccess()
        {
            var application = new WebApplicationFactory<Program>();
            var client = application.CreateClient();

            string json = JsonSerializer.Serialize(new User()
            {
                Name = "Teste",
                Email = "Teste",
                Login = "teste",
                Password = "teste" //Hashed password "Teste"
            });
            var content = new StringContent(
              json,
              Encoding.UTF8,
              "application/json"
            );

            var response = await client.PostAsync("/User", content);

            response.EnsureSuccessStatusCode(); // Status Code 200-299
        }

        [Test, Order(2)]
        public async Task Post_AuthenticateEndpointReturnSuccess()
        {
            var application = new WebApplicationFactory<Program>();
            var client = application.CreateClient();

            string json = JsonSerializer.Serialize(new
            {
                login = "Teste",
                password = "teste"
            });
            var content = new StringContent(
              json,
              Encoding.UTF8,
              "application/json"
            );

            var response = await client.PostAsync("/User/login", content);

            response.EnsureSuccessStatusCode(); // Status Code 200-299
            response.Content.Headers.ContentType.ToString().Should().Be("application/json; charset=utf-8");
            var autheticationResult = await response.Content.ReadAsStringAsync();
            autheticationResult.Should().Be("true");
        }

        [Test, Order(2)]
        public async Task Post_AuthenticateEndpointReturnFailure()
        {
            var application = new WebApplicationFactory<Program>();
            var client = application.CreateClient();

            string json = JsonSerializer.Serialize(new
            {
                login = "Teste",
                password = "asdasdasdasas"
            });
            var content = new StringContent(
              json,
              Encoding.UTF8,
              "application/json"
            );

            var response = await client.PostAsync("/User/login", content);

            response.StatusCode.Should().Be(System.Net.HttpStatusCode.Forbidden); // Status Code 403
            response.Content.Headers.ContentType.ToString().Should().Be("application/json; charset=utf-8");
            var autheticationResult = await response.Content.ReadAsStringAsync();
            autheticationResult.Should().Be("false");
        }
    }
}
