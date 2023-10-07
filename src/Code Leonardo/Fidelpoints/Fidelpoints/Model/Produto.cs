using System.ComponentModel.DataAnnotations;

namespace Fidelpoints.Model
{
    public class Produto
    {
        [Key]
        public int Id { get; set; }
        [Required]
        public string Name { get; set; }
        [Required]
        public int Point { get; set; }
        [Required]
        public string Description { get; set; }
        [Required]
        public string Image { get; set; }
    }
}
