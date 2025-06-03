package cadastroee.controller;

import cadastroee.model.Compra;
import cadastroee.model.Produto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class CompraFacade extends AbstractFacade<Compra> implements CompraFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }

    // Método adicional: localizar compras por produto
    @Override
    public List<Compra> findByProduto(Produto produto) {
        return em.createQuery("SELECT c FROM Compra c WHERE c.idProduto = :produto", Compra.class)
                 .setParameter("produto", produto)
                 .getResultList();
    }
}
