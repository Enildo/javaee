package cadastroee.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import cadastroee.controller.ProdutoFacadeLocal;
import cadastroee.model.Produto;

@WebServlet(name = "ServletProduto", urlPatterns = {"/ServletProduto"})
public class ServletProduto extends HttpServlet {

    @EJB
    ProdutoFacadeLocal facade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Lista de Produtos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de Produtos:</h1>");
            
            try {
                for (Produto p : facade.findAll()) {
                    out.println("<p>" + p.getNome() + " - R$ " + p.getPrecoVenda() + "</p>");
                }
            } catch (Exception e) {
                out.println("<p>Erro ao listar produtos: " + e.getMessage() + "</p>");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);  // Redireciona POST para GET
    }

    @Override
    public String getServletInfo() {
        return "Servlet que lista produtos usando ProdutoFacadeLocal";
    }
}
