using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;

namespace Fidelpoints.Model
{
    
        public class AppDbContext : DbContext
        {
            public AppDbContext(DbContextOptions options) : base(options)
            {
            }
            public DbSet<Cliente> Clientes { get; set; }
            public DbSet<Produto> Produtos { get; set; }






        }
    
}
