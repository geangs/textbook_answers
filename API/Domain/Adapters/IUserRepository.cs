using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Adapters
{
    public interface IUserRepository
    {
        void Add(User user);
        bool Authenticate(string login, string password);
        User? GetUser(int id);
        User? GetUserByLogin(string login);
    }
}
