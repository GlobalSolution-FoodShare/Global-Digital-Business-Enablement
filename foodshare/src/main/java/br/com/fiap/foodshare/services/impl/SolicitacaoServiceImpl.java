package br.com.fiap.foodshare.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.foodshare.dto.SolicitacaoDTO;
import br.com.fiap.foodshare.dto.SolicitacaoProdutoDTO;
import br.com.fiap.foodshare.dto.responseDTO.SolicitacaoProdutoResponseDTO;
import br.com.fiap.foodshare.dto.responseDTO.SolicitacaoResponseDTO;
import br.com.fiap.foodshare.exception.BadRequestException;
import br.com.fiap.foodshare.exception.RestNotFoundException;
import br.com.fiap.foodshare.models.Produto;
import br.com.fiap.foodshare.models.Receptor;
import br.com.fiap.foodshare.models.Solicitacao;
import br.com.fiap.foodshare.models.SolicitacaoProduto;
import br.com.fiap.foodshare.models.enums.Status;
import br.com.fiap.foodshare.repository.ProdutoRepository;
import br.com.fiap.foodshare.repository.ReceptorRepository;
import br.com.fiap.foodshare.repository.SolicitacaoProdutoRepository;
import br.com.fiap.foodshare.repository.SolicitacaoRepository;
import br.com.fiap.foodshare.services.SolicitacaoService;

@Service
public class SolicitacaoServiceImpl implements SolicitacaoService {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	@Autowired
	private ReceptorRepository receptorRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private SolicitacaoProdutoRepository solicitacaoProdutoRepository;

	@Override
	public Page<SolicitacaoResponseDTO> buscarTodos(Pageable pageable) {
		Page<Solicitacao> solicitacoes = solicitacaoRepository.findAll(pageable);

		return solicitacoes.map(solicitacao -> {
			SolicitacaoResponseDTO solicitacaoResponseDTO = new SolicitacaoResponseDTO(solicitacao);
			solicitacaoResponseDTO.getSolicitacaoProduto()
					.forEach(c -> c.setJaFoiDoado(verificaDoacoesProdutoSolicitacao(c)));
			return solicitacaoResponseDTO;
		});
	}

	@Override
	public SolicitacaoResponseDTO buscarPorId(Long id) {
		Solicitacao solicitacao = solicitacaoRepository.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Cliente não localizado"));

		SolicitacaoResponseDTO solicitaoResponse = new SolicitacaoResponseDTO(solicitacao);
		solicitaoResponse.getSolicitacaoProduto().forEach(c -> c.setJaFoiDoado(verificaDoacoesProdutoSolicitacao(c)));

		return solicitaoResponse;
	}

	@Override
	public SolicitacaoResponseDTO cadastrar(SolicitacaoDTO solicitacaoDTO) {
		Solicitacao solicitacao = new Solicitacao();

		Optional<Receptor> receptorOpt = receptorRepository.findById(solicitacaoDTO.getCliente());
		if (receptorOpt.isPresent()) {
			solicitacao.setReceptor(receptorOpt.get());
		} else {
			throw new RestNotFoundException("Receptor com ID " + solicitacaoDTO.getCliente() + " não encontrado.");
		}

		solicitacao.setData(LocalDateTime.now());
		solicitacao.setStatus(Status.AGUARDANDO);

		List<SolicitacaoProduto> solicitacaoProdutos = new ArrayList<>();
		for (SolicitacaoProdutoDTO spDto : solicitacaoDTO.getSolicitacoesProduto()) {
			SolicitacaoProduto sp = new SolicitacaoProduto();

			Optional<Produto> produtoOpt = produtoRepository.findById(spDto.getProduto().getId());
			if (produtoOpt.isPresent()) {
				sp.setProduto(produtoOpt.get());
			} else {
				throw new RestNotFoundException("Produto com ID " + spDto.getProduto().getId() + " não encontrado.");
			}

			sp.setQuantidade(spDto.getQuantidade());

			sp.setSolicitacao(solicitacao);
			solicitacaoProdutos.add(sp);
		}

		solicitacao.setSolicitacaoProduto(solicitacaoProdutos);

		Solicitacao savedSolicitacao = solicitacaoRepository.save(solicitacao);

		return new SolicitacaoResponseDTO(savedSolicitacao);
	}

