using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using Domain.Entities;

namespace Infra.Database.Context
{
    public class TextBookAnswersContext : DbContext
    {
        public TextBookAnswersContext(DbContextOptions<TextBookAnswersContext> options) : base(options) { }

        public TextBookAnswersContext()
        {

        }

        public virtual DbSet<Book> Books { get; set; }
        public virtual DbSet<User> Users { get; set; }
        public virtual DbSet<Chapter> Chapters { get; set; }
        public virtual DbSet<Question> Questions { get; set; }
        public virtual DbSet<Answer> Answers { get; set; }
    }
}
