package br.com.fiap.foodshare.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.foodshare.dto.ProdutoDTO;
import br.com.fiap.foodshare.dto.responseDTO.ProdutoResponseDTO;
import br.com.fiap.foodshare.exception.RestNotFoundException;
import br.com.fiap.foodshare.models.Produto;
import br.com.fiap.foodshare.repository.ProdutoRepository;
import br.com.fiap.foodshare.services.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	public Page<ProdutoResponseDTO> buscarTodos(Pageable pageable) {
		Page<Produto> produtos = produtoRepository.findAll(pageable);
		return produtos.map(produto -> new ProdutoResponseDTO(produto));
	}

	@Override
	public ProdutoResponseDTO buscarPorId(Long id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Produto não encontrado com o id: " + id));
		return new ProdutoResponseDTO(produto);
	}

	@Override
	public ProdutoResponseDTO cadastrar(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		produto.setNome(produtoDTO.getNome());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setPeso(produtoDTO.getPeso());

		Produto novoProduto = produtoRepository.save(produto);
		return new ProdutoResponseDTO(novoProduto);
	}

	@Override
	public ProdutoResponseDTO atualizar(Long id, ProdutoDTO produtoDTO) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Produto não encontrado com o id: " + id));
		produto.setNome(produtoDTO.getNome());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setPeso(produtoDTO.getPeso());

		Produto produtoAtualizado = produtoRepository.save(produto);
		return new ProdutoResponseDTO(produtoAtualizado);
	}

	@Override
	public void deletar(Long id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new RestNotFoundException("Produto não encontrado com o id: " + id));
		produtoRepository.delete(produto);
	}

}
