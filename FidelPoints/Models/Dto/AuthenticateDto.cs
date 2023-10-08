using System;
using System.ComponentModel.DataAnnotations;

namespace FidelPoints.Models.Dto
{
	public class AuthenticateDto
	{
        [Required]

		public int Id { get; set; }

        [Required]

        public string Password { get; set; }

        public AuthenticateDto()
		{
		}
	}
}

