package br.com.fiap.foodshare.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import br.com.fiap.foodshare.models.Doacao;
import br.com.fiap.foodshare.models.Doador;
import br.com.fiap.foodshare.models.Produto;
import br.com.fiap.foodshare.models.Receptor;
import br.com.fiap.foodshare.models.Solicitacao;
import br.com.fiap.foodshare.models.SolicitacaoProduto;
import br.com.fiap.foodshare.repository.ClienteRepository;
import br.com.fiap.foodshare.repository.DoacaoRepository;
import br.com.fiap.foodshare.repository.DoadorRepository;
import br.com.fiap.foodshare.repository.ProdutoRepository;
import br.com.fiap.foodshare.repository.ReceptorRepository;
import br.com.fiap.foodshare.repository.SolicitacaoProdutoRepository;
import br.com.fiap.foodshare.repository.SolicitacaoRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {
    @Autowired
    private DoadorRepository doadorRepository;

    @Autowired
    private ReceptorRepository receptorRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DoacaoRepository doacaoRepository;

    @Autowired
    private SolicitacaoRepository solicitacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private SolicitacaoProdutoRepository solicitacaoProdutoRepository;

    @Override
    public void run(String... args) throws Exception {

        Doador doador = new Doador();
        doador.setCpf("123.456.789-00");
        doador.setNomeCompleto("Nome do Doador");

        Receptor receptor = new Receptor();

        receptor.setCpf("123.456.789-30");
        receptor.setNomeCompleto("Gustavo viado");

        Doacao doacao = new Doacao();
        doacao.setDataDoacao(LocalDateTime.now());
        doacao.setStatus("Pendente");

        Doacao doacao2 = new Doacao();
        doacao2.setDataDoacao(LocalDateTime.now());
        doacao2.setStatus("Pendente");

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setReceptor(receptor);
        solicitacao.setDataSolicitacao(LocalDateTime.now());
        solicitacao.setStatus("Pendente");

        doador = doadorRepository.save(doador);

        doacao.setDoador(doador);

        doacao2.setDoador(doador);

        receptorRepository.save(receptor);

        doacaoRepository.save(doacao);

        doacaoRepository.save(doacao2);

        solicitacaoRepository.save(solicitacao);

        System.out.println(clienteRepository.count());

        criaProdutos();

        receptorRealizaSolicitacao();

        doadorFazDoacao();

    }

    public void receptorRealizaSolicitacao() {
        var receptor = receptorRepository.findById(2l).get();
        var produto = produtoRepository.findById(1l).get();
        var produto2 = produtoRepository.findById(2l).get();

        Solicitacao solicitacao = new Solicitacao();
        solicitacao.setReceptor(receptor);
        solicitacao.setDataSolicitacao(LocalDateTime.now());
        solicitacao.setStatus("Pendente");

        var receptorXsolicitacao = solicitacaoRepository.save(solicitacao);


        SolicitacaoProduto solicitacaoProduto = new SolicitacaoProduto();

        solicitacaoProduto.setSolicitacao(receptorXsolicitacao);
        solicitacaoProduto.setProduto(produto);
        solicitacaoProduto.setQuantidadeProduto(3);

        SolicitacaoProduto solicitacaoProduto2= new SolicitacaoProduto();

        solicitacaoProduto2.setSolicitacao(receptorXsolicitacao);
        solicitacaoProduto2.setProduto(produto2);
        solicitacaoProduto2.setQuantidadeProduto(1);

        System.out.println(solicitacaoProduto);

        solicitacaoProdutoRepository.save(solicitacaoProduto);
        solicitacaoProdutoRepository.save(solicitacaoProduto2);



    }


    public void doadorFazDoacao(){
        Doador doador = doadorRepository.findById(1l).get();
        SolicitacaoProduto solicitacaoProduto = solicitacaoProdutoRepository.findById(1l).get();

        Doacao doacao = new Doacao();

        doacao.setDataDoacao(LocalDateTime.now());
        doacao.setDoador(doador);
        doacao.setSolicitacaoProduto(solicitacaoProduto);
        doacao.setStatus("Aguardando retirada");

        doacaoRepository.save(doacao);


    }

    public void criaProdutos() {
        // Criação de produtos de doação

        // Produto 1
        Produto produto1 = new Produto();
        produto1.setNomeProduto("Arroz");
        produto1.setPeso(2.5);
        produto1.setDescricaoProduto("Arroz branco");

        // Produto 2
        Produto produto2 = new Produto();
        produto2.setNomeProduto("Feijão");
        produto2.setPeso(1.0);
        produto2.setDescricaoProduto("Feijão carioca");

        // Produto 3
        Produto produto3 = new Produto();
        produto3.setNomeProduto("Óleo de Soja");
        produto3.setPeso(0.9);
        produto3.setDescricaoProduto("Óleo de soja refinado");

        // Salvar os produtos no banco de dados
        produtoRepository.save(produto1);
        produtoRepository.save(produto2);
        produtoRepository.save(produto3);

    }

}