using Domain.Adapters;
using Domain.Entities;
using Infra.Database.Context;
using Microsoft.AspNetCore.Cryptography.KeyDerivation;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace Infra.Database.Repositories
{
    

    public class UserRepository : IUserRepository
    {
        private readonly TextBookAnswersContext context;

        public UserRepository(TextBookAnswersContext context)
        {
            this.context = context;
        }
        public void Add(User user)
        {
            user.Password = CreateNewHashedPassword(user.Password);
            context.Users.Add(user);
            context.SaveChanges();
        }

        public User? GetUser(int id)
        {
            return context.Users.Find(id);
        }

        public User? GetUserByLogin(string login)
        {
            return context.Users.SingleOrDefault(user => user.Login == login);
        }

        public bool Authenticate(string login, string password)
        {
            var user = context.Users.FirstOrDefault(x => x.Login == login);
            if (user == null)
                return false;
            if (VerifyPassword(user.Password, password))
                return true;
            return false;
        }

        private string CreateNewHashedPassword(string password)
        {
            byte[] salt = new byte[128 / 8];
            using (var rngCsp = RandomNumberGenerator.Create())
            {
                rngCsp.GetNonZeroBytes(salt);
            }

            string base46Salt = Convert.ToBase64String(salt);

            string hashedPassword = GetHashedPassword(password, salt);


            return $"{base46Salt}.{hashedPassword}";
        }

        private bool VerifyPassword(string hashedPassword, string password)
        {
            var splitHash = hashedPassword.Split('.');
            if (splitHash.Length != 2)
                return false;

            var base64Salt = splitHash[0];
            var hash = splitHash[1];
            var salt = Convert.FromBase64String(base64Salt);

            if (hash == GetHashedPassword(password, salt))
            {
                return true;
            }

            return false;

        }

        private static string GetHashedPassword(string password, byte[] salt)
        {
            return Convert.ToBase64String(KeyDerivation.Pbkdf2(
                            password: password,
                            salt: salt,
                            prf: KeyDerivationPrf.HMACSHA256,
                            iterationCount: 100000,
                            numBytesRequested: 256 / 8));
        }

    }
}
