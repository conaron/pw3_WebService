/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webs;

import com.google.gson.Gson;
import control.EnqueteUtil;
import control.InscricaoUtil;
import control.UsuarioCursoUtil;
import control.UsuarioUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import model.Curso;
import model.CursoHorario;
import model.Enquete;
import model.Inscricao;
import model.Usuario;
import model.UsuarioCurso;
import model.UsuarioDados;

/**
 *
 * @author Ton
 */
@WebService(serviceName = "recebe")
public class Recebe {

    /**
     * This is a sample web service operation
     *
     * @param txt
     * @return
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * This is a sample web service operation
     *
     * @param opcao
     * @return
     */
    @WebMethod(operationName = "recebe_enquete")
    public String recebe_enquete(@WebParam(name = "opcao") int opcao) {
        EnqueteUtil dao = new EnqueteUtil();
        Enquete enquete = dao.findEnquetesUltima().get(0);
        String saida = "Nada alterado";

        switch (opcao) {
            case 1:
                enquete.setOpcao1Quantidade(enquete.getOpcao1Quantidade() + 1);
                break;
            case 2:
                enquete.setOpcao2Quantidade(enquete.getOpcao2Quantidade() + 1);
                break;
            case 3:
                enquete.setOpcao3Quantidade(enquete.getOpcao3Quantidade() + 1);
                break;
        }

        try {
            dao.edit(enquete);
            saida = "Voto registrado";
        } catch (Exception ex) {
            Logger.getLogger(Recebe.class.getName()).log(Level.SEVERE, null, ex);
        }

        saida += "\n" + dao.findEnquetesUltima().get(0).toString();

        String retorno = dao.listaJson();

        return retorno;
    }

    /**
     * Operação de Web service
     *
     * @param autenticacao
     * @return
     */
    @WebMethod(operationName = "recebe_login")
    public String recebe_login(@WebParam(name = "autenticacao") final String autenticacao) {
        UsuarioUtil dao = new UsuarioUtil();
        Usuario usuario = new Usuario();
        UsuarioCursoUtil daoC = new UsuarioCursoUtil();
        String retorno;
        String dados = "";

        usuario.setUsuarioId(0);

        for (Usuario u : dao.findUsuarioEntities()) {
            if (autenticacao.equals(u.getHash())) {
                usuario = u;
                usuario.setSenha("hahaha");
                dados = daoC.listaJsonPorUsuario(usuario.getUsuarioId());
            }
        }

        retorno = (new Gson().toJson(usuario)) + dados;

//        retorno += ";;;" + daoC.listaJsonPorUsuario(usuario.getUsuarioId());
//        retorno = autenticacao    ;
        return retorno;
    }

    /**
     * Operação de Web service
     *
     * @param inscricao
     * @return
     */
    @WebMethod(operationName = "recebe_inscricao")
    public String recebe_inscricao(@WebParam(name = "inscricao") String inscricao) {
        Inscricao i = new Gson().fromJson(inscricao, Inscricao.class);
        InscricaoUtil dao = new InscricaoUtil();
        String retorno = dao.create(i);
        return retorno;
    }

}
