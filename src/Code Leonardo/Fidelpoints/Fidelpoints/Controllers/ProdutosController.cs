using Fidelpoints.Model;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace Fidelpoints.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProdutosController : ControllerBase
    {
        private readonly AppDbContext _context;
        public ProdutosController(AppDbContext context)
        {
            _context = context;



        }
        [HttpGet]
        public async Task<ActionResult> Getall()
        {
            var product = await _context.Produtos.ToListAsync();
            return Ok(product);
        }



        [HttpPost]
        public async Task<ActionResult> Create(Produto product)
        {
            await _context.AddAsync(product);
            await _context.SaveChangesAsync();
            return Ok(product);
        }
        [HttpGet("id")]
        public async Task<ActionResult> GetById(int id)
        {
            var product = await _context.Produtos.FindAsync(id);
            if (product == null) return BadRequest(new { message = "Id não encontrado" });
            return Ok(product);
        }
        [HttpPut("id")]
        public async Task<ActionResult> Update(int id, Produto product)
        {
            if (product.Id != id) return BadRequest();
            if (await _context.Produtos.AsNoTracking()
                    .FirstOrDefaultAsync(c => c.Id == id) == null) return NotFound();
            _context.Produtos.Update(product);
            await _context.SaveChangesAsync();
            return NoContent();
        }



        [HttpDelete("id")]
        public async Task<ActionResult> Delete(int id)
        {
            var product = await _context.Produtos.FindAsync(id);
            if (product == null) return NotFound();
            _context.Produtos.Remove(product);
            await _context.SaveChangesAsync();
            return NoContent();
        }
    }
}
    

