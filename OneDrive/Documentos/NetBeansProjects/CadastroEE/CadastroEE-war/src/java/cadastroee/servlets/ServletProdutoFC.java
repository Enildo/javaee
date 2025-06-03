package cadastroee.servlets;

import cadastroee.controller.CompraFacadeLocal;
import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.controller.VendaFacadeLocal;
import cadastroee.model.Compra;
import cadastroee.model.Produto;
import cadastroee.model.Venda;
import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ServletProdutoFC", urlPatterns = {"/ServletProdutoFC"})
public class ServletProdutoFC extends HttpServlet {

    @EJB
    private ProdutoFacadeLocal facade;

    @EJB
    private CompraFacadeLocal compraFacade;

    @EJB
    private VendaFacadeLocal vendaFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        String destino;

        try {
            if (acao == null) {
                acao = "listar";
            }

            if (acao.equals("formIncluir") || acao.equals("formAlterar")) {
                destino = "ProdutoDados.jsp";

                if (acao.equals("formAlterar")) {
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    Produto produto = facade.find(id);
                    request.setAttribute("produto", produto);
                }

            } else {
                destino = "ProdutoLista.jsp";

                if (acao.equals("listar")) {
                    request.setAttribute("produtos", facade.findAll());

                } else if (acao.equals("excluir")) {
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    Produto produto = facade.find(id);

                    // Excluir todas as vendas associadas
                    List<Venda> vendas = vendaFacade.findByProduto(produto);
                    for (Venda venda : vendas) {
                        vendaFacade.remove(venda);
                    }

                    // Excluir todas as compras associadas
                    List<Compra> compras = compraFacade.findByProduto(produto);
                    for (Compra compra : compras) {
                        compraFacade.remove(compra);
                    }

                    // Agora excluir o produto
                    facade.remove(produto);

                    request.setAttribute("mensagem", "Produto, compras e vendas associadas excluídos com sucesso!");
                    request.setAttribute("produtos", facade.findAll());

                } else if (acao.equals("alterar")) {
                    Integer id = Integer.parseInt(request.getParameter("id"));
                    Produto produto = facade.find(id);
                    produto.setNome(request.getParameter("nome"));
                    produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                    produto.setPrecoVenda(Float.parseFloat(request.getParameter("precoVenda")));
                    facade.edit(produto);
                    request.setAttribute("mensagem", "Produto alterado com sucesso!");
                    request.setAttribute("produtos", facade.findAll());

                } else if (acao.equals("incluir")) {
                    Produto produto = new Produto();
                    produto.setNome(request.getParameter("nome"));
                    produto.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                    produto.setPrecoVenda(Float.parseFloat(request.getParameter("precoVenda")));
                    facade.create(produto);
                    request.setAttribute("mensagem", "Produto incluído com sucesso!");
                    request.setAttribute("produtos", facade.findAll());
                }
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(destino);
            dispatcher.forward(request, response);

        } catch (Exception e) {
            throw new ServletException("Erro no processamento da ação: " + acao, e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
