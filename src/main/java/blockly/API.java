package blockly;

import cronapi.*;
import cronapi.rest.security.CronappSecurity;
import java.util.concurrent.Callable;


@CronapiMetaData(type = "blockly")
@CronappSecurity
public class API {

public static final int TIMEOUT = 300;

/**
 *
 * @param Dados
 *
 * @author Ícaro Antunes
 * @since 06/12/2023, 14:26:26
 *
 */
public static Var retornaTokenAuth(@ParamMetaData(description = "Dados", id = "db74c577") Var Dados) throws Exception {
 return new Callable<Var>() {

   public Var call() throws Exception {
    return
cronapi.util.Operations.getSystemParameter(
Var.valueOf("urlApi"));
   }
 }.call();
}

}

