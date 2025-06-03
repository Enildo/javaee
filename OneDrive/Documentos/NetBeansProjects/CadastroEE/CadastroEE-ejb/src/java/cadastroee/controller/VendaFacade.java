package cadastroee.controller;

import cadastroee.model.Produto;
import cadastroee.model.Venda;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VendaFacade extends AbstractFacade<Venda> implements VendaFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VendaFacade() {
        super(Venda.class);
    }

    // Método adicional: localizar vendas por produto
    @Override
    public List<Venda> findByProduto(Produto produto) {
        return em.createQuery("SELECT v FROM Venda v WHERE v.idProduto = :produto", Venda.class)
                 .setParameter("produto", produto)
                 .getResultList();
    }
}
