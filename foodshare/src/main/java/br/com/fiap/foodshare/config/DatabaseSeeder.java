//package br.com.fiap.foodshare.config;
//
//import java.time.LocalDateTime;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import br.com.fiap.foodshare.models.Doacao;
//import br.com.fiap.foodshare.models.Doador;
//import br.com.fiap.foodshare.models.Endereco;
//import br.com.fiap.foodshare.models.Produto;
//import br.com.fiap.foodshare.models.Receptor;
//import br.com.fiap.foodshare.models.Solicitacao;
//import br.com.fiap.foodshare.models.SolicitacaoProduto;
//import br.com.fiap.foodshare.models.Usuario;
//import br.com.fiap.foodshare.models.enums.Status;
//import br.com.fiap.foodshare.repository.ClienteRepository;
//import br.com.fiap.foodshare.repository.DoacaoRepository;
//import br.com.fiap.foodshare.repository.DoadorRepository;
//import br.com.fiap.foodshare.repository.ProdutoRepository;
//import br.com.fiap.foodshare.repository.ReceptorRepository;
//import br.com.fiap.foodshare.repository.SolicitacaoProdutoRepository;
//import br.com.fiap.foodshare.repository.SolicitacaoRepository;
//import br.com.fiap.foodshare.repository.UsuarioRepository;
//import br.com.fiap.foodshare.services.TokenService;
//
//@Configuration
//public class DatabaseSeeder implements CommandLineRunner {
//    @Autowired
//    private DoadorRepository doadorRepository;
//
//    @Autowired
//    private ReceptorRepository receptorRepository;
//
//    @Autowired
//    private ClienteRepository clienteRepository;
//
//    @Autowired
//    private DoacaoRepository doacaoRepository;
//
//    @Autowired
//    private SolicitacaoRepository solicitacaoRepository;
//
//    @Autowired
//    private ProdutoRepository produtoRepository;
//
//    @Autowired
//    private SolicitacaoProdutoRepository solicitacaoProdutoRepository;
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Autowired
//    AuthenticationManager manager;
//
//    @Autowired
//    PasswordEncoder encoder;
//
//    @Autowired
//    TokenService tokenService;
//
//    @Override
//    public void run(String... args) throws Exception {
//
//        Doador doador = new Doador();
//        doador.setCpf("123.456.789-00");
//        doador.setNomeCompleto("Nome do Doador");
//
//        Receptor receptor = new Receptor();
//
//        Endereco receptorEndereco = criaEndereco();
//        Endereco doadorEndereco = criaEndereco();
//        receptor.setEndereco(receptorEndereco);
//
//        receptor.setCpf("123.456.789-30");
//        receptor.setNomeCompleto("Gustavo Receptor");
//        receptor.setEndereco(receptorEndereco);
//        receptorEndereco.setCliente(receptor);
//
//        Doacao doacao = new Doacao();
//        doacao.setData(LocalDateTime.now());
//        doacao.setStatus(Status.AGUARDANDO);
//
//        Doacao doacao2 = new Doacao();
//        doacao2.setData(LocalDateTime.now());
//        doacao2.setStatus(Status.AGUARDANDO);
//
//        Solicitacao solicitacao = new Solicitacao();
//        solicitacao.setReceptor(receptor);
//        solicitacao.setData(LocalDateTime.now());
//        solicitacao.setStatus(Status.AGUARDANDO);
//
//        doador.setEndereco(doadorEndereco);
//        doadorEndereco.setCliente(doador);
//
//        doador = doadorRepository.save(doador);
//
//        doacao.setDoador(doador);
//
//        doacao2.setDoador(doador);
//
//        receptorRepository.save(receptor);
//
//        var clientesNoRaio = clienteRepository.buscarClientesNoRaio(-23.5792551, -46.641581, 10.0);
//
//        clientesNoRaio.forEach(c -> System.out.println(c));
//
//        // doacaoRepository.save(doacao);
//
//        // doacaoRepository.save(doacao2);
//
//        solicitacaoRepository.save(solicitacao);
//
//        criaProdutos();
//
//        receptorRealizaSolicitacao();
//
//        doadorFazDoacao();
//
//        cadastrarUsuario();
//
//        cadastrarClienteEEndereco();
//
//    }
//
//    public Endereco criaEndereco() {
//        Endereco endereco = new Endereco();
//        endereco.setCep("01234-567");
//        endereco.setBairro("Centro");
//        endereco.setLogradouro("Rua Principal");
//        endereco.setNumero("123");
//        endereco.setComplemento("Apt 456");
//        endereco.setCidade("São Paulo");
//        endereco.setEstado("São Paulo");
//        endereco.setUf("SP");
//        endereco.setLatitude(-23.572798279092712);
//        endereco.setLongitude(-46.636276245117195);
//
//        return endereco;
//    }
//
//    public void cadastrarUsuario() {
//        var cliente = clienteRepository.findById(1l).get();
//
//        Usuario usuarioCadadastrar = new Usuario();
//
//        usuarioCadadastrar.setEmail("luan.reis@fiap.com.br");
//        usuarioCadadastrar.setSenha(encoder.encode("senha123"));
//        usuarioCadadastrar.setCliente(cliente);
//        usuarioRepository.save(usuarioCadadastrar);
//
//    }
//
//    public void cadastrarClienteEEndereco() {
//        // Criar um novo cliente
//        Doador cliente = new Doador();
//        cliente.setNomeCompleto("Nome do cliente");
//        cliente.setCpf("122.456.789-00");
//        // ...
//
//        Endereco endereco = new Endereco();
//        endereco.setCep("01234-567");
//        endereco.setBairro("Centro");
//        endereco.setLogradouro("Rua Principal");
//        endereco.setNumero("123");
//        endereco.setComplemento("Apt 456");
//        endereco.setCidade("São Paulo");
//        endereco.setEstado("São Paulo");
//        endereco.setUf("SP");
//        endereco.setLongitude(-46.63563251495361);
//        endereco.setLatitude(-23.579091596182877);
//
//        // Associar o endereço ao cliente
//        cliente.setEndereco(endereco);
//        endereco.setCliente(cliente);
//
//        // Salvar o cliente e endereço
//        clienteRepository.save(cliente);
//
//    }
//
//    public void receptorRealizaSolicitacao() {
//        var receptor = receptorRepository.findById(2l).get();
//        var produto = produtoRepository.findById(1l).get();
//        var produto2 = produtoRepository.findById(2l).get();
//
//        Solicitacao solicitacao = new Solicitacao();
//        solicitacao.setReceptor(receptor);
//        solicitacao.setData(LocalDateTime.now());
//        solicitacao.setStatus(Status.AGUARDANDO);
//
//        var receptorXsolicitacao = solicitacaoRepository.save(solicitacao);
//
//        SolicitacaoProduto solicitacaoProduto = new SolicitacaoProduto();
//
//        solicitacaoProduto.setSolicitacao(receptorXsolicitacao);
//        solicitacaoProduto.setProduto(produto);
//        solicitacaoProduto.setQuantidade(3);
//
//        SolicitacaoProduto solicitacaoProduto2 = new SolicitacaoProduto();
//
//        solicitacaoProduto2.setSolicitacao(receptorXsolicitacao);
//        solicitacaoProduto2.setProduto(produto2);
//        solicitacaoProduto2.setQuantidade(1);
//
//        solicitacaoProdutoRepository.save(solicitacaoProduto);
//        solicitacaoProdutoRepository.save(solicitacaoProduto2);
//
//        solicitacaoRepository.findById(2l).get();
//    }
//
//    public void doadorFazDoacao() {
//        Doador doador = doadorRepository.findById(1l).get();
//        SolicitacaoProduto solicitacaoProduto = solicitacaoProdutoRepository.findById(1l).get();
//
//        Doacao doacao = new Doacao();
//
//        doacao.setData(LocalDateTime.now());
//        doacao.setDoador(doador);
//        doacao.setSolicitacaoProduto(solicitacaoProduto);
//        doacao.setStatus(Status.AGUARDANDO);
//
//        doacaoRepository.save(doacao);
//
//        solicitacaoRepository.existsDoacaoBySolicitacaoProduto(solicitacaoProduto);
//
//    }
//
//    public void criaProdutos() {
//        // Criação de produtos de doação
//
//        // Produto 1
//        Produto produto1 = new Produto();
//        produto1.setNome("Arroz");
//        produto1.setPeso(2.5);
//        produto1.setDescricao("Arroz branco");
//
//        // Produto 2
//        Produto produto2 = new Produto();
//        produto2.setNome("Feijão");
//        produto2.setPeso(1.0);
//        produto2.setDescricao("Feijão carioca");
//
//        // Produto 3
//        Produto produto3 = new Produto();
//        produto3.setNome("Óleo de Soja");
//        produto3.setPeso(0.9);
//        produto3.setDescricao("Óleo de soja refinado");
//
//        // Salvar os produtos no banco de dados
//        produtoRepository.save(produto1);
//        produtoRepository.save(produto2);
//        produtoRepository.save(produto3);
//
//    }
//
//}