	@Override
	public SolicitacaoResponseDTO atualizar(Long id, SolicitacaoDTO solicitacaoDTO) {
		Solicitacao solicitacao = solicitacaoRepository.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Solicitação não encontrada com o id: " + id));

		if (solicitacao.getStatus() == Status.CANCELADO) {
			throw new BadRequestException("Solicitação concluída ou cancelada");
		}

		if (solicitacaoDTO.getCliente() != null) {
			Receptor receptor = receptorRepository.findById(solicitacaoDTO.getCliente())
					.orElseThrow(() -> new RestNotFoundException(
							"Receptor não encontrado com o id: " + solicitacaoDTO.getCliente()));
			solicitacao.setReceptor(receptor);
		}

		Set<Long> idsProdutosDto = solicitacaoDTO.getSolicitacoesProduto().stream()
				.map(spDto -> spDto.getProduto().getId()).collect(Collectors.toSet());

		List<SolicitacaoProduto> spToRemove = solicitacao.getSolicitacaoProduto().stream()
				.filter(sp -> !idsProdutosDto.contains(sp.getProduto().getId())).collect(Collectors.toList());

		solicitacao.getSolicitacaoProduto().removeAll(spToRemove);

		for (SolicitacaoProduto sp : spToRemove) {
			solicitacaoProdutoRepository.delete(sp);
		}

		for (SolicitacaoProdutoDTO spDto : solicitacaoDTO.getSolicitacoesProduto()) {
			SolicitacaoProduto sp = solicitacao.getSolicitacaoProduto().stream().filter(
					solicitacaoProduto -> solicitacaoProduto.getProduto().getId().equals(spDto.getProduto().getId()))
					.findFirst().orElse(new SolicitacaoProduto());

			sp.setQuantidade(spDto.getQuantidade());

			if (sp.getProduto() == null) {
				Produto produto = produtoRepository.findById(spDto.getProduto().getId())
						.orElseThrow(() -> new RestNotFoundException(
								"Produto não encontrado com o id: " + spDto.getProduto().getId()));
				sp.setProduto(produto);
			}

			sp.setSolicitacao(solicitacao);
			solicitacao.getSolicitacaoProduto().add(sp);
		}

		Solicitacao updatedSolicitacao = solicitacaoRepository.save(solicitacao);

		return new SolicitacaoResponseDTO(updatedSolicitacao);
	}

	@Override
	public void deletar(Long id) {
		Solicitacao solicitacao = solicitacaoRepository.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Solicitação não encontrada com o id: " + id));

		solicitacaoRepository.delete(solicitacao);
	}

	@Override
	public SolicitacaoResponseDTO atualizarStatus(Long id, Status novoStatus) {
		Solicitacao solicitacao = solicitacaoRepository.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Solicitação não encontrada com o id: " + id));

		solicitacao.setStatus(novoStatus);

		Solicitacao solicitacaoAtualizada = solicitacaoRepository.save(solicitacao);

		return new SolicitacaoResponseDTO(solicitacaoAtualizada);
	}

	private boolean verificaDoacoesProdutoSolicitacao(SolicitacaoProdutoResponseDTO solicitacaoProdutoResponse) {
		SolicitacaoProduto solicitacaoProduto = new SolicitacaoProduto();
		solicitacaoProduto.setId(solicitacaoProdutoResponse.getId());

		return solicitacaoRepository.existsDoacaoBySolicitacaoProduto(solicitacaoProduto);
	}

	@Override
	public List<SolicitacaoResponseDTO> buscarSolicitacoesPorIdCliente(Long idCliente) {
		Receptor receptor = receptorRepository.findById(idCliente)
				.orElseThrow(() -> new RestNotFoundException("Receptor com ID " + idCliente + " não encontrado."));

		List<Solicitacao> solicitacoes = solicitacaoRepository.findByReceptor(receptor);

		return solicitacoes.stream().map(solicitacao -> {
			SolicitacaoResponseDTO solicitacaoResponseDTO = new SolicitacaoResponseDTO(solicitacao);
			solicitacaoResponseDTO.getSolicitacaoProduto()
					.forEach(c -> c.setJaFoiDoado(verificaDoacoesProdutoSolicitacao(c)));
			return solicitacaoResponseDTO;
		}).collect(Collectors.toList());
	}
}
