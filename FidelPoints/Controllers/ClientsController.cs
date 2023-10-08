using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Text;
using FidelPoints.Models;
using FidelPoints.Models.Dto;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;

namespace FidelPoints.Controllers
{
    [Authorize(Roles = "Administrador")]
    [Route("api/[controller]")]
    [ApiController]
    public class ClientsController : ControllerBase
    {
        private readonly AppDbContext _context;
        public ClientsController(AppDbContext context)
        {
            _context = context;
        }
        [HttpGet]
        public async Task<ActionResult> Getall()
        {
            var client = await _context.Clients.ToListAsync();
            return Ok(client);
        }

        [HttpGet("cpf")]
        public async Task<ActionResult> GetClientByCpf(string cpf)
        {
            var client = await _context.Clients
                .FirstAsync(c => c.Cpf == cpf);
            return Ok(client);
        }

        [HttpGet("extrato")]
        public async Task<ActionResult> GetExtratoClientByCpf(string cpf)
        {
            var client = await _context.Orders
                .Include(c => c.Sellers)
                .Include(c => c.Clients)
                .FirstAsync(c => c.Clients.Cpf == cpf);
            return Ok(client);
        }

        [HttpPost]
        public async Task<ActionResult> Create(Client client)
        {
            client.Password =BCrypt.Net.BCrypt.HashPassword(client.Password);
            await _context.AddAsync(client);
            await _context.SaveChangesAsync();
            return Ok(client);
        }
        [HttpGet("id")]
        public async Task<ActionResult> GetById(int id)
        {
            var client = await _context.Clients.FindAsync(id);
            if (client == null) return BadRequest(new { message = "Id não encontrado" });
            return Ok(client);
        }

        [HttpPut("id")]
        public async Task<ActionResult> Update(int id, Client client)
        {
            if (client.Id != id) return BadRequest();
            if (await _context.Clients.AsNoTracking()
                    .FirstOrDefaultAsync(c => c.Id == id) == null) return NotFound();
            _context.Clients.Update(client);
            await _context.SaveChangesAsync();
            return NoContent();
        }

        [HttpDelete("id")]
        public async Task<ActionResult> Delete(int id)
        {
            var client = await _context.Clients.FindAsync(id);
            if (client == null) return NotFound();
            _context.Clients.Remove(client);
            await _context.SaveChangesAsync();
            return NoContent();
        }

        [AllowAnonymous]
        [HttpPost("authenticate")]
        public async Task<ActionResult> Authenticate(AuthenticateDto client)
        {
            var clientDb = await _context.Clients.FindAsync(client.Id);

            if (clientDb == null) return
                    NotFound();

            if (clientDb == null || !BCrypt.Net.BCrypt.Verify(client.Password, clientDb.Password))
                return Unauthorized();

            var jwt = GenerateJwtToken(clientDb);

            return Ok(new { jwtToken = jwt });
        }


        private string GenerateJwtToken(Client client)
        {
            var tokenHandler = new JwtSecurityTokenHandler();
            var key = Encoding.ASCII.GetBytes("8{i^cDdTt~=d/|7Ek>3!3;a0vmI$7Hv|");
            var claims = new ClaimsIdentity(new Claim[]
            {
                    new Claim(ClaimTypes.NameIdentifier, client.Id.ToString()),
            });
            var tokenDescriptor = new SecurityTokenDescriptor
            {
                Subject = claims,
                Expires = DateTime.UtcNow.AddHours(8),
                SigningCredentials = new SigningCredentials(new SymmetricSecurityKey(key),
                SecurityAlgorithms.HmacSha256Signature)
            };
            var token = tokenHandler.CreateToken(tokenDescriptor);
            return tokenHandler.WriteToken(token);
        }

    }
}
