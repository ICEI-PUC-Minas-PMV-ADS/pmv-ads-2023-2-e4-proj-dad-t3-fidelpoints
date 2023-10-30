using System.ComponentModel.DataAnnotations;

namespace Fidelpoints.Model
{
    public class Cliente
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public string Name { get; set; }
        [Required]
        public string Email { get; set; }
        [Required]
        public string Password { get; set; }
        [Required]
        public int Point { get; set; }
        public string Cpf { get; set; }
    }
}
