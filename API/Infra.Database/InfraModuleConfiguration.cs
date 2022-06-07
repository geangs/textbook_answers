using Domain.Adapters;
using Infra.Database.Context;
using Infra.Database.Repositories;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using static System.Formats.Asn1.AsnWriter;

namespace Infra.Database
{
    public static class InfraModuleConfiguration
    {
        public static void AddDatabaseConfiguration(this IServiceCollection services, string connectionString)
        {
            services.AddDbContext<TextBookAnswersContext>(options =>
                options.UseSqlServer(connectionString));
            services.AddScoped<IBookRepository, BookRepository>();
            services.AddScoped<IUserRepository, UserRepository>();
            services.AddScoped<IChapterRepository, ChapterRepository>();
            services.AddScoped<IQuestionRepository, QuestionRepository>();
            services.AddScoped<IAnswerRepository, AnswerRepository>();
        }
        public static void SeedDatabase(this IServiceProvider serviceProvider)
        {
            using (var scope = serviceProvider.CreateScope())
            {
                var services = scope.ServiceProvider;
                try
                {
                    var context = services.GetRequiredService<TextBookAnswersContext>();
                    // Para recriar o banco de dados para aplicar as mudanças no modelo, descomentar linha abaixo
                    //context.Database.EnsureDeleted();
                    context.Database.EnsureCreated();

                }
                catch (Exception ex)
                {
                    var logger = services.GetRequiredService<ILogger<TextBookAnswersContext>>();
                    logger.LogError(ex, "An error occurred while seeding the database.");
                }
            }
        }
    }
}
