package cadastroee.controller;

import cadastroee.model.Produto;
import cadastroee.model.Venda;
import java.util.List;
import jakarta.ejb.Local;

@Local
public interface VendaFacadeLocal {

    void create(Venda venda);

    void edit(Venda venda);

    void remove(Venda venda);

    Venda find(Object id);

    List<Venda> findAll();

    List<Venda> findRange(int[] range);

    int count();

    // Método adicional: localizar vendas por produto
    List<Venda> findByProduto(Produto produto);
}
