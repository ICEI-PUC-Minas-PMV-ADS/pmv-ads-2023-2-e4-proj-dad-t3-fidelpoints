using Fidelpoints.Model;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace Fidelpoints.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ClientesController : ControllerBase
    {
        private readonly AppDbContext _context;
        public ClientesController(AppDbContext context)
        {
            _context = context;



        }
        [HttpGet]
        public async Task<ActionResult> Getall()
        {
            var product = await _context.Clientes.ToListAsync();
            return Ok(product);
        }



        [HttpPost]
        public async Task<ActionResult> Create(Cliente cliente)
        {
            await _context.AddAsync(cliente);
            await _context.SaveChangesAsync();
            return Ok(cliente);
        }
        [HttpGet("id")]
        public async Task<ActionResult> GetById(int id)
        {
            var cliente = await _context.Clientes.FindAsync(id);
            if (cliente == null) return BadRequest(new { message = "Id não encontrado" });
            return Ok(cliente);
        }
        [HttpPut("id")]
        public async Task<ActionResult> Update(int id, Cliente cliente)
        {
            if (cliente.Id != id) return BadRequest();
            if (await _context.Clientes.AsNoTracking()
                    .FirstOrDefaultAsync(c => c.Id == id) == null) return NotFound();
            _context.Clientes.Update(cliente);
            await _context.SaveChangesAsync();
            return NoContent();
        }



        [HttpDelete("id")]
        public async Task<ActionResult> Delete(int id)
        {
            var cliente = await _context.Clientes.FindAsync(id);
            if (cliente == null) return NotFound();
            _context.Clientes.Remove(cliente);
            await _context.SaveChangesAsync();
            return NoContent();
        }
    }
}
