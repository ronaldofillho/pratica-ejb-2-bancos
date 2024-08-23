package com.gugawag.pdist.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.annotation.WebServlet;

import com.gugawag.pdist.ejbs.MensagemService;
import com.gugawag.pdist.model.Mensagem;

@WebServlet(urlPatterns = {"/mensagem.do"})
public class MensagemServlet extends javax.servlet.http.HttpServlet {

    @EJB(lookup = "java:module/mensagemService")
    private MensagemService msgService;

    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse res) throws javax.servlet.ServletException, IOException {
        String operacao = req.getParameter("oper");
        PrintWriter resposta = res.getWriter();
        switch (operacao) {
            case "3": {
                long id = Integer.parseInt(req.getParameter("id"));
                String msg = req.getParameter("mensagem");
                msgService.inserir(id, msg);
            }

            case "4": {
                for(Mensagem msg: msgService.listar()) {
                    resposta.println(msg.getMensagem());
                }
                break;
            }
        }
    }

}