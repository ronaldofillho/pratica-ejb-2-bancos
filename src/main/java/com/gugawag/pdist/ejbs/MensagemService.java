package com.gugawag.pdist.ejbs;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;

import com.gugawag.pdist.model.Mensagem;


@Stateless(name = "mensagemService")
@Remote
public class MensagemService {

    @EJB
    private MensagemDAO mensagemDAO;

    public List<Mensagem> listar() {
        return mensagemDAO.listar();
    }

    public void inserir(long id, String msg) {
        // Verifica se a mensagem contém palavras proibidas antes de inserir no banco
        if (msg.contains("palavrao")) {
            throw new RuntimeException("Proibido palavras de baixo calão");
        }

        // Se não houver palavras proibidas, prossegue com a inserção
        Mensagem novaMsg = new Mensagem(id, msg);
        mensagemDAO.inserir(novaMsg);
    }
}
