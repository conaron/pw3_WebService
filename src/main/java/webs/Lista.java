/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webs;

import control.CidadeUtil;
import control.CursoDepartamentoUtil;
import control.CursoUtil;
import control.EnqueteUtil;
import control.NoticiaUtil;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Ton
 */
@WebService(serviceName = "lista")
public class Lista {

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

    @WebMethod(operationName = "cidade")
    public String cidade() {
        CidadeUtil dao = new CidadeUtil();
        String retorno = dao.listaJson();
        return retorno;
    }

    @WebMethod(operationName = "enquete")
    public String enquete() {
        EnqueteUtil dao = new EnqueteUtil();
        String retorno = dao.listaJson();
        return retorno;
    }

    /**
     * Operação de Web service
     *
     * @return
     */
    @WebMethod(operationName = "lista_departamento")
    public String lista_departamento() {
        CursoDepartamentoUtil dao = new CursoDepartamentoUtil();
        String retorno = dao.listaJson();
        return retorno;
    }

    /**
     * Operação de Web service
     *
     * @return
     */
    @WebMethod(operationName = "lista_curso")
    public String lista_curso() {
        CursoUtil dao = new CursoUtil();
        String retorno = dao.listaJson();
        return retorno;
    }

    /**
     * Operação de Web service
     *
     * @param departamento
     * @return
     */
    @WebMethod(operationName = "lista_curso_departamento")
    public String lista_curso_departamento(@WebParam(name = "departamento") int departamento) {
        CursoUtil dao = new CursoUtil();
        String retorno = dao.listaJsonDepartamento(departamento);
        return retorno;
    }

    /**
     * Operação de Web service
     *
     * @return
     */
    @WebMethod(operationName = "lista_noticia")
    public String lista_noticia() {
        NoticiaUtil dao = new NoticiaUtil();
        String retorno = dao.listaJson();
        return retorno;
    }

}
