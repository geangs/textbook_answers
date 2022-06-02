using Aplication.Services;
using Domain.Services;
using Microsoft.Extensions.DependencyInjection;

namespace Aplication
{
    public static class ApplicationModuleConfiguration
    {

        public static void AddApplicationDependencies(this IServiceCollection services)
        {
            services.AddScoped<IBookService, BookService>();
        }
    }
}
