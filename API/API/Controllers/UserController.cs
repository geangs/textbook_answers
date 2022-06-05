using Domain.Adapters;
using Domain.Entities;
using Microsoft.AspNetCore.Mvc;

namespace API.Controllers
{
    [ApiController]
    [Route("[controller]")]
    public class UserController : Controller
    {
        public readonly record struct LoginData(string login, string password);
        private readonly IUserRepository userRepository;

        public UserController(IUserRepository userRepository)
        {
            this.userRepository = userRepository;
        }
        [HttpPost]
        public void Add(User user)
        {
            if (string.IsNullOrEmpty(user.Name) 
                || string.IsNullOrEmpty(user.Login) 
                || string.IsNullOrEmpty(user.Password)
                || string.IsNullOrEmpty(user.Email)
                )
            {
                Response.StatusCode = 400;
                return;
            }
            userRepository.Add(user);
        }

        [HttpPost("login")]
        public bool Login(LoginData loginData)
        {
            if (string.IsNullOrEmpty(loginData.login)
                || string.IsNullOrEmpty(loginData.password)
                )
            {
                Response.StatusCode = 400;
                return false;
            }
            var result = userRepository.Authenticate(loginData.login, loginData.password);
            if(result)
            {
                return true;
            }
            Response.StatusCode = 403;
            return false;
        }
    }
}
