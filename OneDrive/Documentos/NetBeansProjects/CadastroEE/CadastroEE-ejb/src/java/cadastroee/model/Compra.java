package cadastroee.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Compra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Compra.findAll", query = "SELECT c FROM Compra c"),
    @NamedQuery(name = "Compra.findByIdCompra", query = "SELECT c FROM Compra c WHERE c.idCompra = :idCompra"),
    @NamedQuery(name = "Compra.findByDataCompra", query = "SELECT c FROM Compra c WHERE c.dataCompra = :dataCompra"),
    @NamedQuery(name = "Compra.findByQuantidade", query = "SELECT c FROM Compra c WHERE c.quantidade = :quantidade"),
    @NamedQuery(name = "Compra.findByPrecoUnitario", query = "SELECT c FROM Compra c WHERE c.precoUnitario = :precoUnitario")
})
public class Compra implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "id_compra")
    private Integer idCompra;

    @Column(name = "data_compra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCompra;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    @JoinColumn(name = "id_fornecedor", referencedColumnName = "id_pessoa")
    @ManyToOne
    private PessoaJuridica idFornecedor;

    @JoinColumn(name = "id_produto", referencedColumnName = "id_produto")
    @ManyToOne
    private Produto idProduto;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idUsuario;

    public Compra() {
    }

    public Compra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public Date getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public PessoaJuridica getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(PessoaJuridica idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public Produto getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Produto idProduto) {
        this.idProduto = idProduto;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCompra != null ? idCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Compra)) {
            return false;
        }
        Compra other = (Compra) object;
        return !((this.idCompra == null && other.idCompra != null) || 
                (this.idCompra != null && !this.idCompra.equals(other.idCompra)));
    }

    @Override
    public String toString() {
        return "cadastroee.model.Compra[ idCompra=" + idCompra + " ]";
    }
}
