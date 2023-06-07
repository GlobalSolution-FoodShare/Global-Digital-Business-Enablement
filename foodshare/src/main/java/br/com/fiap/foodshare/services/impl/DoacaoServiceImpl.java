package br.com.fiap.foodshare.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.foodshare.dto.DoacaoDTO;
import br.com.fiap.foodshare.dto.SolicitacaoProdutoDTO;
import br.com.fiap.foodshare.dto.responseDTO.DoacaoResponseDTO;
import br.com.fiap.foodshare.dto.responseDTO.SolicitacaoProdutoResponseDTO;
import br.com.fiap.foodshare.exception.BadRequestException;
import br.com.fiap.foodshare.exception.RestNotFoundException;
import br.com.fiap.foodshare.models.Doacao;
import br.com.fiap.foodshare.models.Doador;
import br.com.fiap.foodshare.models.SolicitacaoProduto;
import br.com.fiap.foodshare.models.enums.Status;
import br.com.fiap.foodshare.repository.DoacaoRepository;
import br.com.fiap.foodshare.repository.DoadorRepository;
import br.com.fiap.foodshare.repository.SolicitacaoProdutoRepository;
import br.com.fiap.foodshare.repository.SolicitacaoRepository;
import br.com.fiap.foodshare.services.DoacaoService;

@Service
public class DoacaoServiceImpl implements DoacaoService {

	@Autowired
	private DoacaoRepository doacaoRepository;

	@Autowired
	private DoadorRepository doadorRepository;

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	@Autowired
	private SolicitacaoProdutoRepository solicitacaoProdutoRepository;

	@Override
	public Page<DoacaoResponseDTO> buscarTodos(Pageable pageable) {
		Page<Doacao> doacoes = doacaoRepository.findAll(pageable);
		return doacoes.map(doacao -> new DoacaoResponseDTO(doacao));
	}

	@Override
	public DoacaoResponseDTO buscarPorId(Long id) {
		Doacao doacao = doacaoRepository.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Doação não encontrada com o id: " + id));

		return new DoacaoResponseDTO(doacao);
	}

	@Override
	public DoacaoResponseDTO cadastrar(DoacaoDTO doacaoDTO) {
		Doacao doacao = new Doacao();

		doacao.setStatus(Status.AGUARDANDO);

		Doador doador = doadorRepository.findById(doacaoDTO.getCliente()).orElseThrow(
				() -> new RestNotFoundException("Doador não encontrado com o id: " + doacaoDTO.getCliente()));
		doacao.setDoador(doador);

		if (doacaoDTO.getSolicitacaoProduto() != null) {
			SolicitacaoProdutoDTO solicitacaoProdutoDTO = doacaoDTO.getSolicitacaoProduto();
			SolicitacaoProduto solicitacaoProduto = solicitacaoProdutoRepository.findById(solicitacaoProdutoDTO.getId())
					.orElseThrow(() -> new RestNotFoundException(
							"Solicitação de Produto não encontrada com o id: " + solicitacaoProdutoDTO.getId()));
			doacao.setSolicitacaoProduto(solicitacaoProduto);

			if (solicitacaoProduto.getSolicitacao().getStatus() == Status.CANCELADO) {
				throw new BadRequestException(
						"Não é possível criar uma doação para uma solicitação de produto cancelada.");
			}

			// Verificar se o cliente já fez uma doação anteriormente para a mesma
			// solicitação de SolicitacaoProduto
			boolean hasExistingDoacao = doacaoRepository.existsByDoadorAndSolicitacaoProduto(doador,
					solicitacaoProduto);
			if (hasExistingDoacao) {
				throw new BadRequestException("O cliente já fez uma doação para esta solicitação de produto.");
			}
		}

		Doacao novaDoacao = doacaoRepository.save(doacao);
		return new DoacaoResponseDTO(novaDoacao);
	}

	@Override
	public DoacaoResponseDTO atualizar(Long id, DoacaoDTO doacaoDTO) {
		Doacao doacao = doacaoRepository.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Doação não encontrada com o id: " + id));

		if (doacaoDTO.getCliente() != null) {
			Doador doador = doadorRepository.findById(doacaoDTO.getCliente()).orElseThrow(
					() -> new RestNotFoundException("Doador não encontrado com o id: " + doacaoDTO.getCliente()));
			doacao.setDoador(doador);
		}

		if (doacaoDTO.getSolicitacaoProduto() != null) {
			SolicitacaoProduto solicitacaoProduto = solicitacaoProdutoRepository
					.findById(doacaoDTO.getSolicitacaoProduto().getId())
					.orElseThrow(() -> new RestNotFoundException("Solicitação de Produto não encontrada com o id: "
							+ doacaoDTO.getSolicitacaoProduto().getId()));
			doacao.setSolicitacaoProduto(solicitacaoProduto);
		} else {
			doacao.setSolicitacaoProduto(null);
		}

		Doacao doacaoAtualizada = doacaoRepository.save(doacao);
		return new DoacaoResponseDTO(doacaoAtualizada);
	}

	@Override
	public void deletar(Long id) {
		Doacao doacao = doacaoRepository.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Doação não encontrada com o id: " + id));
		doacaoRepository.delete(doacao);
	}

	@Override
	public DoacaoResponseDTO atualizarStatus(Long id, Status novoStatus) {
		Doacao doacao = doacaoRepository.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Doação não encontrada com o id: " + id));

		if (novoStatus == Status.CANCELADO) {
			SolicitacaoProduto solicitacaoProduto = doacao.getSolicitacaoProduto();
			if (solicitacaoProduto != null) {
				doacaoRepository.delete(doacao);
				return new DoacaoResponseDTO(doacao);
			}
		}

		doacao.setStatus(novoStatus);
		Doacao doacaoAtualizada = doacaoRepository.save(doacao);
		return new DoacaoResponseDTO(doacaoAtualizada);
	}

	private boolean verificaDoacoesProdutoSolicitacao(SolicitacaoProdutoResponseDTO solicitacaoProdutoResponse) {
		SolicitacaoProduto solicitacaoProduto = new SolicitacaoProduto();
		solicitacaoProduto.setId(solicitacaoProdutoResponse.getId());

		return solicitacaoRepository.existsDoacaoBySolicitacaoProduto(solicitacaoProduto);
	}

	@Override
	public Page<DoacaoResponseDTO> buscarDoacoesPorIdCliente(Long idCliente, Pageable pageable) {
	    Doador doador = doadorRepository.findById(idCliente)
	            .orElseThrow(() -> new RestNotFoundException("Doador com ID " + idCliente + " não encontrado."));

	    Page<Doacao> doacoes = doacaoRepository.findByDoador(doador, pageable);

	    return doacoes.map(doacao -> {
	        DoacaoResponseDTO doacaoResponseDTO = new DoacaoResponseDTO(doacao);
	        doacaoResponseDTO.getSolicitacaoProduto()
	                .setJaFoiDoado(verificaDoacoesProdutoSolicitacao(doacaoResponseDTO.getSolicitacaoProduto()));
	        return doacaoResponseDTO;
	    });
	}


}
