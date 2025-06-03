package cadastroee.controller;

import cadastroee.model.Compra;
import cadastroee.model.Produto;
import java.util.List;
import jakarta.ejb.Local;

@Local
public interface CompraFacadeLocal {

    void create(Compra compra);

    void edit(Compra compra);

    void remove(Compra compra);

    Compra find(Object id);

    List<Compra> findAll();

    List<Compra> findRange(int[] range);

    int count();

    // Método adicional: localizar compras por produto
    List<Compra> findByProduto(Produto produto);
}
