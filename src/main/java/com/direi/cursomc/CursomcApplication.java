package com.direi.cursomc;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.direi.cursomc.domain.Categoria;
import com.direi.cursomc.domain.Cidade;
import com.direi.cursomc.domain.Cliente;
import com.direi.cursomc.domain.Endereco;
import com.direi.cursomc.domain.Estado;
import com.direi.cursomc.domain.Produto;
import com.direi.cursomc.domain.enums.TipoCliente;
import com.direi.cursomc.repositories.CategoriaRepository;
import com.direi.cursomc.repositories.CidadeRepository;
import com.direi.cursomc.repositories.ClienteRepository;
import com.direi.cursomc.repositories.EnderecoRepository;
import com.direi.cursomc.repositories.EstadoRepository;
import com.direi.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Construção");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Nouse", 50.00);
		
		cat1.getProdutos().addAll((Arrays.asList(p1,p2,p3)));
		cat2.getProdutos().addAll((Arrays.asList(p2)));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2,cat3));
		
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
	
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Valdirei Felipe", "valdirei@gmail.com", "09988090890", TipoCliente.PESSOAFISICA);
		Cliente cli2 = new Cliente(null, "Gabriele Silva", "gabriele@gmail.com", "24543454", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("3199988988","329099099"));
		
		Endereco e1 = new Endereco(null, "Rua Geraldo de Almeida", "92", "Casa A", "Mangueiras", "30666370", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua Eilat", "87", "Casa A", "Olaria", "30666370", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		
		clienteRepository.saveAll(Arrays.asList(cli1,cli2));
		
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		
	}

}
