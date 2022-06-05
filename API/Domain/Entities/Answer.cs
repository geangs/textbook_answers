using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Domain.Entities
{
    public class Answer
    {
        public int Id { get; set; }
        public string Text { get; set; }
        public int Upvotes { get; set; }
        public int Downvotes { get; set; }

        public int Score => Upvotes - Downvotes;

        public int QuestionId { get; set; }
        public Question Question { get; set; }

        public int AuthorId { get; set; }
        public User Author { get; set; }
    }
}
