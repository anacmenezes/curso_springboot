package com.springboot.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.springboot.domain.Categoria;
import com.springboot.domain.Cidade;
import com.springboot.domain.Cliente;
import com.springboot.domain.Endereco;
import com.springboot.domain.Estado;
import com.springboot.domain.ItemPedido;
import com.springboot.domain.Pagamento;
import com.springboot.domain.PagamentoComBoleto;
import com.springboot.domain.PagamentoComCartao;
import com.springboot.domain.Pedido;
import com.springboot.domain.Produto;
import com.springboot.domain.enums.EstadoPagamento;
import com.springboot.domain.enums.TipoCliente;

@Service
public class DBService {
	
//	@Autowired
//	private CategoriaRepository categoriaRepository;

	public void instatiateTestDatabase() throws ParseException {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
//		Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
//		Categoria cat4 = new Categoria(null, "Eletrônicos");
//		Categoria cat5 = new Categoria(null, "Jardinagem");
//		Categoria cat6 = new Categoria(null, "Decoração");
//		Categoria cat7 = new Categoria(null, "Perfumaria");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("07/07/2022 18:10"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/08/2022 14:27"), cli1, e1);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("06/11/2022 16:42"), null);
		ped2.setPagamento(pagto2);
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
//		categoriaRepository.saveAll(Arrays.asList());
		
	}
	
}
